package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardRequest {
    private String id;
    @NotBlank
    private String name;
    private String desc;
    @NotBlank
    private String idOrganisation;
}
