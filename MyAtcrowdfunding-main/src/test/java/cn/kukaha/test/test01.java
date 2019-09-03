package cn.kukaha.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.kukaha.atcrowdfunding.bean.User;
import cn.kukaha.atcrowdfunding.manager.service.UserService;
import cn.kukaha.atcrowdfunding.util.MD5Util;

public class test01 {
	public static void main(String[] args) {
		
		ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring*.xml");
		
		UserService userService = ioc.getBean(UserService.class);
		
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setLoginacct("test"+i);
			user.setUserpswd(MD5Util.digest("123456"));
			user.setUsername("test"+i);
			user.setCreatetime("2019-07-11 14:44:33");
			user.setEmail("test"+i+"@qq.com");
			userService.saveUser(user);
		}
		
	}
}
