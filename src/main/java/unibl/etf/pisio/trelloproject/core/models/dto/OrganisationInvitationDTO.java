package unibl.etf.pisio.trelloproject.core.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationInvitationDTO {
    private String id;
    private String idOrganisation;
    private String idMember;
    private OrganisationDTO organisation;
    private MemberDTO member;
}
