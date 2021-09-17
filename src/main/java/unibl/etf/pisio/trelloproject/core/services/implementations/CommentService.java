package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.entities.CommentEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.CommentEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.ICommentService;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommentService extends CrudJpaService<CommentEntity, String> implements ICommentService {
    private final ModelMapper modelMapper;
    private final CommentEntityRepository repository;

    public CommentService(ModelMapper _mapper, CommentEntityRepository _repository) {
        super(_repository, _mapper, CommentEntity.class);
        modelMapper = _mapper;
        repository = _repository;
    }
}
