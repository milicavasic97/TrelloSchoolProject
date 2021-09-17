package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.models.entities.OrganisationInvitationEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.OrganisationInvitationEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IOrganisationInvitationService;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrganisationInvitationService extends CrudJpaService<OrganisationInvitationEntity, String> implements IOrganisationInvitationService {
    private final ModelMapper modelMapper;
    private final OrganisationInvitationEntityRepository repository;

    public OrganisationInvitationService(ModelMapper _mapper, OrganisationInvitationEntityRepository _repository) {
        super(_repository, _mapper, OrganisationInvitationEntity.class);
        modelMapper = _mapper;
        repository = _repository;
    }
}
