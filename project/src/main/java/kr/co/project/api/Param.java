package kr.co.project.api;

import lombok.Data;

@Data
public class Param {

	private int page;
	private int pageRow;
	private String stype;
	private String sword;
	
	private int startIdx;
	
	public Param() {
		this.page = 1;
		this.pageRow = 10;
	}
	
	public int getStartIdx() {
		startIdx = (page-1)*pageRow;
		return startIdx;
	}
	
}
