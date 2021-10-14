package unibl.etf.pisio.trelloproject.core.services;

import unibl.etf.pisio.trelloproject.core.base.ICrudService;
import unibl.etf.pisio.trelloproject.core.models.dto.CommentDTO;

import java.util.List;

public interface ICommentService extends ICrudService<String> {

    void deleteAllByCardId(String id);

    List<CommentDTO> findAllByCardId(String idCard);
}
