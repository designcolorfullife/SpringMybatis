package com.zhangwei.ibatis.model;

import com.zhangwei.ibatis.model.dao.UserDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by qutan.zw on 2015/9/15.
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    @Override
    public User selectUserByID(String id) {
        return getSqlSession().selectOne("com.zhangwei.ibatis.models.UserMapper.selectUserByID", id);
    }

    @Override
    public List<User> selectAllUser() {
        return getSqlSession().selectList("com.zhangwei.ibatis.models.UserMapper.selectUserByDate");
    }

    @Override
    public int selectUserSizeByDate(Date date) {
        return getSqlSession().selectOne("com.zhangwei.ibatis.models.UserMapper.selectUserSizeByDate", date);
    }

    @Override
    public boolean updateUser(User user) {
//        getSqlSession().update();
        return getSqlSession().update("com.zhangwei.ibatis.models.UserMapper.modifyUser", user)==0 ? false:true;
    }

    @Override
    public int addUser(User user) {
        return getSqlSession().insert("com.zhangwei.ibatis.models.UserMapper.addUser",user);
    }

    @Override
    public boolean delUser(User user) {
        return getSqlSession().delete("com.zhangwei.ibatis.models.UserMapper.delUser",user)==0? false:true;
    }
}
