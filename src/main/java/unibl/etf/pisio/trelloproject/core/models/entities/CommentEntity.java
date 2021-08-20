package unibl.etf.pisio.trelloproject.core.models.entities;

import lombok.*;
import unibl.etf.pisio.trelloproject.core.base.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "comment")
public class CommentEntity implements BaseEntity<String> {
    @Id
    @Column(name = "id", nullable = false, length = 24)
    private String id;
    @Basic
    @Column(name = "text", nullable = false, length = -1)
    private String text;
    @Basic
    @Column(name = "date", nullable = false)
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name = "idCard", referencedColumnName = "id", nullable = false)
    private CardEntity card;
    @ManyToOne
    @JoinColumn(name = "idMember", referencedColumnName = "id", nullable = false)
    private MemberEntity member;

}
