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
@Table(name = "board")
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity implements BaseEntity<String> {
    @Id
    @Column(name = "id", nullable = false, length = 24)
    private String id;
    @Basic
    @Column(name = "boardName", nullable = false, length = 512)
    private String name;
    @Basic
    @Column(name = "boardDescription", nullable = true, length = -1)
    private String desc;
    @Basic
    @Column(name = "closed", nullable = true)
    private Boolean closed;
    @Basic
    @Column(name = "pinned", nullable = true)
    private Boolean pinned;
    @Basic
    @Column(name = "url", nullable = true, length = -1)
    private String url;
    @Basic
    @Column(name = "invited", nullable = true)
    private Boolean invited;
    @Basic
    @Column(name = "shortUrl", nullable = true, length = -1)
    private String shortUrl;
    @Basic
    @Column(name = "subscribed", nullable = true)
    private Boolean subscribed;
    @Basic
    @Column(name = "dateLastActivity", nullable = true)
    private Timestamp dateLastActivity;
    @Basic
    @Column(name = "dateLastView", nullable = true)
    private Timestamp dateLastView;
    @Basic
    @Column(name = "shortLink", nullable = true, length = -1)
    private String shortLink;
    @ManyToOne
    @JoinColumn(name = "idOrganisation", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private OrganisationEntity organisation;
    @OneToMany(mappedBy = "board")
    @JsonIgnore
    private List<BoardInvitationEntity> boardinvitations;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonIgnore
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<MembershipEntity> memberships;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonIgnore
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<TrelloListEntity> trellolists;
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
