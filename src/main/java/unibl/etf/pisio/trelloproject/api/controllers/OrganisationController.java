package unibl.etf.pisio.trelloproject.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import unibl.etf.pisio.trelloproject.api.models.requests.OrganisationRequest;
import unibl.etf.pisio.trelloproject.core.models.dto.BoardDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationDTO;
import unibl.etf.pisio.trelloproject.core.services.IBoardService;
import unibl.etf.pisio.trelloproject.core.services.IMembershipService;
import unibl.etf.pisio.trelloproject.core.services.IOrganisationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organisations")
public class OrganisationController {

    private final IOrganisationService organisationService;
    private final IBoardService boardService;
    private final IMembershipService membershipService;

    public OrganisationController(IOrganisationService _service, IBoardService _boardService,
                                  IMembershipService _membershipService) {
        organisationService = _service;
        boardService = _boardService;
        membershipService = _membershipService;
    }

    @GetMapping
    public List<OrganisationDTO> getAll(){
        return organisationService.findAll(OrganisationDTO.class);
    }

    @GetMapping("/{id}/boards")
    public List<BoardDTO> getAllBoardsForOrganisation(@PathVariable String id) {
        return boardService.getAllBoardsByOrganisationId(id);
    }

    @GetMapping("/{id}/members")
    public List<MemberDTO> getAllMembersForOrganisation(@PathVariable String id) {
        return membershipService.getAllMembersByOrganisationId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganisationDTO insert(@RequestBody @Valid OrganisationRequest organisationRequest) {
        return organisationService.insert(organisationRequest, OrganisationDTO.class);
    }

    @PutMapping("/{id}")
    public OrganisationDTO update(@PathVariable String id, @RequestBody @Valid OrganisationRequest organisationRequest) {
        return organisationService.update(id, organisationRequest, OrganisationDTO.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        organisationService.delete(id);
    }
}
