package unibl.etf.pisio.trelloproject.core.models.entities;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import unibl.etf.pisio.trelloproject.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "member")
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
    @Column(name = "url", nullable = false, length = -1)
    private String url;
    @Basic
    @Column(name = "confirmed", nullable = false)
    private Boolean confirmed;
    @Basic
    @Column(name = "email", nullable = false, length = -1)
    private String email;
    @Basic
    @Column(name = "password", nullable = true)
    private byte[] password;
    @Basic
    @Column(name = "salt", nullable = true)
    private byte[] salt;
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
