package unibl.etf.pisio.trelloproject.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unibl.etf.pisio.trelloproject.core.services.ITrelloListService;

@RestController
@RequestMapping("/lists")
public class TrelloListController {

    private final ITrelloListService trelloListService;

    public TrelloListController(ITrelloListService _trelloListService){
        trelloListService = _trelloListService;
    }
}
