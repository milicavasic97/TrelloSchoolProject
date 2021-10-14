package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

import java.util.Date;

@Data
public class CommentRequest {
    private String id;
    private String text;
    private Date date;
    private String idCard;
    private String idMember;
}
