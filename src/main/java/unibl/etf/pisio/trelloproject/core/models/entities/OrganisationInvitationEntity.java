package unibl.etf.pisio.trelloproject.core.models.entities;

import lombok.*;
import unibl.etf.pisio.trelloproject.core.base.BaseEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "organisationinvitation")
public class OrganisationInvitationEntity implements BaseEntity<String> {
    @Id
    @Column(name = "id", nullable = false, length = 24)
    private String id;
    @ManyToOne
    @JoinColumn(name = "idMember", referencedColumnName = "id", nullable = false)
    private MemberEntity member;
    @ManyToOne
    @JoinColumn(name = "idOrganisation", referencedColumnName = "id", nullable = false)
    private OrganisationEntity organisation;
    @Column(name ="accepted")
    private Boolean accepted;

}
