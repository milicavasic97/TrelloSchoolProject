package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationDTO;
import unibl.etf.pisio.trelloproject.core.models.entities.MembershipEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.MembershipEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IMembershipService;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class MembershipService extends CrudJpaService<MembershipEntity, String> implements IMembershipService {
    private final ModelMapper modelMapper;
    private final MembershipEntityRepository repository;

    public MembershipService(ModelMapper _mapper, MembershipEntityRepository _repository) {
        super(_repository, _mapper, MembershipEntity.class);
        modelMapper = _mapper;
        repository = _repository;
    }

    @Override
    public List<MemberDTO> getAllMembersByOrganisationId(String id) {
        List<MemberDTO> memberDTOList = new ArrayList<>();
        List<MembershipEntity> membershipEntities = repository.getAllByOrganisation_Id(id);
        while (!membershipEntities.isEmpty()) {
            MemberDTO dto = modelMapper.map(membershipEntities.remove(0).getMember(), MemberDTO.class);
            if (!memberDTOList.contains(dto))
                memberDTOList.add(dto);
        }
        return memberDTOList;
    }

    @Override
    public List<OrganisationDTO> getAllOrganisationsByMemberId(String id) {
        List<OrganisationDTO> organisationDTOList = new ArrayList<>();
        List<MembershipEntity> membershipEntities = repository.getAllByMember_Id(id);
        while (!membershipEntities.isEmpty()) {
            OrganisationDTO dto = modelMapper.map(membershipEntities.remove(0).getOrganisation(), OrganisationDTO.class);
            if(!organisationDTOList.contains(dto))
                organisationDTOList.add(dto);
        }
        return organisationDTOList;
    }
}
