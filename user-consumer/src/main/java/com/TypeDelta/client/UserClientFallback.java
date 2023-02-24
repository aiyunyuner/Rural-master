package com.TypeDelta.client;


import com.TypeDelta.pojo.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public User insertOneUser(User user) {
        User u = new User();
        u.setError("服务器出错请稍后重试！！！！");
        return u;
    }

    @Override
    public String deleteOneUser(User user) {
        return "服务器出错请稍后重试！！！！";
    }

    @Override
    public User loginUser(User user) {
        User u = new User();
        u.setError("服务器出错请稍后重试！！！！");
        return u;
    }

    @Override
    public List<User> selectAll() {
        ArrayList<User> users = new ArrayList<>();

        User u = new User();
        u.setError("服务器出错请稍后重试！！！！");
        users.add(u);
        return users;
    }

    @Override
    public Map<String, Object> tokenUserLogin(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "服务器错误！！！");
        return map;
    }

    @Override
    public String updateUser(User user) {
        return "服务器出错请稍后重试!!!!";
    }

    @Override
    public List<User> userListAndPage(Integer currentPage, Integer pageSize) {
        ArrayList<User> users = new ArrayList<>();

        User u = new User();
        u.setError("服务器出错请稍后重试！！！！");
        users.add(u);
        return users;
    }

    @Override
    public Integer countUser() {
        return 0;
    }

}
