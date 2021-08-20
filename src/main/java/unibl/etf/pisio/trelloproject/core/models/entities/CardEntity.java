package unibl.etf.pisio.trelloproject.core.models.entities;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import unibl.etf.pisio.trelloproject.core.base.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "card")
public class CardEntity implements BaseEntity<String> {
    @Id
    @Column(name = "id", nullable = false, length = 24)
    private String id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "desc", nullable = true, length = -1)
    private String desc;
    @Basic
    @Column(name = "url", nullable = false, length = -1)
    private String url;
    @Basic
    @Column(name = "due", nullable = true)
    private Timestamp due;
    @Basic
    @Column(name = "dueComplete", nullable = true)
    private Boolean dueComplete;
    @Basic
    @Column(name = "closed", nullable = true)
    private Boolean closed;
    @Basic
    @Column(name = "dateLastActivity", nullable = true)
    private Timestamp dateLastActivity;
    @Basic
    @Column(name = "pos", nullable = false)
    private Integer pos;
    @Basic
    @Column(name = "shortLink", nullable = true, length = -1)
    private String shortLink;
    @Basic
    @Column(name = "shortUrl", nullable = true, length = -1)
    private String shortUrl;
    @Basic
    @Column(name = "subscribed", nullable = true)
    private Boolean subscribed;
    @ManyToOne
    @JoinColumn(name = "idList", referencedColumnName = "id", nullable = false)
    private TrelloListEntity trellolist;
    @OneToMany(mappedBy = "card")
    @JsonIgnore
    private List<CommentEntity> comments;
    @OneToMany(mappedBy = "card")
    @JsonIgnore
    private List<MemberCardEntity> membercards;

}
