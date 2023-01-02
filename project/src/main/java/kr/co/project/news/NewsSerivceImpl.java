package kr.co.project.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsSerivceImpl implements NewsService {

	@Autowired
	NewsMapper mapper;
	
	@Override
	public Map index(NewsVO vo) {
		int totalCount = mapper.count(vo); // 총게시물수
		// 총페이지수
		int totalPage = totalCount / vo.getPageRow();
		if (totalCount % vo.getPageRow() > 0) totalPage++;
		
		// 시작인덱스
		int startIdx = (vo.getPage()-1) * vo.getPageRow();
		vo.setStartIdx(startIdx);
		List<NewsVO> list = mapper.list(vo);
		
		// 페이징처리
		int endPage = (int)(Math.ceil(vo.getPage()/10.0)*10);
		int startPage = endPage-9;
		if (endPage > totalPage) endPage = totalPage;
		boolean prev = startPage > 1 ? true : false;
		boolean next = endPage < totalPage ? true : false;
		
		Map map = new HashMap();
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("prev", prev);
		map.put("next", next);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map view(int no) {
		mapper.updateViewcount(no);
		return mapper.view(no);
	}

	@Override
	public Map edit(int no) {
		return mapper.view(no);
	}

	@Override
	public boolean update(NewsVO vo) {
		return mapper.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean delete(int no) {
		return mapper.delete(no) > 0 ? true : false;
	}

	@Override
	public boolean insert(Map map) {
		return mapper.insert(map) > 0 ? true : false;
	}

}
