package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.entities.TrelloListEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.TrelloListEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.ITrelloListService;

import javax.transaction.Transactional;

@Service
@Transactional
public class TrelloListService extends CrudJpaService<TrelloListEntity, String> implements ITrelloListService {
    private final ModelMapper modelMapper;
    private final TrelloListEntityRepository repository;

    public TrelloListService(ModelMapper _mapper, TrelloListEntityRepository _repository) {
        super(_repository, _mapper, TrelloListEntity.class);
        modelMapper = _mapper;
        repository = _repository;
    }
}
