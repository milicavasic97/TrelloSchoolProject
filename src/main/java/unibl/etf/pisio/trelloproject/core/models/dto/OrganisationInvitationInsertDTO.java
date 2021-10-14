package unibl.etf.pisio.trelloproject.core.models.dto;

import lombok.Data;

@Data
public class OrganisationInvitationInsertDTO {
    private String idOrganisation;
    private String idMember;
    private Boolean accepted;
}
