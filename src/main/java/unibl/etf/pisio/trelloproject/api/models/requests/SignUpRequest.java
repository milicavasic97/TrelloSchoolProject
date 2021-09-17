package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String fullName;
    private String bio;
    private String initials;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
