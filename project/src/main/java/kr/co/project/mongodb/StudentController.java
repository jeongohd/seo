package kr.co.project.mongodb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
	
	@Autowired
	StudentDAO sdao;
	
	@RequestMapping("/student/index")
	public void index() {};
	
	@PostMapping("/student/insert")
	@ResponseBody // 리턴을 map 으로 할 것이기 때문에
	public Map<String, Object> insert(@RequestBody Map map) {
		return sdao.insert(map);
	}
	
	@RequestMapping("/student/list")
	@ResponseBody
	public List<Map> list(@RequestBody Map map) {
		return sdao.list(map);
	}
	// 검색어를 사용하기 위해 파라미터를 @RequestBody Map map로 설정.
	// jackson - 파라미터인 json을 객체로 바꾸고 객체를 json으로 바꿈.
}
