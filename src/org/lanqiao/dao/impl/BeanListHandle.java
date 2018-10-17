package org.lanqiao.dao.impl;

import org.lanqiao.dao.ResultSetHandle;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandle<T> implements ResultSetHandle<List<T>>{
    //定义成员属性
    private  Class<T> clazz;
    //构造方法
    public BeanListHandle(Class<T> clazz){
        this.clazz = clazz;
    }

    //实现接口的方法
    @Override
    public List<T> handle(ResultSet resultSet) throws SQLException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {
        //进行结果集的处理
        //1、创建一个List
        List<T> list = new ArrayList<>();
        while (resultSet.next()){
            //创建一个对象
            T t = clazz.newInstance();
            //获取一个包含属性的对象
            BeanInfo bean = Introspector.getBeanInfo(clazz,Object.class);
            //得到一个数组
            PropertyDescriptor[] pds = bean.getPropertyDescriptors();
            //对数组进行for循环
            for(int i = 0;i < pds.length;i++){
                //获取属性名称
                String name = pds[i].getName();
                //获取所有写的方法
                Method method = pds[i].getWriteMethod();
                //执行
                method.invoke(t,resultSet.getObject(name));
            }
            list.add(t);
        }
        return list;
    }
}
