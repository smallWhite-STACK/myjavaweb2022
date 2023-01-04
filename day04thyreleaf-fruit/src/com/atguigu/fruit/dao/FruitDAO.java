package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    /**
     * 查询库存列表
     * @param pageNo 当前页码
     * @return
     */
    List<Fruit> getFruitList(String keyword , int pageNo);

    //新增库存
    boolean addFruit(Fruit fruit);

    //修改库存
    boolean updateFruit(Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //删除特定库存记录
    boolean delFruit(String fname);

    //
    Fruit getFruitByFid(Integer id);

    //通过id删除fruit
    void delFruitByFid(int id);

    //获取总的条目数
    int getFruitCount(String keyword);
}
