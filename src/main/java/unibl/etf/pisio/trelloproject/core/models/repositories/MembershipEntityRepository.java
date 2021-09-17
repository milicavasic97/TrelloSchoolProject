package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.models.entities.MembershipEntity;

import java.util.List;

public interface MembershipEntityRepository extends JpaRepository<MembershipEntity, String> {

    List<MembershipEntity> getAllByOrganisation_Id(String id);

    List<MembershipEntity> getAllByMember_Id(String id);
}
