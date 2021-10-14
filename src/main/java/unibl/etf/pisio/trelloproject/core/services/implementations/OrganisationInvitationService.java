package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.api.models.requests.OrganisationInvitationRequest;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.exceptions.ConflictException;
import unibl.etf.pisio.trelloproject.core.exceptions.NotFoundException;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationInvitationDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationInvitationInsertDTO;
import unibl.etf.pisio.trelloproject.core.models.entities.OrganisationInvitationEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.OrganisationInvitationEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IMemberService;
import unibl.etf.pisio.trelloproject.core.services.IOrganisationInvitationService;
import unibl.etf.pisio.trelloproject.core.services.IOrganisationService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrganisationInvitationService extends CrudJpaService<OrganisationInvitationEntity, String> implements IOrganisationInvitationService {
    private final ModelMapper modelMapper;
    private final OrganisationInvitationEntityRepository repository;
    private final IMemberService memberService;
    private final IMemberService membershipService;
    private final IOrganisationService organisationService;

    public OrganisationInvitationService(ModelMapper modelMapper,
                                         OrganisationInvitationEntityRepository repository,
                                         IMemberService memberService, IMemberService membershipService,
                                         IOrganisationService organisationService) {
        super(repository, modelMapper, OrganisationInvitationEntity.class);
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.memberService = memberService;
        this.membershipService = membershipService;
        this.organisationService = organisationService;
    }


    @Override
    public void insert(String idOrganisation, OrganisationInvitationRequest request) {
        MemberDTO memberDTO = memberService.findByEmail(request.getEmail());
        if (memberDTO == null)
            throw new NotFoundException();
        OrganisationInvitationEntity entity =
                repository.findByOrganisation_IdAndMember_Email(idOrganisation, request.getEmail());
        if (entity != null)
            throw new ConflictException();
        OrganisationDTO organisation = organisationService.findById(idOrganisation, OrganisationDTO.class);
        if(organisation.getIdCreatedBy().equals(memberDTO.getId()))
            throw new ConflictException("Member is admin!");

        OrganisationInvitationInsertDTO newOI = new OrganisationInvitationInsertDTO();
        newOI.setIdOrganisation(idOrganisation);
        newOI.setIdMember(memberDTO.getId());
        newOI.setAccepted(false);

        OrganisationInvitationEntity invitationEntity = super.insert(newOI, OrganisationInvitationEntity.class);

    }

    @Override
    public List<OrganisationInvitationDTO> getAllInvitationsByMemberId(String idMember) {
        return repository.findAllByMember_Id(idMember)
                .stream()
                .filter(o -> !o.getAccepted())
                .map(o -> modelMapper.map(o, OrganisationInvitationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrganisationInvitationDTO> getAllAcceptedInvitationsByOrganisation(String idOrganisation) {
        return repository.findAllByOrganisation_Id(idOrganisation)
                .stream()
                .filter(o -> o.getAccepted())
                .map(o -> modelMapper.map(o, OrganisationInvitationDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public void acceptInvitation(String idOrganisationInvitation) {
        OrganisationInvitationEntity entity = super.findEntityById(idOrganisationInvitation);
        entity.setAccepted(true);
        super.update(entity.getId(), entity, OrganisationInvitationDTO.class);
    }

    @Override
    public void rejectInvitation(String idOrganisationInvitation) {
        super.delete(idOrganisationInvitation);
    }

    @Override
    public List<OrganisationDTO> getAllAcceptedOrganisations(String idMember) {
        return repository.findAllByMember_Id(idMember)
                .stream()
                .filter(o -> o.getAccepted())
                .map(o -> modelMapper.map(o.getOrganisation(), OrganisationDTO.class))
                .collect(Collectors.toList());
    }
}
