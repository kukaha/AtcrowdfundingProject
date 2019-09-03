package cn.kukaha.atcrowdfunding.manager.dao;

import cn.kukaha.atcrowdfunding.bean.Student;
import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    Student selectByPrimaryKey(String id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);
}