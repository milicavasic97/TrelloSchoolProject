package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.api.models.requests.ChangeMemberTypeRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.ChangeStatusRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.MemberUpdateRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.SignUpRequest;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.exceptions.ConflictException;
import unibl.etf.pisio.trelloproject.core.exceptions.ForbiddenException;
import unibl.etf.pisio.trelloproject.core.exceptions.NotFoundException;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberDTO;
import unibl.etf.pisio.trelloproject.core.models.entities.MemberEntity;
import unibl.etf.pisio.trelloproject.core.models.enums.MemberType;
import unibl.etf.pisio.trelloproject.core.models.enums.UserStatus;
import unibl.etf.pisio.trelloproject.core.models.repositories.MemberEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IMemberService;
import unibl.etf.pisio.trelloproject.core.util.IdGeneratorUtil;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
@Transactional
public class MemberService extends CrudJpaService<MemberEntity, String> implements IMemberService {
    private final ModelMapper modelMapper;
    private final MemberEntityRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Value("${authorization.default.username:}")
    private String defaultUsername;
    @Value("${authorization.default.full-name:}")
    private String defaultFullName;
    @Value("${authorization.default.initials:}")
    private String defaultInitials;
    @Value("${authorization.default.member-type:}")
    private String defaultMemberType;
    @Value("${authorization.default.url:}")
    private String defaultUrl;
    @Value("${authorization.default.password:}")
    private String defaultPassword;
    @Value("${authorization.default.email:}")
    private String defaultEmail;

    public MemberService(ModelMapper modelMapper,
                         MemberEntityRepository repository, PasswordEncoder passwordEncoder) {
        super(repository, modelMapper, MemberEntity.class);
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void postConstruct() {
        if (repository.count() == 0) {
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setId(IdGeneratorUtil.generateId());
            memberEntity.setUsername(defaultUsername);
            memberEntity.setPassword(passwordEncoder.encode(defaultPassword));
            memberEntity.setFullName(defaultFullName);
            memberEntity.setEmail(defaultEmail);
            memberEntity.setMemberType(defaultMemberType);
            memberEntity.setConfirmed(true);
            memberEntity.setInitials(defaultInitials);
            memberEntity.setUrl(defaultUrl);
            memberEntity.setStatus(UserStatus.ACTIVE.name());
            repository.saveAndFlush(memberEntity);
        }
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        if (repository.existsByUsername(signUpRequest.getUsername()))
            throw new ConflictException("Username exists");
        if (repository.existsByEmail(signUpRequest.getEmail()))
            throw new ConflictException("Email exists");
        MemberEntity entity = modelMapper.map(signUpRequest, MemberEntity.class);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setStatus(UserStatus.ACTIVE.name());
        entity.setConfirmed(true);
        entity.setMemberType(MemberType.NORMAL.name());
        SignUpRequest member = insert(entity, SignUpRequest.class);
    }

    @Override
    public void changeStatus(String id, ChangeStatusRequest changeStatusRequest) {
        if (UserStatus.REQUESTED.equals(changeStatusRequest.getStatus()))
            throw new ForbiddenException();
        MemberEntity entity = findEntityById(id);
        if (entity == null)
            throw new NotFoundException();
        entity.setStatus(changeStatusRequest.getStatus().name());
        repository.saveAndFlush(entity);
    }

    @Override
    public void changeMemberType(String id, ChangeMemberTypeRequest changeMemberTypeRequest) {
        MemberEntity entity = findEntityById(id);
        if (entity == null)
            throw new NotFoundException();
        entity.setMemberType(changeMemberTypeRequest.getMemberType().name());
    }

    @Override
    public MemberDTO update(String id, MemberUpdateRequest memberUpdateRequest) {
        if (repository.existsByUsernameAndIdNot(memberUpdateRequest.getUsername(), id))
            throw new ConflictException();
        MemberEntity entity = findEntityById(id);
        entity.setUsername(memberUpdateRequest.getUsername());
        entity.setEmail(memberUpdateRequest.getEmail());
        entity.setFullName(memberUpdateRequest.getFullName());
        entity.setBio(memberUpdateRequest.getBio());
        entity.setInitials(memberUpdateRequest.getInitials());
        return update(id, entity, MemberDTO.class);
    }

    @Override
    public MemberDTO findByEmail(String email) {
        return modelMapper.map(repository.findByEmail(email), MemberDTO.class);
    }
}
