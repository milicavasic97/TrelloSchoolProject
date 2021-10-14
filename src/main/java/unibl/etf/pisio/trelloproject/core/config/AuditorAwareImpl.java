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
        if (authentication != null && authentication.getPrincipal().toString() !="anonymousUser") {
            System.out.println();
            System.out.println(authentication.getPrincipal().toString());
            System.out.println();
            JwtUserDTO jwtUser = (JwtUserDTO) authentication.getPrincipal();
            return jwtUser == null ?
                    Optional.empty() :
                    Optional.of(memberService.findById(jwtUser.getId(), MemberEntity.class));
        }
        return Optional.empty();
    }
}