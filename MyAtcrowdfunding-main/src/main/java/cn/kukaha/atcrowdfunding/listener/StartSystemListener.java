package cn.kukaha.atcrowdfunding.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * 在服务器启动时，创建application对象时需要执行的类
 * @author LC
 *
 */
public class StartSystemListener implements ServletContextListener{
	
	public void contextInitialized(ServletContextEvent sce) {
		//${pageContext.request.contextPath}
		//1.将项目的上下文路径(request.getContextPath())放置到application域中
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		application.setAttribute("APP_PATH", contextPath);
		System.out.println("StartSystemListener被监听 APP_PATH 路径为" + contextPath);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
