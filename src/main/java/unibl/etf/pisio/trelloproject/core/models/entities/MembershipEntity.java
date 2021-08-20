package unibl.etf.pisio.trelloproject.core.models.entities;

import lombok.*;
import unibl.etf.pisio.trelloproject.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "membership")
public class MembershipEntity implements BaseEntity<String> {
    @Id
    @Column(name = "id", nullable = false, length = 24)
    private String id;
    @Basic
    @Column(name = "membershipType", nullable = false, length = -1)
    private String membershipType;
    @ManyToOne
    @JoinColumn(name = "idMember", referencedColumnName = "id", nullable = false)
    private MemberEntity member;
    @ManyToOne
    @JoinColumn(name = "idBoard", referencedColumnName = "id", nullable = false)
    private BoardEntity board;
    @ManyToOne
    @JoinColumn(name = "idOrganisation", referencedColumnName = "id", nullable = false)
    private OrganisationEntity organisation;

}
