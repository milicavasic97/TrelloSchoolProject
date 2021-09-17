package unibl.etf.pisio.trelloproject.core.models.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponseDTO extends MemberDTO {
    private String token;
}
