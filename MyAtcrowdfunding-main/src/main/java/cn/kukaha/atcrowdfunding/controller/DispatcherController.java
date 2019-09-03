package cn.kukaha.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kukaha.atcrowdfunding.bean.User;
import cn.kukaha.atcrowdfunding.manager.service.StudentService;
import cn.kukaha.atcrowdfunding.manager.service.UserService;
import cn.kukaha.atcrowdfunding.util.AjaxResult;
import cn.kukaha.atcrowdfunding.util.Constant;
import cn.kukaha.atcrowdfunding.util.MD5Util;
import cn.kukaha.atcrowdfunding.util.StringUtil;

@Controller
public class DispatcherController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
//	@Qualifier("studentService")
	private StudentService studentService;
	
	@RequestMapping("doSearch")
	@ResponseBody
	public Object doSearch(String keyWord) throws Exception{
		AjaxResult result = new AjaxResult();
		
		try {
			if(StringUtil.isEmpty(keyWord)){
				keyWord = "*:*";
			}
			String search = studentService.search(keyWord);
			result.setData(search);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("搜索失败");
		}
		return result;
	}
	
	@RequestMapping("index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("main")
	public String main(){
		return "main";
	}
	
//	同步的请求
//	@RequestMapping("doLogin")
//	public String doLogin(String loginacct,String userpswd,String type,HttpSession session){
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("loginacct", loginacct);
//		paramMap.put("userpswd", userpswd);
//		paramMap.put("type", type);
//		User user = userService.queryUserlogin(paramMap);
//		session.setAttribute(Constant.LOGIN_USER, user);
//		return "redirect:/main.htm";
//	}
	
//	异步请求
	@RequestMapping("doLogin")
	@ResponseBody
	public Object doLogin(String loginacct,String userpswd,String type,HttpSession session){
		AjaxResult result = new AjaxResult();
		try {
			userpswd = MD5Util.digest(userpswd);
//			System.out.println(userpswd);
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("loginacct", loginacct);
			paramMap.put("userpswd", userpswd);
			paramMap.put("type", type);
			User user = userService.queryUserlogin(paramMap);
			session.setAttribute(Constant.LOGIN_USER, user);
			result.setSuccess(true);
			//{"success":true}
		} catch (Exception e) {
			result.setMessage("登陆失败！");
			e.printStackTrace();
			result.setSuccess(false);
			//{"success":false,"message":"登陆失败！"}
		}
		return result;
	}
	
	//注销登录
	@RequestMapping("logOut")
	public String logOut(HttpSession session){
//		session.invalidate();
		session.removeAttribute("user");
		return "redirect:/index.htm";
	}
}
