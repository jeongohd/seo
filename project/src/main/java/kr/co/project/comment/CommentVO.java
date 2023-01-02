package kr.co.project.comment;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVO {
	private int no;
	private String content;
	private int member_no;
	private int board_no;
	private Timestamp regdate;
	private String tablename;
	private String member_name;
	
	private int startIdx;
	private int pageRow;
	private int page;
	
	public CommentVO() {
		this.pageRow = 10;
		this.page = 1;
	}
}
