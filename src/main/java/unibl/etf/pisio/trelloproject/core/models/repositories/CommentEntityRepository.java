package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.models.entities.CommentEntity;

import java.util.List;

public interface CommentEntityRepository extends JpaRepository<CommentEntity, String> {

    void deleteAllByCard_Id(String id);

    List<CommentEntity> findAllByCard_Id(String idCard);
}
