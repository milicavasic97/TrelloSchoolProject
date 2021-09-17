package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.models.entities.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MemberEntityRepository extends JpaRepository<MemberEntity, String> {

    Optional<MemberEntity> findByUsernameAndStatus(String username, String status);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, String id);
}
