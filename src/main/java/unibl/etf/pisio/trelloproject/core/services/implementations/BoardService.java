package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.api.models.requests.BoardRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.MembershipInsertRequest;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.dto.BoardDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.JwtUserDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.MembershipDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.TrelloListDTO;
import unibl.etf.pisio.trelloproject.core.models.entities.BoardEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.BoardEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IBoardService;
import unibl.etf.pisio.trelloproject.core.services.IMembershipService;
import unibl.etf.pisio.trelloproject.core.services.ITrelloListService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoardService extends CrudJpaService<BoardEntity, String> implements IBoardService {
    private final ModelMapper modelMapper;
    private final BoardEntityRepository repository;
    private final IMembershipService membershipService;
    private final ITrelloListService trelloListService;

    public BoardService(ModelMapper _mapper, BoardEntityRepository _repository, IMembershipService membershipService, ITrelloListService trelloListService) {
        super(_repository, _mapper, BoardEntity.class);
        this.modelMapper = _mapper;
        this.repository = _repository;
        this.membershipService = membershipService;
        this.trelloListService = trelloListService;
    }


    @Override
    public List<BoardDTO> getAllBoardsByOrganisationId(String id) {
        return repository.getAllByOrganisation_Id(id)
                .stream()
                .map(e -> modelMapper.map(e, BoardDTO.class))
                .collect(Collectors.toList());
    }

    public BoardDTO insert(BoardRequest request) {
        BoardDTO dto = modelMapper.map(request, BoardDTO.class);
        dto.setDateLastActivity(Timestamp.valueOf(LocalDateTime.now()));
        dto.setDateLastView(Timestamp.valueOf(LocalDateTime.now()));
        BoardDTO result = super.insert(dto, BoardDTO.class);

        MembershipInsertRequest membershipRequest = new MembershipInsertRequest();
        JwtUserDTO userDTO = (JwtUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        membershipRequest.setIdMember(userDTO.getId());
        membershipRequest.setIdBoard(result.getId());
        membershipRequest.setIdOrganisation(result.getIdOrganisation());
        membershipRequest.setMembershipType("ADMIN");
        membershipService.insert(membershipRequest, MembershipDTO.class);

        return result;
    }

    @Override
    public void deleteBoard(String id) {
        BoardEntity entity = super.findEntityById(id);
        List<TrelloListDTO> trelloListDTOS = trelloListService.getAllListsByBoardId(id);

        for(var list : trelloListDTOS)
            trelloListService.deleteCardsAndList(list.getId());

        super.delete(id);
    }
}
