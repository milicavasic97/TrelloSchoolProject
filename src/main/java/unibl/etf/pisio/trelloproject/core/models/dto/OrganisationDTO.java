package unibl.etf.pisio.trelloproject.core.models.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;

@Data
public class OrganisationDTO {
    private String id;
    private String name;
    private String displayName;
    private String desc;
    private Boolean invited;
    private String url;
    private String website;
}
