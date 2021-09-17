package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;

@Data
public class MemberRequest {
    private String id;
    private String username;
    private String fullName;
    private String avatarHash;
    private String avatarSource;
    private String bio;
    private String initials;
    private String memberType;
    private String status;
    private String url;
    private Boolean confirmed;
    private String email;
}
