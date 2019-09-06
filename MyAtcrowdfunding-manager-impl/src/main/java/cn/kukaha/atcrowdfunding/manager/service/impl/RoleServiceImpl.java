package cn.kukaha.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kukaha.atcrowdfunding.bean.Role;
import cn.kukaha.atcrowdfunding.bean.User;
import cn.kukaha.atcrowdfunding.manager.dao.RoleMapper;
import cn.kukaha.atcrowdfunding.manager.service.RoleService;
import cn.kukaha.atcrowdfunding.util.Page;
import cn.kukaha.atcrowdfunding.vo.Data;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;
	
	
	public Page queryPage(Map<String, Object> paramMap) {
		Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		Integer startIndex = page.getStartIndex();
		paramMap.put("startIndex", startIndex);
		List<Role> datas= roleMapper.queryList(paramMap);
		page.setDatas(datas);
		int totalsize = roleMapper.queryCount(paramMap);
		page.setTotalsize(totalsize);
		return page;
	}


	public Role getRoleById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}


	public int updateRole(Role role) {
		return roleMapper.updateByPrimaryKey(role);
	}


	public int removeRole(Integer id) {
		return roleMapper.deleteByPrimaryKey(id);
	}


	public int removeBatchRoleByVO(Data data) {
		System.out.println(data.getIds());
		return roleMapper.deleteBatchRoleByVO(data.getIds());
	}


	public int saveRole(Role role) {
		return roleMapper.insert(role);
	}

}
