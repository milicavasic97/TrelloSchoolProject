package unibl.etf.pisio.trelloproject.core.services;

import unibl.etf.pisio.trelloproject.api.models.requests.TrelloListRequest;
import unibl.etf.pisio.trelloproject.core.base.ICrudService;
import unibl.etf.pisio.trelloproject.core.models.dto.TrelloListDTO;

import java.util.List;

public interface ITrelloListService extends ICrudService<String> {
    List<TrelloListDTO> getAllListsByBoardId(String boardId);

    void deleteCardsAndList(String id);

    TrelloListDTO insert(TrelloListRequest request);

    void  updateListPosition(String listId, Integer position);
}
