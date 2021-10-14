package unibl.etf.pisio.trelloproject.core.services;

import unibl.etf.pisio.trelloproject.api.models.requests.ChangeMemberTypeRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.ChangeStatusRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.MemberUpdateRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.SignUpRequest;
import unibl.etf.pisio.trelloproject.core.base.ICrudService;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberDTO;

public interface IMemberService extends ICrudService<String> {

    void signUp(SignUpRequest signUpRequest);

    void changeStatus(String id, ChangeStatusRequest changeStatusRequest);

    void changeMemberType(String id, ChangeMemberTypeRequest changeMemberTypeRequest);

    MemberDTO update(String id, MemberUpdateRequest memberUpdateRequest);

    MemberDTO findByEmail(String email);
}
