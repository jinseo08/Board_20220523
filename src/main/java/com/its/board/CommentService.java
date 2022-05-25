package com.its.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    public void save(CommentDTO commentDTO) {
        commentRepository.save(commentDTO);

    }

    public List<CommentDTO> findAll(Long boardId) {
        return commentRepository.findAll(boardId);
    }
}
