package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.entities.CardEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.CardEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.ICardService;

import javax.transaction.Transactional;

@Service
@Transactional
public class CardService extends CrudJpaService<CardEntity, String> implements ICardService {
    private final ModelMapper modelMapper;
    private final CardEntityRepository repository;

    public CardService(ModelMapper _mapper, CardEntityRepository _repository) {
        super(_repository, _mapper, CardEntity.class);
        modelMapper = _mapper;
        repository = _repository;
    }

}
