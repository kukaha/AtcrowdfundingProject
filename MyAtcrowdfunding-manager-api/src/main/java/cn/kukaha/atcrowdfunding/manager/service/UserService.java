package cn.kukaha.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import cn.kukaha.atcrowdfunding.bean.Role;
import cn.kukaha.atcrowdfunding.bean.User;
import cn.kukaha.atcrowdfunding.util.Page;
import cn.kukaha.atcrowdfunding.vo.Data;

public interface UserService {

	User queryUserlogin(Map<String, Object> paramMap);

//	@Deprecated
//	Page queryPage(Integer pageno, Integer pagesize);
	
	int saveUser(User user);

	Page queryPage(Map<String,Object> paramMap);

	User getUserById(Integer id);

	int updateUser(User user);

	int removeUser(Integer id);

	int removeBatchUser(Integer[] ids);

	int removeBatchUserByVO(Data d);

	List<Role> queryAllRole();

	List<Integer> queryRoleByUserId(Integer id);

	int saveUserRoleRelationship(Integer userid, Data data);

	int deleteUserRoleRelationship(Integer userid, Data data);


}
