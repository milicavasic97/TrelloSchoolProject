package unibl.etf.pisio.trelloproject.core.services;

import unibl.etf.pisio.trelloproject.core.base.ICrudService;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberCardDTO;
import unibl.etf.pisio.trelloproject.core.models.entities.MemberCardEntity;

import java.util.List;

public interface IMemberCardService extends ICrudService<String> {

    List<MemberCardEntity> getAllByCardId(String id);

    void deleteAllByCardId(String id);

    MemberCardDTO findByMemberIdAndCardId(String idMember, String idCard);

    boolean existsByMemberIdAndCardId(String idMember, String idCard);
}
