package unibl.etf.pisio.trelloproject.core.models.entities;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import unibl.etf.pisio.trelloproject.core.base.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "card")
@EntityListeners(AuditingEntityListener.class)
public class CardEntity implements BaseEntity<String> {
    @Id
    @Column(name = "id", nullable = false, length = 24)
    private String id;
    @Basic
    @Column(name = "cardName", nullable = false, length = -1, unique = false)
    private String name;
    @Basic
    @Column(name = "cardDescription", nullable = true, length = -1)
    private String desc;
    @Basic
    @Column(name = "url", nullable = true, length = -1)
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
    @LastModifiedDate
    private Timestamp dateLastActivity;
    @Basic
    @Column(name = "position", nullable = false)
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
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommentEntity> comments;
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MemberCardEntity> membercards;
    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private Date createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    private Date modifiedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    @JoinColumn(name = "created_by", referencedColumnName = "id", updatable = false)
    private MemberEntity createdBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @LastModifiedBy
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private MemberEntity updatedBy;

}
