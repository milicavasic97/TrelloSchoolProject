package unibl.etf.pisio.trelloproject.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unibl.etf.pisio.trelloproject.api.base.CrudController;
import unibl.etf.pisio.trelloproject.api.models.requests.BoardRequest;
import unibl.etf.pisio.trelloproject.core.models.dto.BoardDTO;
import unibl.etf.pisio.trelloproject.core.services.IBoardService;


@RestController
@RequestMapping("/boards")
public class BoardController {

    private final IBoardService boardService;

    public BoardController(IBoardService _boardService) {
        boardService = _boardService;
    }


}
