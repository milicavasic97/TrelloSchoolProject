package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
public class BoardRequest {
    @NotBlank
    private String id;
    @NotBlank
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
    @NotBlank
    private String idOrganisation;
}
