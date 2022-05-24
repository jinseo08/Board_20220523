package com.its.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long b_id;
    private String boardTitle;
    private String boardWriter;
    private String boardPassword;
    private String boardContents;
    private int boardHits;
    private String boardCreatedDate;
    private MultipartFile boardFile; // saveFile.jsp 에서 컨트롤러로 넘어올 때 파일을 담아오는 용도
    private String boardFileName; // 상세조회 등을 할 때 파일 이름을 담을 용도

}
