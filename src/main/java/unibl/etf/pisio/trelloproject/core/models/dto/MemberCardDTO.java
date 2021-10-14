package unibl.etf.pisio.trelloproject.core.models.dto;

import lombok.Data;

@Data
public class MemberCardDTO {
    private String id;
    private String idMember;
    private String idCard;
    private MemberDTO member;
    private CardDTO card;
}
