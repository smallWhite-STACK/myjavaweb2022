package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.UserBasicDAO;
import com.atguigu.qqzone.dao.impl.UserBasicDAOImpl;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xujian
 * @create 2022-12-28 15:21
 */
public class UserBasicServiceImpl implements UserBasicService {
    private UserBasicDAO userBasicDAO;//需要在applicationContext.xml中配置
    @Override
    public UserBasic login(String loginId, String pwd) {

        UserBasic userBasic = userBasicDAO.getUserBasic(loginId, pwd);
        return userBasic;
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        List<UserBasic> friendList = userBasicDAO.getUserBasicList(userBasic);

        //3.创建一个List(因为friendList不是我们最终结果，因此，我们对查询到的最终结果放在该list中)
        List<UserBasic> friendListEnd = new ArrayList<>(friendList.size());

//        return friendList;//我们获取的这个friendList都是id，因此我们需要进一步查询
        for (int i = 0; i < friendList.size(); i++) {
            //通过遍历list中每一个对象的id,然后借助id查询该对象的详细信息


            //1.先取出对象
            UserBasic userBasic1 = friendList.get(i);
            //2.通过这个对象中的id查询其详细信息
            UserBasic userBasicById = userBasicDAO.getUserBasicById(userBasic1.getId());
            friendListEnd.add(userBasicById);
        }
        return friendListEnd;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        UserBasic userBasicById = userBasicDAO.getUserBasicById(id);
        return userBasicById;
    }
}
