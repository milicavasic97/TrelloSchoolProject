package unibl.etf.pisio.trelloproject.core.services.implementations;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.api.models.requests.LoginRequest;
import unibl.etf.pisio.trelloproject.core.exceptions.UnauthorizedException;
import unibl.etf.pisio.trelloproject.core.models.dto.JwtUserDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.LoginResponseDTO;
import unibl.etf.pisio.trelloproject.core.services.IAuthService;
import unibl.etf.pisio.trelloproject.core.services.IMemberService;
import unibl.etf.pisio.trelloproject.core.util.LoggingUtil;

import java.util.Date;

@Service
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final IMemberService memberService;
    @Value("${authorization.token.expiration-time}")
    private String tokenExpirationTime;
    @Value("${authorization.token.secret}")
    private String tokenSecret;

    public AuthService(AuthenticationManager _authManager, IMemberService _memberService) {
        authenticationManager = _authManager;
        memberService = _memberService;
    }

    @Override
    public LoginResponseDTO login(LoginRequest loginRequest) {
        LoginResponseDTO response = null;
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.getUsername(), loginRequest.getPassword()
                            )
                    );
            JwtUserDTO jwtUserDTO = (JwtUserDTO) authenticate.getPrincipal();
            response = memberService.findById(jwtUserDTO.getId(), LoginResponseDTO.class);
            response.setToken(generateJwt(jwtUserDTO));
        } catch (Exception ex){
            LoggingUtil.logException(ex, getClass());
            throw new UnauthorizedException();
        }
        return response;
    }

    private String generateJwt(JwtUserDTO jwtUser) {
        return Jwts.builder()
                .setId(jwtUser.getId())
                .setSubject(jwtUser.getUsername())
                .claim("role", jwtUser.getMemberType().name())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(tokenExpirationTime)))
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }
}
