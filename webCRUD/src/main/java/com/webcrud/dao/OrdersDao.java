package com.webcrud.dao;

import com.webcrud.pojo.Department;
import com.webcrud.pojo.Employee;
import com.webcrud.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class OrdersDao {
    private static Map<Integer, Order> orderMap = null;

    static{
        Date date = new Date();
        orderMap = new HashMap<Integer, Order>();
        orderMap.put(1001, new Order(1001, 10000.00, "张三"));
        orderMap.put(1002, new Order(1002,20000.00,"李四" ));
        orderMap.put(1003, new Order(1003, 30000.00,"王五"));
        orderMap.put(1004, new Order(1004, 40000.00,"赛睿"));
        orderMap.put(1005, new Order(1005,50000.00,"雅典"));
    }

    //查询所有订单
    public Collection<Order> getAll(){
        return orderMap.values();
    }
    public void delete(Integer id){
        orderMap.remove(id);
    }

}
