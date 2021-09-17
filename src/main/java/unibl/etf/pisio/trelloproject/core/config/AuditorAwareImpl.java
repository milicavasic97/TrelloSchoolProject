package unibl.etf.pisio.trelloproject.core.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import unibl.etf.pisio.trelloproject.core.models.dto.JwtUserDTO;
import unibl.etf.pisio.trelloproject.core.models.entities.MemberEntity;
import unibl.etf.pisio.trelloproject.core.services.IMemberService;
import unibl.etf.pisio.trelloproject.core.services.implementations.MemberService;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<MemberEntity> {

    private final IMemberService memberService;

    public AuditorAwareImpl(IMemberService userService) {
        this.memberService = userService;
    }

    @Override
    public Optional<MemberEntity> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtUserDTO) {
            JwtUserDTO jwtUser = (JwtUserDTO) authentication.getPrincipal();
            return Optional.of(memberService.findById(jwtUser.getId(), MemberEntity.class));
        }
        return Optional.empty();
    }
}