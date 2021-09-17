package unibl.etf.pisio.trelloproject.core.models.entities;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import unibl.etf.pisio.trelloproject.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "organisation")
@EntityListeners(AuditingEntityListener.class)
public class OrganisationEntity implements BaseEntity<String> {
    @Id
    @Column(name = "id", nullable = false, length = 24)
    private String id;
    @Basic
    @Column(name = "organisationName", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "displayName", nullable = false, length = -1)
    private String displayName;
    @Basic
    @Column(name = "organisationDescription", nullable = true, length = -1)
    private String desc;
    @Basic
    @Column(name = "invited", nullable = true)
    private Boolean invited;
    @Basic
    @Column(name = "url", nullable = true, length = -1)
    private String url;
    @Basic
    @Column(name = "website", nullable = false, length = -1)
    private String website;
    @OneToMany(mappedBy = "organisation")
    @JsonIgnore
    private List<BoardEntity> boards;
    @OneToMany(mappedBy = "organisation")
    @JsonIgnore
    private List<MembershipEntity> memberships;
    @OneToMany(mappedBy = "organisation")
    @JsonIgnore
    private List<OrganisationInvitationEntity> organisationinvitations;
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
