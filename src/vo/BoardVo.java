package vo;

import lombok.Data;

@Data
public class BoardVo {

    private int no;
    private String title;
    private String content;
    private String writer;
    private String w_date;
    private String delyn;

}
