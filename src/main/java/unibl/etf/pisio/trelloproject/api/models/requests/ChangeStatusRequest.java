package unibl.etf.pisio.trelloproject.api.models.requests;

import lombok.Data;
import unibl.etf.pisio.trelloproject.core.models.enums.UserStatus;

import javax.validation.constraints.NotNull;

@Data
public class ChangeStatusRequest {
    @NotNull
    private UserStatus status;
}
