package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.UserBasicDAO;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-28 15:04
 */
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {

    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        return super.load("select * from t_user_basic where loginId = ? and pwd = ? " , loginId , pwd);
    }

    /**
     * 获取用户的好友列表，需要sql进行复杂查询
     *  这里我们只是获取fid，只有序号
     * @param userBasic
     * @return
     */
    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {

        ///java.lang.NoSuchFieldException: topicDAO
//        String sql= "select fid from t_friend where uid = ?";
        String sql= "select fid as id from t_friend where uid = ?";
        //注意参数是id不是UserBasic对象
        List<UserBasic> userBasics = super.executeQuery(sql, userBasic.getId());
        return userBasics;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {

        String sql="select * from t_user_basic where id = ?";
        return load(sql, id);
    }
}
