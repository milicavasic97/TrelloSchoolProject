package unibl.etf.pisio.trelloproject.core.services;

import unibl.etf.pisio.trelloproject.api.models.requests.CardInsertRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.CardUpdateRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.TrelloListRequest;
import unibl.etf.pisio.trelloproject.core.base.ICrudService;
import unibl.etf.pisio.trelloproject.core.models.dto.CardDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.TrelloListDTO;

import java.util.List;

public interface ICardService extends ICrudService<String> {
    List<CardDTO> getAllByTrelloListId(String id);

    void deleteCardsByTrelloListIdRestrict(String id);

    CardDTO insert(CardInsertRequest request);

    void updateListPosition(String cardId, Integer position);

    CardDTO update(String id, CardUpdateRequest request);


}
