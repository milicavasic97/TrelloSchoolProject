package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberUpdateRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String fullName;
    private String bio;
    @NotBlank
    private String initials;
    @NotBlank
    private String memberType;
    private String status;
    @NotBlank
    private String url;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
