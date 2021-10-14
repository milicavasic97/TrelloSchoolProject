package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.core.models.dto.JwtUserDTO;
import unibl.etf.pisio.trelloproject.core.models.enums.UserStatus;
import unibl.etf.pisio.trelloproject.core.models.repositories.MemberEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IJwtUserDetailsService;

@Service
public class JwtUserDetailsService implements IJwtUserDetailsService {

    private final MemberEntityRepository memberEntityRepository;
    private final ModelMapper modelMapper;

    public JwtUserDetailsService(MemberEntityRepository _memberEntityRepository, ModelMapper _modelMapper) {
        memberEntityRepository = _memberEntityRepository;
        modelMapper = _modelMapper;
    }

    @Override
    public JwtUserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        return modelMapper.map(
                memberEntityRepository.findByUsernameAndStatus(username, UserStatus.ACTIVE.name()).
                        orElseThrow(() -> new UsernameNotFoundException(username))
                , JwtUserDTO.class
        );
    }
}
