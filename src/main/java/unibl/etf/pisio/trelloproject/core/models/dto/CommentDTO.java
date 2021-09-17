package unibl.etf.pisio.trelloproject.core.models.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class CommentDTO {
    private String id;
    private String text;
    private Date date;

}
