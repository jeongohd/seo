package kr.co.project.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper mapper;
	
	@Override
	public List<BoardVO> list(BoardVO vo) {
		return mapper.list(vo);
	}
	
//	@Override
//	public Map index(BoardVO vo) {
//		int totalCount = mapper.count(vo); // 총게시물수
//		// 총페이지수
//		int totalPage = totalCount / vo.getPageRow();
//		if (totalCount % vo.getPageRow() > 0) totalPage++;
//		
//		// 시작인덱스
//		int startIdx = (vo.getPage()-1) * vo.getPageRow();
//		vo.setStartIdx(startIdx);
//		List<BoardVO> list = mapper.list(vo);
//		
//		// 페이징처리
//		int endPage = (int)(Math.ceil(vo.getPage()/10.0)*10);
//		int startPage = endPage-9;
//		if (endPage > totalPage) endPage = totalPage;
//		boolean prev = startPage > 1 ? true : false;
//		boolean next = endPage < totalPage ? true : false;
//		
//		Map map = new HashMap();
//		map.put("totalCount", totalCount);
//		map.put("totalPage", totalPage);
//		map.put("startPage", startPage);
//		map.put("endPage", endPage);
//		map.put("prev", prev);
//		map.put("next", next);
//		map.put("list", list);
//		
//		return map;
//	}
	
	
	

	@Override
	public BoardVO view(int no) {
		mapper.updateViewcount(no);
		return mapper.view(no);
	}

	@Override
	public BoardVO edit(int no) {
		return mapper.view(no);
	}

	@Override
	public boolean update(BoardVO vo) {
		return mapper.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean delete(int no) {
		return mapper.delete(no) > 0 ? true : false;
	}

	@Override
	public boolean insert(BoardVO vo) {
		return mapper.insert(vo) > 0 ? true : false;
	}

}
