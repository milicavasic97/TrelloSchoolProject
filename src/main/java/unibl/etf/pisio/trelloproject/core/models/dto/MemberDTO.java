package unibl.etf.pisio.trelloproject.core.models.dto;

import javax.persistence.Basic;
import javax.persistence.Column;

public class MemberDTO {
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
    private byte[] password;
    private byte[] salt;
}
