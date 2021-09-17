package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.models.entities.CommentEntity;

public interface CommentEntityRepository extends JpaRepository<CommentEntity, String> {
}
