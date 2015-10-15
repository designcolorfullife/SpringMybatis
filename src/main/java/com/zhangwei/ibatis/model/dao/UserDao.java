package com.zhangwei.ibatis.model.dao;

import com.zhangwei.ibatis.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by qutan.zw on 2015/9/15.
 */
public interface UserDao {
    User selectUserByID(String id);

    List<User> selectAllUser();

    int selectUserSizeByDate(Date date);

    boolean updateUser(User user);

    int addUser(User user);

    boolean delUser(User user);
}
