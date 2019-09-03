package cn.kukaha.atcrowdfunding.manager.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kukaha.atcrowdfunding.bean.Student;
import cn.kukaha.atcrowdfunding.manager.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private HttpSolrClient httpSolrClient;
	
	public String search(String keyword) throws Exception {
		SolrParams solrParams = new SolrQuery(keyword);
		QueryResponse queryResponse =  httpSolrClient.query(solrParams);
		List<Student> beans = queryResponse.getBeans(Student.class);
		
		System.out.println(beans.size());
		for(Student student : beans){
			System.out.println(student);
		}
		
		httpSolrClient.commit();
		System.out.println("查询成功");
		
		return beans.toString();
	}
}
