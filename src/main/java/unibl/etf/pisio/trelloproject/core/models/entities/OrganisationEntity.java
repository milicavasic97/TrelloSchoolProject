package unibl.etf.pisio.trelloproject.core.models.entities;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import unibl.etf.pisio.trelloproject.core.base.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "organisation")
public class OrganisationEntity implements BaseEntity<String> {
    @Id
    @Column(name = "id", nullable = false, length = 24)
    private String id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "displayName", nullable = false, length = -1)
    private String displayName;
    @Basic
    @Column(name = "desc", nullable = true, length = -1)
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

}
