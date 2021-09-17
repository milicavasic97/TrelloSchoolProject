package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
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

    private final JmsTemplate jmsTemplate;
//    @Value("${mq.topic}")
//    private String topicName;
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
//    @Value("${mq.queue}")
//    private String queueName;

    public MemberService(MemberEntityRepository _repository, ModelMapper _mapper,
                         PasswordEncoder _encoder, JmsTemplate _jmsTemplate) {
        super(_repository, _mapper, MemberEntity.class);
        modelMapper = _mapper;
        repository = _repository;
        passwordEncoder = _encoder;
        jmsTemplate = _jmsTemplate;
    }

    @PostConstruct
    public void postConstruct() {
        if(repository.count()== 0){
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
            throw new ConflictException();
        MemberEntity entity = getModelMapper().map(signUpRequest, MemberEntity.class);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setStatus(UserStatus.REQUESTED.name());
        entity.setMemberType(MemberType.NORMAL.name());
        MemberDTO user = insert(entity, MemberDTO.class);
//        jmsTemplate.convertAndSend(new ActiveMQQueue(queueName), user);
//        jmsTemplate.convertAndSend(new ActiveMQTopic(topicName), user);
    }

    @Override
    public void changeStatus(String id, ChangeStatusRequest changeStatusRequest) {
        if (UserStatus.REQUESTED.equals(changeStatusRequest.getStatus()))
            throw new ForbiddenException();
        MemberEntity entity = findEntityById(id);
        if(entity == null)
            throw new NotFoundException();
        entity.setStatus(changeStatusRequest.getStatus().name());
        repository.saveAndFlush(entity);
    }

    @Override
    public void changeMemberType(String id, ChangeMemberTypeRequest changeMemberTypeRequest) {
        MemberEntity entity = findEntityById(id);
        if(entity == null)
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
}
