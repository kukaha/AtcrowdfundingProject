package cn.kukaha.atcrowdfunding.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ElasticsearchController{
	
	public static TransportClient createClient() {
		TransportClient client = null;
		try{
			Settings settings = Settings.builder().put("cluster.name","elasticsearch").build();
			client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"),9300));
		}catch (Exception e){
			e.printStackTrace();
		}
		return client;
	}
	
	@RequestMapping("query")
	@ResponseBody
	public String query() throws Exception{
		TransportClient client = createClient();
		GetResponse response = client.prepareGet("kukaha","user","1").execute().actionGet();
		client.close();
		return response.getSourceAsString();
	}
	
	
//	private ElasticsearchTemplate elasticsearchTemplate;
	
//	SearchQuery searchQuery = new NativeSearchQueryBuilder()
//			.withQuery(QueryBuilders.queryStringQuery("董晓斌"))
//			.build();
	
	// 1、直接用名称创建
//	@RequestMapping("query")
//	public void query(){
//		elasticsearchTemplate.createIndex("book");
//	}

}
