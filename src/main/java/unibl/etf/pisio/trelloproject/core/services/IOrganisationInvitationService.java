package unibl.etf.pisio.trelloproject.core.services;

import unibl.etf.pisio.trelloproject.api.models.requests.OrganisationInvitationRequest;
import unibl.etf.pisio.trelloproject.core.base.ICrudService;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationInvitationDTO;

import java.util.List;

public interface IOrganisationInvitationService extends ICrudService<String> {

    void insert(String idOrganisation, OrganisationInvitationRequest request);

    List<OrganisationInvitationDTO> getAllInvitationsByMemberId(String idMember);

    List<OrganisationInvitationDTO> getAllAcceptedInvitationsByOrganisation(String idOrganisation);

    void acceptInvitation(String idOrganisationInvitation);

    void rejectInvitation(String idOrganisationInvitation);

    List<OrganisationDTO> getAllAcceptedOrganisations(String idMember);
}
