package com.webcrud.control;

// 所有/webjars/*,都去classpath:/META-INF/resources/webjars找资源
// webjars 以jar包的方式引入静态资源，引入第三方插件，只要导入maven依赖就可以。参照webjars官网。 访问插件下的js： eg: localhost:8080/webjars/jquery/3.3.1/jquery.js

import com.webcrud.dao.DepartmentDao;
import com.webcrud.dao.EmployeDao;
import com.webcrud.dao.OrdersDao;
import com.webcrud.mapper.DepartmentMapper;
import com.webcrud.mapper.EmployeeMapper;
import com.webcrud.pojo.Department;
import com.webcrud.pojo.Employee;
import com.webcrud.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class IndexConrol {
    private static Logger logger = LoggerFactory.getLogger(IndexConrol.class);

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    //注册
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        if ((!StringUtils.isEmpty(username)) && ("123".equals(password))) {
            System.out.println("##################登陆成功！");
            session.setAttribute("username", username);
            //进入成功页面，按F5 页面提示重新提交，为了防止重新提交，可以使用重定向，也可以在视图解析器中配置
            return "redirect:/main.html";

        } else {
            map.put("msg", "登陆名或密码错误！");
            return "/login.html";
        }
    }

    //获取所有emps
    @GetMapping("emps")
    public String emps(Model model) {
        List<Employee> employees = employeeMapper.getAll();
        System.err.println(employees);
        //放在请求域中
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //来到添加页面,查出所有的部门，在页面显示
        List<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；
    // 要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String fromEmp(Employee employee) {
        System.err.println("保存的员工信息：" + employee);
        //departmentMapper.getDeparmentId();
        //保存员工
        employeeMapper.save(employee);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeMapper.getEmployee(id);
        System.err.println("employee: " + employee);
        model.addAttribute("emp", employee);
        //页面要显示所有的部门列表
        List<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("depts", departments);
        System.err.println(" List<Department>" + departments);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "emp/add";
    }

    //员工修改；需要提交员工id；
    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {
        System.out.println("修改的员工数据：" + employee);
        employeeMapper.update(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeMapper.delete(id);
        return "redirect:/emps";
    }

    //重定向来的，页面中的url 换个浏览器也能登录（登陆就失去作用了），需要采用拦截器来验证
    //注释掉，是因为采用才配置文件配置了视图
   /* @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        System.out.println("################进入/success");
        return "/success";
    }*/


}