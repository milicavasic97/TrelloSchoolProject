package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrganisationRequest {
    private String id;
    private String name;
    @NotBlank
    private String displayName;
    private String desc;
}
