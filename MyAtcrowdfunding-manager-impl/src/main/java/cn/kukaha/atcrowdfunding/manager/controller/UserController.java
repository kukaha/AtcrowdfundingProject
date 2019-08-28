package cn.kukaha.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kukaha.atcrowdfunding.bean.Role;
import cn.kukaha.atcrowdfunding.bean.User;
import cn.kukaha.atcrowdfunding.manager.service.UserService;
import cn.kukaha.atcrowdfunding.util.AjaxResult;
import cn.kukaha.atcrowdfunding.util.Page;
import cn.kukaha.atcrowdfunding.util.StringUtil;
import cn.kukaha.atcrowdfunding.vo.Data;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//同步请求
//	@RequestMapping("/index")
//	public String index(@RequestParam(value="pageno",required=false,defaultValue="1")Integer pageno,@RequestParam(value="pagesize",required=false,defaultValue="10")Integer pagesize,Map map){
//		Page page = userService.queryPage(pageno,pagesize);
//		map.put("page", page);
//		return "user/index-1";
//	}

	@RequestMapping("/index")
	public String toIndex(){
		return "user/index";
	}
	
	@RequestMapping("/assignRole")
	public String assignRole(Integer id,Map<String,Object> map){
		List<Role> allListRole = userService.queryAllRole();
		List<Integer> roleIds = userService.queryRoleByUserId(id);
		
		List<Role> leftRoleList = new ArrayList<Role>();//未分配角色
		List<Role> rightRoleList = new ArrayList<Role>();//已分配角色
		
		for(Role role : allListRole){
			if(roleIds.contains(role.getId())){
				rightRoleList.add(role);
			}else{
				leftRoleList.add(role);
			}
		}
		map.put("leftRoleList", leftRoleList);
		map.put("rightRoleList", rightRoleList);
		return "user/assignRole";
	}
	
	
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "user/add";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(Integer id,Map<String,Object> map){
		User user = userService.getUserById(id);
		map.put("user", user);
		return "user/update";
	}
	
	//异步请求
//	@RequestMapping("/index")
//	@ResponseBody
//	public Object index(@RequestParam(value="pageno",required=false,defaultValue="1")Integer pageno,@RequestParam(value="pagesize",required=false,defaultValue="10")Integer pagesize){
//		
//		AjaxResult result = new AjaxResult();
//		try {
//			Page page = userService.queryPage(pageno,pagesize);
//			System.out.println(page.getDatas());
//			result.setSuccess(true);
//			result.setPage(page);
//		} catch (Exception e) {
//			result.setSuccess(false);
//			e.printStackTrace();
//			result.setMessage("查询数据失败");
//		}
//		return result;
//	}
	
	//异步条件查询
	@RequestMapping("/doIndex")
	@ResponseBody
	public Object index(@RequestParam(value="pageno",required=false,defaultValue="1")Integer pageno,@RequestParam(value="pagesize",required=false,defaultValue="10")Integer pagesize,String queryText){
		
		AjaxResult result = new AjaxResult();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("pageno", pageno);
			paramMap.put("pagesize", pagesize);
			if(StringUtil.isNotEmpty(queryText)){
				if(queryText.contains("%")){
					queryText = queryText.replaceAll("%", "\\\\%");
				}
				paramMap.put("queryText", queryText);
			}
			Page page = userService.queryPage(paramMap);
//			System.out.println(page.getDatas());
			result.setSuccess(true);
			result.setPage(page);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("查询数据失败");
		}
		return result;
	}
	
	@RequestMapping("/doAdd")
	@ResponseBody
	public Object doAdd(User user){
		AjaxResult result = new AjaxResult();
		try {
			int status = userService.saveUser(user);
			result.setSuccess(status==1);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("保存用户失败");
		}
		return result;
	}
	
	@RequestMapping("/doUpdate")
	@ResponseBody
	public Object doUpdate(User user){
		AjaxResult result = new AjaxResult();
		try {
			int status = userService.updateUser(user);
			result.setSuccess(status==1);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("修改用户失败");
		}
		return result;
	}
	
	@RequestMapping("/doRemove")
	@ResponseBody
	public Object doRemove(Integer id){
		AjaxResult result = new AjaxResult();
		try {
			int status = userService.removeUser(id);
			result.setSuccess(status==1);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除用户失败");
		}
		return result;
	}

//接受一个参数名带多个值	   也就是发过来一个字符串id=33&id=88&id=33   ......
//	@RequestMapping("/doRemoveBatch")
//	@ResponseBody
//	public Object doRemoveBatch(Integer[] id){
//		AjaxResult result = new AjaxResult();
//		try {
//			int count = userService.removeBatchUser(id);
//			result.setSuccess(count==id.length);
//		} catch (Exception e) {
//			result.setSuccess(false);
//			e.printStackTrace();
//			result.setMessage("删除用户失败");
//		}
//		return result;
//	}
	
	
//接受多条数据
	@RequestMapping("/doRemoveBatch")
	@ResponseBody
	public Object doRemoveBatch(Data d){
		AjaxResult result = new AjaxResult();
		try {
			int count = userService.removeBatchUserByVO(d);
			result.setSuccess(count==d.getDatas().size());
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除用户失败");
		}
		return result;
	}
	
	
	@RequestMapping("/doAssignRole")
	@ResponseBody
	public Object doAssignRole(Integer userid,Data data){
		AjaxResult result = new AjaxResult();
		try {
			int count = userService.saveUserRoleRelationship(userid,data);
			result.setSuccess(count==1);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("分配角色失败");
		}
		return result;
	}
	
	@RequestMapping("/doUnAssignRole")
	@ResponseBody
	public Object doUnAssignRole(Integer userid,Data data){
		AjaxResult result = new AjaxResult();
		try {
			int count = userService.deleteUserRoleRelationship(userid,data);
			result.setSuccess(count==data.getIds().size());
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("移除角色失败");
		}
		return result;
	}
}
