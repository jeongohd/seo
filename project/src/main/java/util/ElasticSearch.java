package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class ElasticSearch {

	
	// repository elasticsearch 검색 후 server, rest high level 라이브러리 추가
	public static void main(String[] args) throws Exception {
		
//		Map<String, Object> map = getDocument("seoul_wifi","100");
//		System.out.println(map.get("gu_nm"));
//		Map<String, Object> map1 = getDocument("test_index","1");
//		System.out.println(map.get("name"));
		
//		List<Map<String,Object>> list = getDocuments("seoul_wifi", "place_nm", "서울");
//		for (Map<String, Object> li : list) {
//			System.out.println(li.get("place_nm"));
//		}
		List<Map<String,Object>> list = getDocuments("board", "content", "내가용");
		for (Map<String, Object> li : list) {
			System.out.println(li.get("no")+"\t"+li.get("title")+"\t"+li.get("content")+"\t"+li.get("regdate"));
		}
		
		
	}
	
	// 한 건 조회
	public static Map<String, Object> getDocument(String index, String id) throws Exception {
		HttpHost host = new HttpHost("localhost", 9200);
		RestClientBuilder restClientBuilder = RestClient.builder(host);
		RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
		
		GetRequest getRequest = new GetRequest(index, id);
		RequestOptions options = RequestOptions.DEFAULT;
		
		GetResponse getResponse = client.get(getRequest, options);
		Map<String, Object> map = getResponse.getSourceAsMap();
		
		return map;
	}
	// 여러 건 조회
	public static List<Map<String, Object>> getDocuments(String index, String field, String Searchword) throws Exception {
		HttpHost host = new HttpHost("localhost", 9200);
		RestClientBuilder restClientBuilder = RestClient.builder(host);
		RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
		
		SearchRequest searchRequest = new SearchRequest(index);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//		sourceBuilder.query(QueryBuilders.matchQuery(field, Searchword));
		sourceBuilder.query(QueryBuilders.fuzzyQuery(field, Searchword).fuzziness(Fuzziness.TWO));
		searchRequest.source(sourceBuilder);
		
		sourceBuilder.size(); // 20개를 가져옴
		sourceBuilder.from(0);  
		
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		
		List<Map<String, Object>> list = new ArrayList();
		for (SearchHit sh : searchResponse.getHits().getHits()) {
			list.add(sh.getSourceAsMap());
		}
		return list;
	}
	
//		여러 건 조회 (메인 메서드 안에 한 코딩을 위의 매서드로 만듦)
//		HttpHost host = new HttpHost("localhost", 9200);
//		RestClientBuilder restClientBuilder = RestClient.builder(host);
//		RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
//		
//		SearchRequest searchRequest = new SearchRequest("seoul_wifi");
//		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//		sourceBuilder.query(QueryBuilders.matchQuery("place_nm", "도서관 서울"));
//		searchRequest.source(sourceBuilder);
//		
//		// 정확한 텍스트를 입력하지 않았을경우, 비슷한 텍스트로 검색
//		sourceBuilder.query(QueryBuilders.fuzzyQuery("place_nm", "송xx파").fuzziness(Fuzziness.TWO)); // 오류 두개까지 있는 건 알아서 찾아줘서 비슷한 텍스트로 검색.
//		searchRequest.source(sourceBuilder);
//		
//		// 페이징처리관련
//		sourceBuilder.size(20); // 20개를 가져옴
//		sourceBuilder.from(20);  
//		
//		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//		
//		List<Map<String, Object>> list = new ArrayList();
//		for (SearchHit sh : searchResponse.getHits().getHits()) {
//			list.add(sh.getSourceAsMap());
//		}
//		
//		for (Map<String, Object> li : list) {
//			System.out.println(li.get("place_nm"));
//		}

}
