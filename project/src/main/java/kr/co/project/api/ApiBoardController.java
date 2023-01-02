package kr.co.project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import util.BaseController;

@RestController // util 패키지 안 BaseController.pageProcess() 활용하기 위해 상속받음.
@CrossOrigin(origins = "*") // 모든 클라이언트 접근 허용. 
// 특정 클라이언트 접근 허용 (origins = {"http://192.168.0.23:5500"})
public class ApiBoardController extends BaseController {
	
	@Autowired
	ApiBoardMapper mapper;
	
//	@GetMapping("/api/test") 
//	public Map test() {
//		Map map = new HashMap();
//		map.put("name", "오정길");
//		map.put("age", "30");
//		return map;
//	}
//		
//	@GetMapping("/api/test2") 
//	public List<BoardVO> test2() {
//		List<BoardVO> list = new ArrayList();
//		for(int i = 0; i < 10; i ++) {
//			BoardVO vo = new BoardVO();
//			vo.setTitle("title" + i);
//			vo.setContent("content" + i);
//			list.add(vo);
//		}
//		return list;
//	}
	
	@GetMapping("/api/board/list")
	public ResponseObj list(Param param) {
		
		int totalCount = mapper.count(param);
		
		
		pageProcess(param, totalCount); // 페이징처리 관련.
		
		ResponseObj obj = new ResponseObj();
		obj.setTotalCount(totalCount);
		obj.setPage(param.getPage());
		obj.setTotalPage(totalPage);
		obj.setItems(mapper.list(param));
		obj.setStartPage(startPage);
		obj.setEndPage(endPage);
		obj.setPrev(prev);
		obj.setNext(next);
		return obj;
		
	}
	
	
}
