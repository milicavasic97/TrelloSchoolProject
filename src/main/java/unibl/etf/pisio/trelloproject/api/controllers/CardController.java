package unibl.etf.pisio.trelloproject.api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import unibl.etf.pisio.trelloproject.api.models.requests.CardInsertRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.CardUpdateRequest;
import unibl.etf.pisio.trelloproject.api.models.requests.CommentRequest;
import unibl.etf.pisio.trelloproject.core.exceptions.ConflictException;
import unibl.etf.pisio.trelloproject.core.exceptions.NotFoundException;
import unibl.etf.pisio.trelloproject.core.models.dto.*;
import unibl.etf.pisio.trelloproject.core.services.ICardService;
import unibl.etf.pisio.trelloproject.core.services.ICommentService;
import unibl.etf.pisio.trelloproject.core.services.IMemberCardService;
import unibl.etf.pisio.trelloproject.core.services.IMemberService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final ICardService cardService;
    private final ModelMapper modelMapper;
    private final IMemberCardService memberCardService;
    private final IMemberService memberService;
    private final ICommentService commentService;

    public CardController(ICardService cardService, ModelMapper modelMapper,
                          IMemberCardService memberCardService, IMemberService memberService, ICommentService commentService) {
        this.cardService = cardService;
        this.modelMapper = modelMapper;
        this.memberCardService = memberCardService;
        this.memberService = memberService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public CardDTO getOne(@PathVariable String id) {
        return cardService.findById(id, CardDTO.class);
    }

    @GetMapping("/{id}/members")
    public List<MemberDTO> getMembers(@PathVariable String id) {
        return memberCardService.getAllByCardId(id)
                .stream()
                .map(o -> modelMapper.map(o.getMember(), MemberDTO.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}/comments")
    public List<CommentDTO> getComments(@PathVariable String id) {
        List<CommentDTO> commentDTOS = commentService.findAllByCardId(id);
        commentDTOS.sort(Comparator.comparing(CommentDTO::getDate));
        return commentService.findAllByCardId(id);
    }

    @PostMapping("/{id}/idMembers")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberCardDTO addMember(@PathVariable String id, @RequestParam String email) {
        MemberDTO entity = memberService.findByEmail(email);
        if(memberCardService.existsByMemberIdAndCardId(entity.getId(), id))
            throw new ConflictException();
        MemberCardInsertDTO dto = new MemberCardInsertDTO();
        dto.setIdCard(id);
        dto.setIdMember(entity.getId());
        return memberCardService.insert(dto, MemberCardDTO.class);
    }

    @DeleteMapping("/{id}/idMembers/{idMember}")
    public void removeMember(@PathVariable String id, @PathVariable String idMember) {
        MemberCardDTO dto = memberCardService.findByMemberIdAndCardId(idMember, id);
        if (dto != null) {
            memberCardService.delete(dto.getId());
        }
        else
            throw new NotFoundException();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTO insertCard(@RequestBody @Valid CardInsertRequest request) {
        return cardService.insert(request);
    }

    @PutMapping("/{id}")
    public CardDTO updateCard(@PathVariable String id, @RequestBody @Valid CardUpdateRequest request) {
        return cardService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable String id) {
        cardService.delete(id);
    }

    @PostMapping("/{id}/actions/comments")
    public CommentDTO addComment(@PathVariable String id, @RequestBody CommentRequest request) {
        request.setIdCard(id);
        request.setDate(java.sql.Date.valueOf(LocalDate.now()));
        return commentService.insert(request, CommentDTO.class);
    }

    @DeleteMapping("/{id}/actions/{idAction}/comments")
    public void deleteComment(@PathVariable String id, @PathVariable String idAction){
        commentService.delete(idAction);
    }



}
