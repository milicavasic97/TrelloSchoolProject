package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CardInsertRequest {
    @NotBlank
    private String name;
//    @NotNull
    private Integer pos;
    @NotBlank
    private String idTrellolist;
}
