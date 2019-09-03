package cn.kukaha.test;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SolrResponseBase;
import org.apache.solr.client.solrj.response.schema.SchemaResponse.UpdateResponse;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

import cn.kukaha.atcrowdfunding.bean.Student;
import cn.kukaha.atcrowdfunding.bean.User;

public class UserSolrTest {

	/**
	 * 针对于mysql中的，t_user表完成增删改查
	 * solr索引库也要完成对应的操作  solrj技术
	 */
	
	/**
	 * 在solr中索引库中都会存在唯一一个键
	 * 如果一个document的id存在，则进行修改，否则进行创建
	 * @throws Exception 
	 * @throws IOException 
	 */
	@Test
	public void addorUpdate() throws IOException, Exception{
		//请求连接
		final String solrUrl = "http://localhost:8081/solr/core_demo";
		HttpSolrClient hClient = new HttpSolrClient.Builder(solrUrl).build();
		
		Student student = new Student();
		student.setId("1");
		student.setName("董晓斌");
		student.setAddress("山西平遥");
		student.setPhone("17835420569");
		student.setPrice(333.1);
		
		//执行添加功能
		org.apache.solr.client.solrj.response.UpdateResponse response = hClient.addBean(student);
		hClient.commit();
		hClient.close();
		System.out.println("添加成功");
	}
	
	@Test
	public void delete() throws Exception, IOException{
		//请求连接
		final String solrUrl = "http://localhost:8081/solr/core_demo";
		HttpSolrClient hClient = new HttpSolrClient.Builder(solrUrl).build();
		
//		hClient.deleteById("5000");
//		删除全部数据
		hClient.deleteByQuery("*:*");
		
		hClient.commit();
		hClient.close();
		System.out.println("删除成功");
	}
	
	@Test
	public void query() throws Exception, IOException{
		//请求连接
		final String solrUrl = "http://localhost:8081/solr/core_demo";
		HttpSolrClient hClient = new HttpSolrClient.Builder(solrUrl).build();
		
		String query = "*:*";
		SolrParams solrParams = new SolrQuery(query);
		QueryResponse queryResponse =  hClient.query(solrParams);
		List<Student> beans = queryResponse.getBeans(Student.class);
		
		System.out.println(beans.size());
		for(Student student : beans){
			System.out.println(student);
		}
		
		hClient.commit();
		hClient.close();
		System.out.println("查询成功");
	}
	
	
	@Test
	public void complexQuery() throws Exception, IOException{
		//请求连接
		final String solrUrl = "http://localhost:8081/solr/core_demo";
		HttpSolrClient hClient = new HttpSolrClient.Builder(solrUrl).build();
		
		//查询条件编写
		SolrParams solrParams = new SolrQuery();
		
		String keyWord = "*:*";
		//
		
		QueryResponse queryResponse =  hClient.query(solrParams);
		List<Student> beans = queryResponse.getBeans(Student.class);
		System.out.println(beans.size());
		for(Student student : beans){
			System.out.println(student);
		}
		
		hClient.commit();
		hClient.close();
		System.out.println("查询成功");
	}
}
