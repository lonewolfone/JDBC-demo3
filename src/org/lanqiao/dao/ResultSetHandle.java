package org.lanqiao.dao;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandle<T> {
    T handle(ResultSet resultSet) throws SQLException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException;//结果集处理后，返回一个T对象
}
