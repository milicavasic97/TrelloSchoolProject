package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.models.entities.BoardInvitationEntity;

public interface BoardInvitationEntityRepository extends JpaRepository<BoardInvitationEntity, String> {
}
