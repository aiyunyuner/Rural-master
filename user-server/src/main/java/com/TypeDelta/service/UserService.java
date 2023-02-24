package com.TypeDelta.service;


import com.TypeDelta.mapper.UserMapper;
import com.TypeDelta.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
/**
 * user的服务层
 * @author: TypeDelta
 *
 */
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入user
     *
     * @param user
     */
    public void insertUser(User user) {
        userMapper.addUser(user);
    }

    /**
     * 根据id，删除user
     *
     * @param user user对象中的id
     * @return
     */
    @Transactional
    public int deleterUser(User user) {
        return userMapper.deleterUser(user);
    }

    public User loginUser(User user) {


        return userMapper.loginUser(user);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public List<User> userListAndPage(int startNum, int pageSize) {
        return userMapper.userListAndPage(startNum,pageSize);
    }

    public Integer countUser() {
        return userMapper.countUser();
    }
}
