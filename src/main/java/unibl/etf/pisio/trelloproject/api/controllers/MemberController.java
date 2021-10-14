package unibl.etf.pisio.trelloproject.api.controllers;


import org.springframework.web.bind.annotation.*;
import unibl.etf.pisio.trelloproject.api.models.requests.MemberRequest;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationInvitationDTO;
import unibl.etf.pisio.trelloproject.core.services.IMemberService;
import unibl.etf.pisio.trelloproject.core.services.IMembershipService;
import unibl.etf.pisio.trelloproject.core.services.IOrganisationInvitationService;
import unibl.etf.pisio.trelloproject.core.services.IOrganisationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final IMemberService memberService;
    private final IMembershipService membershipService;
    private final IOrganisationService organisationService;
    private final IOrganisationInvitationService organisationInvitationService;

    public MemberController(IMemberService _memberService, IMembershipService _membershipService,
                            IOrganisationService _organisationService,
                            IOrganisationInvitationService organisationInvitationService) {
        memberService = _memberService;
        membershipService = _membershipService;
        organisationService = _organisationService;
        this.organisationInvitationService = organisationInvitationService;
    }

    @GetMapping
    public List<MemberDTO> getAll() {
        return memberService.findAll(MemberDTO.class);
    }

    @GetMapping("/{id}/organisations")
    public List<OrganisationDTO> getAllOrganisationsForMember(@PathVariable String id) {
//        List<OrganisationDTO> organisations1 = membershipService.getAllOrganisationsByMemberId(id);
        List<OrganisationDTO> organisations2 = organisationService.getMembersEmptyOrganisations(id);
        List<OrganisationDTO> organisations3 = organisationInvitationService.getAllAcceptedOrganisations(id);
//        organisations2.addAll(organisations1);
        organisations2.addAll(organisations3);
        organisations2.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
        return organisations2;
    }

    @GetMapping("/{id}/organizationsInvited")
    public List<OrganisationInvitationDTO> getAllInvitations(@PathVariable String id) {
        return organisationInvitationService.getAllInvitationsByMemberId(id);
    }

    @PutMapping("/{id}")
    public MemberDTO update(@PathVariable String id, @RequestBody @Valid MemberRequest memberRequest) {
        return memberService.update(id, memberRequest, MemberDTO.class);
    }

    @PutMapping("/invitations/{id}/accept")
    public void acceptInvitation(@PathVariable String id) {
        organisationInvitationService.acceptInvitation(id);
    }

    @PutMapping("/invitations/{id}/reject")
    public void rejectInvitation( @PathVariable String id) {
        organisationInvitationService.rejectInvitation(id);
    }

}
