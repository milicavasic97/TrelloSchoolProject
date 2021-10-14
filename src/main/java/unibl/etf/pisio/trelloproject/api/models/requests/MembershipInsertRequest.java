package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MembershipInsertRequest {
    private String id;
    @NotBlank
    private String idMember;
    @NotBlank
    private String idOrganisation;
    @NotBlank
    private String idBoard;
    @NotBlank
    private String membershipType;
}
