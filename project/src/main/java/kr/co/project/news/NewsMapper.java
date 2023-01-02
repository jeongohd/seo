package kr.co.project.news;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {
	int insert(Map map);
	int count(NewsVO vo);
	List<NewsVO> list(NewsVO vo);
	Map view(int no);
	void updateViewcount(int no);
	int update(NewsVO vo);
	int delete(int no);
}
