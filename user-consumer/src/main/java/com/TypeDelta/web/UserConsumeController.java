package com.TypeDelta.web;


import com.TypeDelta.client.UserClient;
import com.TypeDelta.pojo.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Log4j2
@RefreshScope
public class UserConsumeController {

    @Value("${name}")
    private String name;

    @GetMapping("/name")
    public String getName() {
        return name;
    }

    @Autowired
    private UserClient userClient;

    @PostMapping("/insert")
    public User InsertOneUser(User user) {
        log.info(user.toString());
        return userClient.insertOneUser(user);
    }

    @PostMapping("/delete")
    public String deleteOneUser(User user) {
        return userClient.deleteOneUser(user);
    }

    @PostMapping("/login")
    public User loginUser(User user) {
        log.info(user.toString());
        return userClient.loginUser(user);
    }

    @GetMapping("/selectAll")
    public List<User> selectAll() {
        return userClient.selectAll();
    }

    @PostMapping("/userlogin")
    public Map<String, Object> tokenUserLogin(User user) {
        log.info(user.toString());
        return userClient.tokenUserLogin(user);
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        return userClient.updateUser(user);
    }

    @GetMapping("/selectPage")
    public List<User> userListAndPage(int currentPage, int pageSize) {
        return userClient.userListAndPage(currentPage, pageSize);
    }

    @GetMapping("/count")
    public Integer countUser() {
        return userClient.countUser();
    }
}
