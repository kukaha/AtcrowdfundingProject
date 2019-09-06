package cn.kukaha.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kukaha.atcrowdfunding.bean.Role;
import cn.kukaha.atcrowdfunding.bean.User;
import cn.kukaha.atcrowdfunding.manager.service.RoleService;
import cn.kukaha.atcrowdfunding.util.AjaxResult;
import cn.kukaha.atcrowdfunding.util.Page;
import cn.kukaha.atcrowdfunding.util.StringUtil;
import cn.kukaha.atcrowdfunding.vo.Data;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/index")
	public String toIndex(){
		return "role/index";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "role/add";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(Integer id,Map<String,Object> map){
		Role role = roleService.getRoleById(id);
		map.put("role", role);
		return "role/update";
	}
	
	@RequestMapping("/doIndex")
	@ResponseBody
	public Object doIndex(@RequestParam(value="pageno",required=false,defaultValue="1")Integer pageno,@RequestParam(value="pagesize",required=false,defaultValue="10")Integer pagesize,String queryText){
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
			
			Page page = roleService.queryPage(paramMap);
			result.setSuccess(true);
			result.setPage(page);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("查询角色失败");
		}
		return result;
	}
	
	@RequestMapping("/doAdd")
	@ResponseBody
	public Object doAdd(Role role){
		AjaxResult result = new AjaxResult();
		try {
			int status = roleService.saveRole(role);
			result.setSuccess(status==1);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("保存名称失败");
		}
		return result;
	}
	
	@RequestMapping("/doRemove")
	@ResponseBody
	public Object doRemove(Integer id){
		AjaxResult result = new AjaxResult();
		try {
			int status = roleService.removeRole(id);
			result.setSuccess(status==1);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除用户失败");
		}
		return result;
	}
	
	//接受多条数据
		@RequestMapping("/doRemoveBatch")
		@ResponseBody
		public Object doRemoveBatch(Data d){
			AjaxResult result = new AjaxResult();
			try {
				int count = roleService.removeBatchRoleByVO(d);
				result.setSuccess(count==d.getIds().size());
			} catch (Exception e) {
				result.setSuccess(false);
				e.printStackTrace();
				result.setMessage("删除名称失败");
			}
			return result;
		}
	
	@RequestMapping("/doUpdate")
	@ResponseBody
	public Object doUpdate(Role role){
		AjaxResult result = new AjaxResult();
		try {
			int status = roleService.updateRole(role);
			result.setSuccess(status==1);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("修改名称失败");
		}
		return result;
	}
	
}
