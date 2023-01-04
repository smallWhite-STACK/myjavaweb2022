package com.atguigu.fruit.dao.impl;


import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;

/**
 * @author xujian
 * @create 2022-12-15 10:32
 */
public class TestImlDAO {
    public static void main(String[] args) {
        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        Fruit fruit = new Fruit(2, "orange", 3, 12,"橘子");

        boolean b = fruitDAO.addFruit(fruit);
        System.out.println(b?"插入成功":"失败");

    }
}
