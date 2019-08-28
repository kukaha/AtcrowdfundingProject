package cn.kukaha.atcrowdfunding.manager.dao;

import cn.kukaha.atcrowdfunding.bean.Role;
import cn.kukaha.atcrowdfunding.bean.User;
import cn.kukaha.atcrowdfunding.vo.Data;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	User queryUserlogin(Map<String, Object> paramMap);

	User queryUserNameLogin(Map<String, Object> paramMap);

//	List<User> queryList(@Param("startIndex")Integer startIndex,@Param("pagesize") Integer pagesize);

//	int queryCount();
	
	int queryCount(Map<String, Object> paramMap);

	List<User> queryList(Map<String, Object> paramMap);

	int deleteBatchUserByVO(@Param("userList")List<User> userList);

	List<Role> queryAllRole();

	List<Integer> queryRoleByUserId(Integer id);

	int saveUserRoleRelationship(@Param("userid")Integer userid,@Param("data") Data data);

	int deleteUserRoleRelationship(@Param("userid")Integer userid,@Param("data") Data data);

//	int deleteBatchUserByVO(Data d);
	
}