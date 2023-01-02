package kr.co.project.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	@Autowired
	BoardService service;
	@Autowired
	BoardMapper mapper;
	
	
 @RequestMapping("/api/board/list")
    public ResponseEntity<Map> api(BoardVO vo) {
    	Map map = new HashMap<String,Object>();
    	int totalCount = mapper.count(vo); 
    	int totalPage = totalCount / vo.getPageRow();
    	if (totalCount % vo.getPageRow() > 0) totalPage++;
    	int startIdx = (vo.getPage()-1) * vo.getPageRow();
    	vo.setStartIdx(startIdx);
    	// 페이징처리
    	int endPage = (int)(Math.ceil(vo.getPage()/(float)vo.getPageRow())*vo.getPageRow());
    	int startPage = endPage-(vo.getPageRow() - 1);
    	if (endPage > totalPage) endPage = totalPage;
    	boolean prev = startPage > 1 ? true : false;
    	boolean next = endPage < totalPage ? true : false;
    	
    	List<BoardVO> items = service.list(vo);
    	
    	map.put("totalCount",totalCount);
    	map.put("totalPage", totalPage);
    	map.put("page", vo.getPage());
    	map.put("pageRow", vo.getPageRow());
    	map.put("items",items);
    	return new ResponseEntity<>(map,HttpStatus.OK);
    };

}
