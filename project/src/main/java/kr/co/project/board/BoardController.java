package kr.co.project.board;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.project.comment.CommentService;
import kr.co.project.member.MemberVO;
import util.ElasticSearch;

@Controller
public class BoardController{

	@Autowired
	BoardService service;
	@Autowired
	CommentService cService;
	@Autowired
	BoardMapper mapper;
	
	
//	@RequestMapping("/test")
//	@ResponseBody
//	public ResponseEntity<Map> test(BoardVO vo) {
//		Map map = new HashMap();
//		map.put("page", 1);
//		map.put("pageRow", 10);
//		map.put("list", service.index(vo));
//		return new ResponseEntity<>(map, HttpStatus.OK );
//	}

	@GetMapping("/test")
	@ResponseBody
	public Map test(BoardVO vo) {
		Map map = new HashMap();
		map.put("list", service.list(vo));
		return map;
	}
	
	 @GetMapping("/test2")
	 @ResponseBody
	   public ResponseEntity<Map> test2(BoardVO vo) {
	      Map map = new HashMap<String,Object>();
	      map.put("items",service.list(vo));
	      return new ResponseEntity<>(map,HttpStatus.OK);
	   };

	      
      @GetMapping("/test3")
      @ResponseBody
      public ResponseEntity<Map> test3(BoardVO vo) {
         Map map = new HashMap<String,Object>();
         int totalCount = mapper.count(vo); 
 		 int totalPage = totalCount / vo.getPageRow();
 		 if (totalCount % vo.getPageRow() > 0) totalPage++;
 		 map.put("totalcount",totalCount);
 		 map.put("Page", vo.getPage());
 		 map.put("totalPage", totalPage);
 		 map.put("items",service.list(vo));
         return new ResponseEntity<>(map,HttpStatus.OK);
      };
	
	
	
	@GetMapping("/board/index.do")
	public String index(Model model, BoardVO vo) {
		model.addAttribute("data", service.list(vo));
		try {
			model.addAttribute("es", ElasticSearch.getDocument("seoul_wifi", "1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/index";
	}
	
	@GetMapping("/board/write.do")
	public String write() {
		
		return "board/write";
	}
	
	@PostMapping("/board/insert.do")
	public String insert(BoardVO vo, Model model, 
			@RequestParam MultipartFile filename, 
			HttpServletRequest req) {
		
		// 첨부파일 처리
		if (!filename.isEmpty()) {
			// 파일명 구하기
			String org = filename.getOriginalFilename();
			String ext = org.substring(org.lastIndexOf(".")); // 확장자
			String real = new Date().getTime()+ext;
			
			// 파일저장
			String path = req.getRealPath("/upload/");
			try {
			filename.transferTo(new File(path+real));
			} catch (Exception e) {}
			
			vo.setFilename_org(org);
			vo.setFilename_real(real);
		}
		// member_no 저장
		HttpSession sess = req.getSession();
		MemberVO mv = (MemberVO)sess.getAttribute("loginInfo");
		vo.setMember_no(mv.getNo());
		if (service.insert(vo)) {
			model.addAttribute("msg", "정상적으로 저장되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "저장이 실패했습니다.");
			return "common/alert";
		}
	}
	
	@GetMapping("/board/view.do")
	public String view(BoardVO vo, Model model) {
		BoardVO data = service.view(vo.getNo());
		model.addAttribute("data", data);
		return "board/view";
	}
	
	@GetMapping("/board/edit.do")
	public String edit(BoardVO vo, Model model) {
		BoardVO data = service.edit(vo.getNo());
		model.addAttribute("data", data);
		return "board/edit";
	}
	
	@PostMapping("/board/update.do")
	public String update(BoardVO vo, Model model) {
		if (service.update(vo)) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?no="+vo.getNo());
			return "common/alert";
		} else {
			model.addAttribute("msg", "수정실패");
			return "common/alert";
		}
	}
	
	@GetMapping("/board/delete.do")
	public String delete(BoardVO vo, Model model) {
		if (service.delete(vo.getNo())) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "삭제실패");
			return "common/alert";
		}
	}
}
