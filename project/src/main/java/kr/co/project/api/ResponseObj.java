package kr.co.project.api;

import java.util.List;

import lombok.Data;

@Data
public class ResponseObj {

	private int totalCount;
	private int totalPage;
	private int page;
	private List<BoardVO> items;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	 
}
