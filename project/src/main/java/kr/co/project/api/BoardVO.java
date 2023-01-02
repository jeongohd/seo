package kr.co.project.api;

import lombok.Data;

@Data
public class BoardVO {
	
	private int no;
	private String title;
	private String member_name;
	private int comment_count;
	private String regdatae;
}
