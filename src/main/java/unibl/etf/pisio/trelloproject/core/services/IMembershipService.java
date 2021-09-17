package unibl.etf.pisio.trelloproject.core.services;

import unibl.etf.pisio.trelloproject.core.base.ICrudService;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationDTO;

import java.util.List;

public interface IMembershipService extends ICrudService<String> {
    List<MemberDTO> getAllMembersByOrganisationId(String id);
    List<OrganisationDTO> getAllOrganisationsByMemberId(String id);
}
