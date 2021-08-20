package unibl.etf.pisio.trelloproject.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unibl.etf.pisio.trelloproject.api.base.CrudController;
import unibl.etf.pisio.trelloproject.api.models.requests.BoardRequest;
import unibl.etf.pisio.trelloproject.core.models.dto.BoardDTO;
import unibl.etf.pisio.trelloproject.core.services.IBoardService;


@RestController
@RequestMapping("${APIv}/boards")
public class BoardController extends CrudController<String, BoardRequest, BoardDTO> {

    public BoardController(IBoardService _boardService){
        super(_boardService, BoardDTO.class);
    }
}
