package unibl.etf.pisio.trelloproject.core.models.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;

@Data
public class TrelloListDTO {
    private String id;
    private String name;
    private Boolean closed;
    private Integer pos;
    private Boolean subscribed;
}
