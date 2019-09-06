package cn.kukaha.atcrowdfunding.manager.dao;

import cn.kukaha.atcrowdfunding.bean.Role;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

	List<Role> queryList(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	int deleteBatchRoleByVO(@Param("ids")List<Integer> ids);
	
}