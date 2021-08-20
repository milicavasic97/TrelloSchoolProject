package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.dto.BoardDTO;
import unibl.etf.pisio.trelloproject.core.models.entities.BoardEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.BoardEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IBoardService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoardService extends CrudJpaService<BoardEntity, String> implements IBoardService {
    private final ModelMapper modelMapper;
    private final BoardEntityRepository repository;

    public BoardService(ModelMapper _mapper, BoardEntityRepository _repository) {
        super(_repository, _mapper, BoardEntity.class);
        this.modelMapper = _mapper;
        this.repository = _repository;
    }


    @Override
    public List<BoardDTO> getAllBoardsByOrganisationId(String id) {
        return repository.getAllByOrganisation_Id(id)
                .stream()
                .map(e -> modelMapper.map(e, BoardDTO.class))
                .collect(Collectors.toList());
    }
}
