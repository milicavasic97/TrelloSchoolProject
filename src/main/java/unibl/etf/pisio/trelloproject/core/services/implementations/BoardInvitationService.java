package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.entities.BoardInvitationEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.BoardInvitationEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IBoardInvitationService;
import unibl.etf.pisio.trelloproject.core.services.IBoardService;

import javax.transaction.Transactional;

@Service
@Transactional
public class BoardInvitationService extends CrudJpaService<BoardInvitationEntity, String> implements IBoardInvitationService {
    private final ModelMapper modelMapper;
    private final BoardInvitationEntityRepository repository;

    public BoardInvitationService(ModelMapper _modelMapper, BoardInvitationEntityRepository _repository) {
        super(_repository, _modelMapper, BoardInvitationEntity.class);
        modelMapper = _modelMapper;
        repository = _repository;
    }

}
