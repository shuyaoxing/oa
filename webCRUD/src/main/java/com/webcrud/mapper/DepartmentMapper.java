package com.webcrud.mapper;

import com.webcrud.pojo.Department;
import com.webcrud.pojo.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

//方式一： 访问数据库，使用注解
//指定这是一个操作数据库的mapper
@Mapper
public interface DepartmentMapper {
    //查询所有部门信息
    @Select("select * from department")
    public List<Department> getDepartments();

    @Select("select id from department where departmentName=#{departmentName}")
    public int getDeparmentId(String departmentName);

    @Select("select * from department where id=#{departmentId}")
    public Department getDeparmentById(Integer departmentId);

}
