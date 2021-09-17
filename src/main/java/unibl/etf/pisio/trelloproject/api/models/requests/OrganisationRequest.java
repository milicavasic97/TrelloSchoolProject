package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

@Data
public class OrganisationRequest {
    private String id;
    private String name;
    private String displayName;
    private String desc;
    private Boolean invited;
    private String url;
    private String website;
}
