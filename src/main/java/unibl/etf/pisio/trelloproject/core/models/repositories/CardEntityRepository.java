package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.models.entities.CardEntity;

import java.util.List;

public interface CardEntityRepository extends JpaRepository<CardEntity, String> {

    List<CardEntity> getAllByTrellolist_Id(String id);

    boolean existsByPosAndTrellolist_Id(Integer pos, String listId);

}
