package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.api.models.requests.TrelloListRequest;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.dto.TrelloListDTO;
import unibl.etf.pisio.trelloproject.core.models.entities.TrelloListEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.TrelloListEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.ITrelloListService;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrelloListService extends CrudJpaService<TrelloListEntity, String> implements ITrelloListService {
    private final ModelMapper modelMapper;
    private final TrelloListEntityRepository repository;
    private final CardService cardService;
    private final MemberCardService memberCardService;
    private final CommentService commentService;

    public TrelloListService(ModelMapper _mapper, TrelloListEntityRepository _repository,
                             CardService _cardService, MemberCardService _memberCardService,
                             CommentService _commentService) {
        super(_repository, _mapper, TrelloListEntity.class);
        modelMapper = _mapper;
        repository = _repository;
        cardService = _cardService;
        memberCardService = _memberCardService;
        commentService = _commentService;
    }

    @Override
    public List<TrelloListDTO> getAllListsByBoardId(String boardId) {
        return repository.getAllByBoard_Id(boardId)
                .stream()
                .map(e -> modelMapper.map(e, TrelloListDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteCardsAndList(String id) {
        TrelloListEntity trelloList = super.findEntityById(id);
        cardService.deleteCardsByTrelloListIdRestrict(trelloList.getId());

        super.delete(id);
    }

    public void updateListPosition(String listId, Integer position) {
        TrelloListDTO trelloListDTO = super.findById(listId, TrelloListDTO.class);
        if (trelloListDTO.getPos() != position) {
            trelloListDTO.setPos(position);
            super.update(listId, trelloListDTO, TrelloListDTO.class);
        }

    }

    @Override
    public TrelloListDTO insert(TrelloListRequest request) {
        List<TrelloListEntity> trelloListEntities = repository.getAllByBoard_Id(request.getIdBoard());
        if (!trelloListEntities.isEmpty()) {
            int i = 1;
            trelloListEntities.sort(Comparator.comparing(TrelloListEntity::getPos));
            for (var list : trelloListEntities)
                updateListPosition(list.getId(), 100 * i++);
            request.setPos((trelloListEntities.size() + 1) * 100);
        } else
            request.setPos(100);
        return super.insert(request, TrelloListDTO.class);
    }
}
