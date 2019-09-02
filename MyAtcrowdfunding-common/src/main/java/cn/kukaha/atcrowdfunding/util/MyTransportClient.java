package cn.kukaha.atcrowdfunding.util;

//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.aspectj.lang.annotation.Before;
//import org.dussan.vaadin.dcharts.data.Ticks;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.jfree.chart.axis.Tick;
//import org.jfree.chart.axis.TickUnit;

public class MyTransportClient{

	
//	private static void querySerach() throws Exception{
//		TransportClient client = null;
//		try{
//			Settings settings=Settings.builder().put("cluster.name", "elasticsearch").build();
//			client = new PreBuiltTransportClient(settings)
//					.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
//
//			GetResponse response = client.prepareGet("kukaha", "user", "1").execute().actionGet();
//			System.out.println(response);
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally {
//			client.close();
//		}
//	}
//	
//	
//	public static void main(String[] args) throws Exception {
//		querySerach();
//		
//	}
	
}
