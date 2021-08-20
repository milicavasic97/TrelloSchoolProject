package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.models.entities.BoardEntity;

import java.util.List;

public interface BoardEntityRepository extends JpaRepository<BoardEntity, String> {
    List<BoardEntity> getAllByOrganisation_Id(String id);

}
