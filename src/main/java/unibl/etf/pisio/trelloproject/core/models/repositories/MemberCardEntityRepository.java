package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.models.entities.MemberCardEntity;

import java.util.List;

public interface MemberCardEntityRepository extends JpaRepository<MemberCardEntity, String> {

    List<MemberCardEntity> getAllByCard_Id(String id);
    void deleteAllByCard_Id(String id);

    MemberCardEntity findByMember_IdAndCard_Id(String idMember, String idCard);

    boolean existsByMember_IdAndCard_Id(String idMember, String idCard);
}
