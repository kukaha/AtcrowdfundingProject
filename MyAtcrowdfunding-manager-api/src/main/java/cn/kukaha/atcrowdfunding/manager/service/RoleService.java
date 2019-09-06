package cn.kukaha.atcrowdfunding.manager.service;

import java.util.Map;

import cn.kukaha.atcrowdfunding.bean.Role;
import cn.kukaha.atcrowdfunding.util.Page;
import cn.kukaha.atcrowdfunding.vo.Data;

public interface RoleService {

	Page queryPage(Map<String, Object> paramMap);

	Role getRoleById(Integer id);

	int updateRole(Role role);

	int removeRole(Integer id);

	int removeBatchRoleByVO(Data d);

	int saveRole(Role role);
	

}
