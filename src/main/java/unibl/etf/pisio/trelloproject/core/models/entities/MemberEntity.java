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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class MemberEntity implements BaseEntity<String> {
    @Id
    @Column(name = "id", nullable = false, length = 24)
    private String id;
    @Basic
    @Column(name = "username", nullable = false, length = 512)
    private String username;
    @Basic
    @Column(name = "fullName", nullable = false, length = -1)
    private String fullName;
    @Basic
    @Column(name = "avatarHash", nullable = true, length = -1)
    private String avatarHash;
    @Basic
    @Column(name = "avatarSource", nullable = true, length = -1)
    private String avatarSource;
    @Basic
    @Column(name = "bio", nullable = true, length = -1)
    private String bio;
    @Basic
    @Column(name = "initials", nullable = false, length = 4)
    private String initials;
    @Basic
    @Column(name = "memberType", nullable = false, length = -1)
    private String memberType;
    @Basic
    @Column(name = "status", nullable = true, length = -1)
    private String status;
    @Basic
    @Column(name = "url", nullable = true, length = -1)
    private String url;
    @Basic
    @Column(name = "confirmed", nullable = false)
    private Boolean confirmed;
    @Basic
    @Column(name = "email", nullable = false, length = -1)
    private String email;
    @Basic
    @Column(name = "password", nullable = true)
    private String password;
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<BoardInvitationEntity> boardinvitations;
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<CommentEntity> comments;
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<MemberCardEntity> membercards;
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<MembershipEntity> memberships;
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<OrganisationInvitationEntity> organisationinvitations;

}
