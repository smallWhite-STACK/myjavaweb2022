package com.atguigu.qqzone.pojo;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-28 12:48
 */
public class UserBasic {
    /*
UserBasic:
    id
    loginid
    nickName
    pwd
    headImg
    */
    private Integer id;
    private String loginId;
    private String nickName;
    private String pwd;
    private String headImg;


    //用户可以查看详情：
    private UserDetail userDetail;  //1:1
    //用户可以拥有多个topic(也就是一个topicList)
    private List<Topic> topicList;  //1:N
    //好友
    private  List<UserBasic> friendList; //M:N

    public UserBasic(){

    }
    public UserBasic(Integer id){
        this.id = id;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<UserBasic> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserBasic> friendList) {
        this.friendList = friendList;
    }
}
