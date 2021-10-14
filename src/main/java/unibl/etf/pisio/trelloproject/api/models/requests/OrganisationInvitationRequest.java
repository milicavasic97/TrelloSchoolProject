package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrganisationInvitationRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String fullName;
}
