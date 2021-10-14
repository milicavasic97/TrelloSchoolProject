package unibl.etf.pisio.trelloproject.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import unibl.etf.pisio.trelloproject.api.models.requests.OrganisationInvitationRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.OrganisationRequest;
import unibl.etf.pisio.trelloproject.core.models.dto.BoardDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationDTO;
import unibl.etf.pisio.trelloproject.core.services.IBoardService;
import unibl.etf.pisio.trelloproject.core.services.IMembershipService;
import unibl.etf.pisio.trelloproject.core.services.IOrganisationInvitationService;
import unibl.etf.pisio.trelloproject.core.services.IOrganisationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organisations")
public class OrganisationController {

    private final IOrganisationService organisationService;
    private final IBoardService boardService;
    private final IMembershipService membershipService;
    private final IOrganisationInvitationService organisationInvitationService;

    public OrganisationController(IOrganisationService organisationService, IBoardService boardService,
                                  IMembershipService membershipService,
                                  IOrganisationInvitationService organisationInvitationService) {
        this.organisationService = organisationService;
        this.boardService = boardService;
        this.membershipService = membershipService;
        this.organisationInvitationService = organisationInvitationService;
    }


    @GetMapping
    public List<OrganisationDTO> getAll(){
//        JwtUserDTO userDTO = (JwtUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return organisationService.findAll(OrganisationDTO.class);
    }

    @GetMapping("/{id}")
    public OrganisationDTO getOne(@PathVariable String id){
        return organisationService.findById(id, OrganisationDTO.class);
    }

    @GetMapping("/{id}/boards")
    public List<BoardDTO> getAllBoardsForOrganisation(@PathVariable String id) {
//        return boardService.getAllBoardsByOrganisationId(id);
        return organisationService.findById(id, OrganisationDTO.class).getBoards();
    }

    @GetMapping("/{id}/members")
    public List<MemberDTO> getAllMembersForOrganisation(@PathVariable String id) {
        return membershipService.getAllMembersByOrganisationId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganisationDTO insert(@RequestBody @Valid OrganisationRequest organisationRequest) {
        return organisationService.insert(organisationRequest);
    }

    @PutMapping("/{id}/members")
    public void inviteMember(@PathVariable String id, @RequestBody OrganisationInvitationRequest request) {
        organisationInvitationService.insert(id,request);
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
