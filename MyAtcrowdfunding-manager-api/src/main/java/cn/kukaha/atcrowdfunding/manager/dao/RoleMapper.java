package cn.kukaha.atcrowdfunding.manager.dao;

import cn.kukaha.atcrowdfunding.bean.Role;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
}