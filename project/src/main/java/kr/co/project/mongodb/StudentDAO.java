package kr.co.project.mongodb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {

	@Autowired
	MongoTemplate mongoTemplate;
	
	// 넘어오는 데이터를 put하지말고, 그냥 싹 다 짚어 넣음. 
	public Map insert(Map map) {
		return mongoTemplate.insert(map, "student");
	}
	
//	동일한 것 만 검색. 
//	public List<Map> list(Map map) {
//		// find, findall 이 다른 상황에서 쓰여야 하는데, 어떤 기준인가?
//		// find(검색어가 있을 때) / findall (검색어가 없을 때)
//		// map.get("sword") != null => 파라미터 sword가 없는 경우. 
//		if(!("".equals(map.get("sword")))) {
//			Criteria cri = new Criteria(); // 이 객체가 쿼리 객체ㅔ 들어가야해서 find()의 첫번째매개변수 자리가 query임.
//			Query q = new Query(cri.orOperator(
//					Criteria.where("id").is(map.get("sword")), // is는 일치하는 걸 의미.
//					Criteria.where("name").is(map.get("sword"))
//					)); // criteria 안에 or / and Operator 가 있으며, 
//			return mongoTemplate.find(q, Map.class, "student"); 
//		} else {
//			return mongoTemplate.findAll(Map.class, "student"); // 검색어 없이 전체(student 컬랙션 안에 있는 모든 데이터)를 가져옴.
//		}
//		
//	}
	

	// like 검색.
	public List<Map> list(Map map) {
		// find, findall 이 다른 상황에서 쓰여야 하는데, 어떤 기준인가?
		// find(검색어가 있을 때) / findall (검색어가 없을 때)
		// map.get("sword") != null => 파라미터 sword가 없는 경우. 
		if(!("".equals(map.get("sword")))) {
			Criteria cri = new Criteria(); // 이 객체가 쿼리 객체ㅔ 들어가야해서 find()의 첫번째매개변수 자리가 query임.
			Query q = new Query(cri.orOperator(
					Criteria.where("name").regex(map.get("sword").toString()), // mongodb에서 like 검색은 정규식으로 했기에 regex를 사용. 
					Criteria.where("id").regex(map.get("sword").toString())  
					)); // criteria 안에 or / and Operator 가 있으며, 
			return mongoTemplate.find(q, Map.class, "student"); 
		} else {
			return mongoTemplate.findAll(Map.class, "student"); // 검색어 없이 전체(student 컬랙션 안에 있는 모든 데이터)를 가져옴.
		}
	}
}
