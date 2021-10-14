package unibl.etf.pisio.trelloproject.core.models.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.List;

@Data
public class TrelloListDTO {
    private String id;
    private String name;
    private Boolean closed;
    private Integer pos;
    private Boolean subscribed;
    private String idBoard;
    private List<CardDTO> cards;
}
