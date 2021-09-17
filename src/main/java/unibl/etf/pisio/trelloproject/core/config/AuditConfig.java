package unibl.etf.pisio.trelloproject.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import unibl.etf.pisio.trelloproject.core.models.entities.MemberEntity;
import unibl.etf.pisio.trelloproject.core.services.IMemberService;
import unibl.etf.pisio.trelloproject.core.services.implementations.MemberService;

@Configuration
public class AuditConfig {

    private final IMemberService memberService;

    public AuditConfig(IMemberService userService) {
        this.memberService = userService;
    }

    @Bean
    AuditorAware<MemberEntity> auditorProvider() {
        return new AuditorAwareImpl(memberService);
    }
}
