package unibl.etf.pisio.trelloproject.core.services;

import unibl.etf.pisio.trelloproject.api.models.requests.LoginRequest;
import unibl.etf.pisio.trelloproject.core.models.dto.LoginResponseDTO;

public interface IAuthService {
    LoginResponseDTO login(LoginRequest loginRequest);

}
