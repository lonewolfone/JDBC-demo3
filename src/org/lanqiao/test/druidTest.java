package org.lanqiao.test;

import org.junit.Test;
import org.lanqiao.utils.JDBCUtils;

import java.sql.Connection;

public class druidTest {

    @Test
    public void testGetConnection(){
            Connection connection = JDBCUtils.getConnection();
            System.out.println(connection);
    }
}
