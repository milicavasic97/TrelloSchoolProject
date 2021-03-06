package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TrelloListRequest {
    private String id;
    @NotBlank
    private String name;
//    @NotNull
    private Integer pos;
    @NotBlank
    private String idBoard;
}
