package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

@Data
public class TrelloListRequest {
    private String id;
    private String name;
    private Boolean closed;
    private Integer pos;
    private Boolean subscribed;
}
