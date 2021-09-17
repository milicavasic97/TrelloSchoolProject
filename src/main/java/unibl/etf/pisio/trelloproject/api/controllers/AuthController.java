package unibl.etf.pisio.trelloproject.api.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import unibl.etf.pisio.trelloproject.api.models.requests.LoginRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.SignUpRequest;
import unibl.etf.pisio.trelloproject.core.models.dto.JwtUserDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.LoginResponseDTO;
import unibl.etf.pisio.trelloproject.core.services.IAuthService;
import unibl.etf.pisio.trelloproject.core.services.IMemberService;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final IAuthService authService;
    private final IMemberService memberService;

    public AuthController(IAuthService _authService, IMemberService _memberService) {
        authService = _authService;
        memberService = _memberService;
    }

    @PostMapping("login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("state")
    public LoginResponseDTO state(Authentication auth) {
        JwtUserDTO jwtUser = (JwtUserDTO) auth.getPrincipal();
        return memberService.findById(jwtUser.getId(), LoginResponseDTO.class);
    }

    @PostMapping("sign-up")
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        memberService.signUp(signUpRequest);
    }
}
