package unibl.etf.pisio.trelloproject.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import unibl.etf.pisio.trelloproject.api.models.requests.BoardRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.MembershipInsertRequest;
import unibl.etf.pisio.trelloproject.core.models.dto.BoardDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.JwtUserDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.MembershipDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.TrelloListDTO;
import unibl.etf.pisio.trelloproject.core.services.IBoardService;
import unibl.etf.pisio.trelloproject.core.services.IMembershipService;
import unibl.etf.pisio.trelloproject.core.services.ITrelloListService;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping("/boards")
public class BoardController {

    private final IBoardService boardService;
    private final ITrelloListService trelloListService;
    private final IMembershipService membershipService;

    public BoardController(IBoardService _boardService, ITrelloListService _trelloListService,
                           IMembershipService _membershipService) {
        boardService = _boardService;
        trelloListService = _trelloListService;
        membershipService = _membershipService;
    }

    @GetMapping("/{id}")
    public BoardDTO getById(@PathVariable String id) {
        return boardService.findById(id, BoardDTO.class);
    }

    @GetMapping("/{id}/lists")
    public List<TrelloListDTO> getLists(@PathVariable String id) {
        List<TrelloListDTO> list = trelloListService.getAllListsByBoardId(id);
        list.sort(Comparator.comparing(TrelloListDTO::getPos));
        return list;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDTO insert(@RequestBody @Valid BoardRequest request) {
        BoardDTO boardDTO = boardService.insert(request);

        return boardDTO;
    }

    @PutMapping("/{id}")
    public BoardDTO update(@PathVariable String id, @RequestBody @Valid BoardRequest request) {
        return boardService.update(id, request, BoardDTO.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
//        boardService.deleteBoard(id);
        boardService.delete(id);
    }
}
