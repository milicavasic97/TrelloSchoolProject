package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CardUpdateRequest {
    @NotBlank
    private String id;
//    @NotBlank
    private String name;
    private String desc;
    private String url;
    private Date due;
    private Boolean dueComplete;
    private Boolean closed;
    @NotNull
    private Integer pos;
    private String shortLink;
    private String shortUrl;
    @NotBlank
    private String idTrellolist;
}
