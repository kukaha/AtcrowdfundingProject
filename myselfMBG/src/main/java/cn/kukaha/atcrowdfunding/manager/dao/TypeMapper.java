package cn.kukaha.atcrowdfunding.manager.dao;

import cn.kukaha.atcrowdfunding.bean.Type;
import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    Type selectByPrimaryKey(Integer id);

    List<Type> selectAll();

    int updateByPrimaryKey(Type record);
}