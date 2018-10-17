package org.lanqiao.utils;

import org.lanqiao.dao.ResultSetHandle;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplete {
    //增删改操作
    //参数：insert,delete,update
    public  static  void execUpdate(String sql,Object ... args){
        Connection connection =  JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);//手动关闭
             preparedStatement = connection.prepareStatement(sql);//此处传哪个sql进来？就执行哪个sql
            //设置参数
            //对参数进行for循环
            for(int i = 0;i < args.length;i++){
                preparedStatement.setObject(i+1,args[i]);//参数从哪开始
            }
            preparedStatement.executeUpdate();//进行更新操作，更新操作都需要事务
            connection.commit();//手动提交
        } catch (SQLException e) {
            try {
                connection.rollback();//若出现异常则进行回滚操作
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            //释放资源
            JDBCUtils.releaseSource(null,preparedStatement,connection);
        }
    }

    //查询操作
    public static  <T>T  execSelect(String sql, ResultSetHandle<T> rsh, Object ... args){
        Connection connection =  JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            //传递参数(给它一个参数)
            //对参数进行循环(参数设置,Object ... args[])
            for(int i = 0;i < args.length;i++){
                preparedStatement.setObject(i + 1,args[i]);
            }
            //返回一个结果集
            resultSet = preparedStatement.executeQuery();
            //进行结构集处理
            //新建一个处理结果集的接口
            //添加一个结果集处理器
            //结果集的处理
           T t =rsh.handle(resultSet);//得到一个泛型对象
           return t;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
