package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;
import unibl.etf.pisio.trelloproject.core.models.enums.MemberType;

import javax.validation.constraints.NotNull;

@Data
public class ChangeMemberTypeRequest {
    @NotNull
    private MemberType memberType;
}
