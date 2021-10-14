package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.api.models.requests.CardInsertRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.CardUpdateRequest;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.dto.CardDTO;
import unibl.etf.pisio.trelloproject.core.models.entities.CardEntity;
import unibl.etf.pisio.trelloproject.core.models.entities.TrelloListEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.CardEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.ICardService;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CardService extends CrudJpaService<CardEntity, String> implements ICardService {
    private final ModelMapper modelMapper;
    private final CardEntityRepository repository;
    private final MemberCardService memberCardService;
    private final CommentService commentService;
    private final TrelloListService trelloListService;


    public CardService(ModelMapper _mapper, CardEntityRepository _repository,
                       MemberCardService _memberCardService, CommentService _commentService,
                       @Lazy TrelloListService trelloListService) {
        super(_repository, _mapper, CardEntity.class);
        modelMapper = _mapper;
        repository = _repository;
        memberCardService = _memberCardService;
        commentService = _commentService;
        this.trelloListService = trelloListService;
    }

    @Override
    public List<CardDTO> getAllByTrelloListId(String id) {
        return repository.getAllByTrellolist_Id(id)
                .stream()
                .map(o -> modelMapper.map(o, CardDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteCardsByTrelloListIdRestrict(String id) {
        List<CardEntity> entities = repository.getAllByTrellolist_Id(id);
        for (var card : entities) {
            memberCardService.deleteAllByCardId(card.getId());
            commentService.deleteAllByCardId(card.getId());
            super.delete(card.getId());
        }
    }

    @Override
    public CardDTO insert(CardInsertRequest request) {
        List<CardEntity> cardEntities = repository.getAllByTrellolist_Id(request.getIdTrellolist());
        if (!cardEntities.isEmpty()) {
            int i = 1;
            cardEntities.sort(Comparator.comparing(CardEntity::getPos));
            for (var card : cardEntities)
                updateListPosition(card.getId(), 100 * i++);
            request.setPos((cardEntities.size() + 1) * 100);
        } else
            request.setPos(100);
        return super.insert(request, CardDTO.class);
    }

    @Override
    public void updateListPosition(String cardId, Integer position) {
        CardDTO cardDTO = super.findById(cardId, CardDTO.class);
        if (cardDTO.getPos() != position) {
            cardDTO.setPos(position);
            super.update(cardId, cardDTO, CardDTO.class);
        }
    }

    @Override
    public CardDTO update(String id, CardUpdateRequest request) {
        TrelloListEntity trelloListEntity = trelloListService.findEntityById(request.getIdTrellolist());
        List<CardDTO> cards = trelloListEntity.getCards().stream()
                .map(o -> modelMapper.map(o, CardDTO.class))
                .sorted(Comparator.comparing(CardDTO::getPos))
                .collect(Collectors.toList());
        CardDTO card = super.findById(id, CardDTO.class);

//        if(card.getPos().equals(request.getPos()) && card.getIdTrellolist().equals(request.getIdTrellolist()))
        if (request.getName() != null)
            return super.update(id, request, CardDTO.class);

        if (cards.isEmpty()) {
            card.setPos(100);
        } else if (!card.getPos().equals(request.getPos()) || !card.getIdTrellolist().equals(request.getIdTrellolist())) {
            if (request.getPos() == 0) {
                if (cards.get(0).getPos() > 2)
                    card.setPos(cards.get(0).getPos() / 2);
                else {
                    card.setPos(reorderCards(request.getPos(), request, cards).getPos());
                }
            } else if ((!request.getIdTrellolist().equals(card.getIdTrellolist()) && request.getPos() == cards.size())
                    || (request.getIdTrellolist().equals(card.getIdTrellolist()) && request.getPos() == cards.size() - 1)) {
                int i = 1;
                while (repository.existsByPosAndTrellolist_Id((request.getPos() + i) * 100, request.getIdTrellolist()))
                    i++;
                card.setPos((request.getPos() + i) * 100);
            } else {
                Integer newPos = null;
                if (!card.getIdTrellolist().equals(request.getIdTrellolist()) || cards.indexOf(card) > request.getPos())
                    newPos = (cards.get(request.getPos() - 1).getPos() + cards.get(request.getPos()).getPos()) / 2;
                else
                    newPos = (cards.get(request.getPos()).getPos() + cards.get(request.getPos() + 1).getPos()) / 2;

                if (!repository.existsByPosAndTrellolist_Id(newPos, request.getIdTrellolist())) {
                    card.setPos(newPos);
                } else
                    card.setPos(reorderCards(request.getPos(), request, cards).getPos());
            }
        }

        if (!request.getIdTrellolist().equals(card.getIdTrellolist())) {
            card.setIdTrellolist(request.getIdTrellolist());
        }

        return super.update(id, card, CardDTO.class);
    }

    private CardUpdateRequest reorderCards(Integer pos, CardUpdateRequest request, List<CardDTO> cards) {
        int i = 0;
        for (var card : cards) {
            if (i == pos) {
                request.setPos(++i * 100);
            }
            card.setPos(++i * 100);
            super.update(card.getId(), card, CardDTO.class);
        }
        return request;
    }
}
