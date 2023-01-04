package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.myssm.base.BaseDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList(String keyword,int pageNo) {

        return super.executeQuery("select * from t_fruit WHERE fname like ? OR remark like ? LIMIT ?,5","%"+keyword+"%","%"+keyword+"%",(pageNo-1)*5);
    }

    @Override
    public Fruit getFruitByFid(Integer id) {
        String sql = "select * from t_fruit where fid = ?";
        Fruit load = load(sql, id);
        return load;
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        int count = executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark()) ;
        //insert语句返回的是自增列的值，而不是影响行数
        //System.out.println(count);
        return count>0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ? " ;
        return super.executeUpdate(sql,fruit.getFcount(),fruit.getFid())>0;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        return super.load("select * from t_fruit where fname like ? ",fname);
    }

    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like ? " ;
        return super.executeUpdate(sql,fname)>0;
    }

    @Override
    public void delFruitByFid(int id) {
        String sql = "DELETE FROM t_fruit where fid = ?";
        executeUpdate(sql, id);
    }

    @Override
    public int getFruitCount(String keyword) {
        String sql = "SELECT COUNT(*) FROM t_fruit WHERE fname like ? OR remark like ?";
        int count= ((Long) super.executeComplexQuery(sql,"%"+keyword+"%","%"+keyword+"%")[0]).intValue();
        return count;
    }
}