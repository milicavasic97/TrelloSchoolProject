package unibl.etf.pisio.trelloproject.api.controllers;


import org.springframework.web.bind.annotation.*;
import unibl.etf.pisio.trelloproject.api.base.CrudController;
import unibl.etf.pisio.trelloproject.api.models.requests.MemberRequest;
import unibl.etf.pisio.trelloproject.core.base.ICrudService;
import unibl.etf.pisio.trelloproject.core.models.dto.MemberDTO;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationDTO;
import unibl.etf.pisio.trelloproject.core.services.IMemberService;
import unibl.etf.pisio.trelloproject.core.services.IMembershipService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final IMemberService memberService;
    private final IMembershipService membershipService;

    public MemberController(IMemberService _memberService, IMembershipService _membershipService) {
        memberService = _memberService;
        membershipService = _membershipService;
    }

    @GetMapping("/{id}/organisations")
    public List<OrganisationDTO> getAllOrganisationsForMember(@PathVariable String id) {
        return membershipService.getAllOrganisationsByMemberId(id);
    }

    @PutMapping("/{id}")
    public MemberDTO update(@PathVariable String id, @RequestBody @Valid MemberRequest memberRequest) {
        return memberService.update(id, memberRequest, MemberDTO.class);
    }


}
