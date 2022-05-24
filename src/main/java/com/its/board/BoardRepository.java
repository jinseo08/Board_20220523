package com.its.board;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {

    @Autowired
    private SqlSessionTemplate sql;

    public int saveBoard(BoardDTO boardDTO) {
        return sql.insert("Board.save",boardDTO);
    }


    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    public BoardDTO findById(Long b_id) {
        sql.update("Board.hits",b_id);
        return sql.selectOne("Board.findById",b_id);
    }

    public int delete(Long b_id) {
        return sql.delete("Board.delete",b_id);
    }

    public int update(BoardDTO boardDTO) {
        return sql.update("Board.update",boardDTO);
    }
}
