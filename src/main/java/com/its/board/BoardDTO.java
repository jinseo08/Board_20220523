package com.its.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    Long b_id;
    String boardTitle;
    String boardWriter;
    String boardPassword;
    String boardContents;
    int boardHits;
    String boardCreatedDate;

}
