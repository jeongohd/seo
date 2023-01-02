package kr.co.project.news;

import java.util.Map;

public interface NewsService {

	// 목록
	Map index(NewsVO vo);
	// 상세
	Map view(int no);
	// 수정폼
	Map edit(int no);
	// 수정처리
	boolean update(NewsVO vo);
	// 삭제처리
	boolean delete(int no);
	// 등록처리
	boolean insert(Map map);
}
