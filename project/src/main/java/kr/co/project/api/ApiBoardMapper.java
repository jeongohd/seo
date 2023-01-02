package kr.co.project.api;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiBoardMapper {

	public int count(Param p);
	public List<BoardVO> list(Param p);
}
