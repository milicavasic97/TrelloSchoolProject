package unibl.etf.pisio.trelloproject.core.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CardDTO {
    private String id;
    private String name;
    private String desc;
    private String url;
    private Date due;
    private Boolean dueComplete;
    private Boolean closed;
    private Date dateLastActivity;
    private Integer pos;
    private String shortLink;
    private String shortUrl;
    private Boolean subscribed;
}
