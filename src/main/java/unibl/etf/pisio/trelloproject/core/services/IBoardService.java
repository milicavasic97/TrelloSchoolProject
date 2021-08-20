package unibl.etf.pisio.trelloproject.core.services;

import unibl.etf.pisio.trelloproject.core.base.ICrudService;
import unibl.etf.pisio.trelloproject.core.models.dto.BoardDTO;

import java.util.List;

public interface IBoardService extends ICrudService<String> {
    List<BoardDTO> getAllBoardsByOrganisationId(String id);
}
