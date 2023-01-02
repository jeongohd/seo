package util;

import kr.co.project.api.Param;

public class BaseController {

	public int startPage;
	public int endPage;
	public boolean prev, next;
	public int totalPage;
	
	public void pageProcess(Param param, int total) {
		
		totalPage = total / param.getPageRow();
		if(total % param.getPageRow() > 0) totalPage++;
		
		this.endPage = (int)(Math.ceil(param.getPage()/10.0))*10;
		this.startPage = this.endPage - 9;
		int realEnd = (int)(Math.ceil((total*1.0)/param.getPageRow()));
		if(realEnd < this.endPage) this.endPage = realEnd;
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
