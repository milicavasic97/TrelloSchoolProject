package unibl.etf.pisio.trelloproject.core.models.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;

@Data
public class MembershipDTO {
    private String id;
    private String membershipType;
    private String idOrganisation;
    private String idBoard;
    private String idMember;
}
