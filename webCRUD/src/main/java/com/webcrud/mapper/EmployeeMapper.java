package com.webcrud.mapper;


import com.webcrud.pojo.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Collection;
import java.util.List;
import java.util.Map;

//方式二： 访问数据库（xml中写sql）
@Mapper  // 或者@MapperScan将接口扫描装配到容器中
public interface EmployeeMapper {

    //查询所有员工信息
    @Select("select * from employee")
    @Results(value = {
            @Result(column="id", property="id", id=true),
            @Result(column="lastName", property="lastName"),
            @Result(column="email", property="email"),
            @Result(column="gender", property="gender"),
            @Result(column="birth", property="birth"),
            @Result(property="department", one=@One(select="com.webcrud.mapper.DepartmentMapper.getDeparmentById"), column="department")
            })
    public List<Employee> getAll();

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into employee(lastName,email,gender,department,birth) " +
            "values (#{lastName},#{email},#{gender}," +
            "#{department.id},#{birth})")
    public int save(Employee employee);

    @Select("select * from employee where id = #{id}")
    @Results(value = {
            @Result(column="id", property="id", id=true),
            @Result(column="lastName", property="lastName"),
            @Result(column="email", property="email"),
            @Result(column="gender", property="gender"),
            @Result(column="birth", property="birth"),
            @Result(property="department", one=@One(select="com.webcrud.mapper.DepartmentMapper.getDeparmentById"), column="department")
            })
    public Employee getEmployee(Integer id);

    @Delete("delete from employee where id = #{id}")
    public int delete(Integer id);

    //更新为什么要更新部门id
//    @Update("update employee set lastName='灰太狼',email='tailang@163.com',gender=1," +
//            "department=101, birth='2015-10-10' where id=4")
    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender}," +
            "department=#{department.id}, birth=#{birth} where id=#{id}")
    public int update(Employee employee);


}
