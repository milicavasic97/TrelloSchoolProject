package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.entities.MemberCardEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.MemberCardEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IMemberCardService;

import javax.transaction.Transactional;

@Service
@Transactional
public class MemberCardService extends CrudJpaService<MemberCardEntity, String> implements IMemberCardService {
    private final ModelMapper modelMapper;
    private final MemberCardEntityRepository repository;

    public MemberCardService(ModelMapper _mapper, MemberCardEntityRepository _repository) {
        super(_repository, _mapper, MemberCardEntity.class);
        modelMapper = _mapper;
        repository = _repository;
    }
}
