package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberCardDTO;
import unibl.etf.pisio.trelloproject.core.models.entities.MemberCardEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.MemberCardEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IMemberCardService;

import javax.transaction.Transactional;
import java.util.List;

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

    @Override
    public List<MemberCardEntity> getAllByCardId(String id) {
        return repository.getAllByCard_Id(id);
    }

    @Override
    public void deleteAllByCardId(String id) {
        repository.deleteAllByCard_Id(id);
    }

    @Override
    public MemberCardDTO findByMemberIdAndCardId(String idMember, String idCard) {
        return modelMapper.map(repository.findByMember_IdAndCard_Id(idMember, idCard), MemberCardDTO.class);
    }

    @Override
    public boolean existsByMemberIdAndCardId(String idMember, String idCard) {
        return repository.existsByMember_IdAndCard_Id(idMember, idCard);
    }
}
