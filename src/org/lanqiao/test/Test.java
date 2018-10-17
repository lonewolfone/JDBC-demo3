package org.lanqiao.test;


import org.lanqiao.dao.IProduct;
import org.lanqiao.dao.impl.productDaoImpl;
import org.lanqiao.entity.Product;



public class Test {
    public static void main(String[] args) throws Exception{
        IProduct iProductp = new productDaoImpl();
        /*//根据ID查询
        Product product = iProductp.getProductById(1);
        System.out.println(product);
        System.out.println("------------------------------------------------------");
        //根据产品名称查询
        List<Product> productList = iProductp.getProductByName("M");
        for (Product product1:productList){
            System.out.println(product1);
        }
        System.out.println("-------------------------------------------------------");
        //查询所有
        List<Product> productAll = iProductp.getAll();
        for (Product productList2:productAll){
            System.out.println(productList2);
        }
        System.out.println("------------------------------------------------------------");
        //更新
        Product product3 = iProductp.getProductById(1);
         product3.setSalePrice(666);
         iProductp.updateProductById(product3);
       //删除
       iProductp.deleteProductById(10);*/
       //基于DQL操作模板的查询
       Product product = iProductp.getProductById(1);
       System.out.println(product);
    }
}
