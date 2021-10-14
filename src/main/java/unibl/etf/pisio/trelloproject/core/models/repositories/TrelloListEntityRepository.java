package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.models.entities.TrelloListEntity;

import java.util.List;

public interface TrelloListEntityRepository extends JpaRepository<TrelloListEntity, String> {
    List<TrelloListEntity> getAllByBoard_Id(String id);
}
