package unibl.etf.pisio.trelloproject.api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import unibl.etf.pisio.trelloproject.api.models.requests.TrelloListRequest;
import unibl.etf.pisio.trelloproject.core.models.dto.CardDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.TrelloListDTO;
import unibl.etf.pisio.trelloproject.core.services.ICardService;
import unibl.etf.pisio.trelloproject.core.services.ITrelloListService;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class TrelloListController {

    private final ITrelloListService trelloListService;
    private final ICardService cardService;
    private final ModelMapper modelMapper;

    public TrelloListController(ITrelloListService trelloListService, ICardService cardService,
                                ModelMapper modelMapper) {
        this.trelloListService = trelloListService;
        this.cardService = cardService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{id}/cards")
    public List<CardDTO> getCards(@PathVariable String id) {
        List<CardDTO> cards = cardService.getAllByTrelloListId(id);
        cards.sort(Comparator.comparing(CardDTO::getPos));
        return cards;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrelloListDTO insert(@RequestBody @Valid TrelloListRequest request) {
        return trelloListService.insert(request);
    }

    @PutMapping("/{id}")
    public TrelloListDTO update(@PathVariable String id, @RequestBody @Valid TrelloListRequest request) {
        return trelloListService.update(id, request, TrelloListDTO.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
//        trelloListService.deleteCardsAndList(id);
        trelloListService.delete(id);
    }

}
