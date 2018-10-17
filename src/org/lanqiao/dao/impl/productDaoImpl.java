package org.lanqiao.dao.impl;

import org.lanqiao.dao.IProduct;
import org.lanqiao.entity.Product;
import org.lanqiao.utils.JDBCTemplete;
import org.lanqiao.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class productDaoImpl implements IProduct {

    @Override//根据ID查询
    public Product getProductById(int id)  {
        String sql = "select * from product where id = ?";
        Product product = new Product();
        List<Product> list = JDBCTemplete.execSelect(sql,new  BeanListHandle<>(Product.class) , id);
        return list.get(0);
    }

    @Override//根据名字查询
    public List<Product> getProductByName(String productName) throws Exception {
        Connection connection =JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select  * from product where productName like '% "+productName +" %' ";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()){
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setProductName(resultSet.getString("productName"));
            product.setDir_id(resultSet.getInt("Dir_id"));
            productList.add(product);
        }
        JDBCUtils.releaseSource(resultSet,statement,connection);
        return productList;
    }

    @Override//查询所有
    public List<Product> getAll() throws Exception {
        Connection connection =JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select  * from product ";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()){
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setProductName(resultSet.getString("productName"));
            product.setDir_id(resultSet.getInt("Dir_id"));
            productList.add(product);
        }
        JDBCUtils.releaseSource(resultSet,statement,connection);
        return productList;
    }

    @Override//更新
    public void updateProductById(Product product) throws Exception {
        Connection connection =JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "update product set salePrice = "+product.getSalePrice()+"where id ="+product.getId();
        statement.execute(sql);
        JDBCUtils.releaseSource(null,statement,connection);

    }

    @Override//删除
    public void deleteProductById(int id) throws Exception {
        String sql ="delete from product where id = ?";
        JDBCTemplete.execUpdate(sql,id);

    }

    @Override
    public void insertProduct(Product product) {

    }
}
