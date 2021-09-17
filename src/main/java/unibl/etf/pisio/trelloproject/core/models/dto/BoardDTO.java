package unibl.etf.pisio.trelloproject.core.models.dto;

import lombok.Data;
import unibl.etf.pisio.trelloproject.core.models.entities.OrganisationEntity;

import java.sql.Timestamp;

@Data
public class BoardDTO {
    private String id;
    private String name;
    private String desc;
    private Boolean closed;
    private Boolean pinned;
    private String url;
    private Boolean invited;
    private String shortUrl;
    private Boolean subscribed;
    private Timestamp dateLastActivity;
    private Timestamp dateLastView;
    private String shortLink;
    private String idOrganisation;
}
