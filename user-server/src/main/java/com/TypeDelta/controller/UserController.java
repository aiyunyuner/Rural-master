package com.TypeDelta.controller;


import com.TypeDelta.pojo.User;
import com.TypeDelta.service.UserService;
import com.TypeDelta.utils.JWTUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Log4j2
@RefreshScope
/**
 * @Author: TypeDelta
 * 对user的管理
 */
public class UserController {
    @Value("${name}")
    private String name;

    @GetMapping("/name")
    public String getName() {
        return name;
    }

    private Integer result = 0;
    @Autowired
    private UserService userService;

    /**
     * 插入user
     *
     * @param user user信息
     */
    @PostMapping("/insert")
    User insertUser(@RequestBody User user) {
        log.info(user.toString());
        userService.insertUser(user);
        user.setResult(1);
        return user;
    }

    /**
     * 删除user对象
     *
     * @param user user中的id信息
     * @return 0失败 1成功
     */
    @PostMapping("/delete")
    String deleteUser(@RequestBody User user) {
        log.info(user.toString());
        if (userService.deleterUser(user) == 1) {
            return "删除成功！";
        } else {
            return "删除失败！";
        }

    }

    @PostMapping("/login")
    User loginUser(@RequestBody User user) {
//        user=;
//        httpServletRequest.getSession().setAttribute("username",);

        return userService.loginUser(user);
    }

    @GetMapping("/selectAll")
    List<User> selectAll() {
        return userService.selectAll();
    }

    @PostMapping("/userlogin")
    public Map<String, Object> tokenUserLogin(@RequestBody User user) {
        log.info(user.toString());
        Map<String, Object> map = new HashMap<>();
        try {
            User loginUser = userService.loginUser(user);
            if (loginUser != null) {
                Map<String, String> payload = new HashMap<>();
                payload.put("id", String.valueOf(loginUser.getId()));
                payload.put("username", loginUser.getName());
                String token = JWTUtils.getToken(payload);
                map.put("statue", true);
                map.put("msg", "认证成功");
                map.put("token", token);
            } else {
                map.put("statue", false);
                map.put("msg", "认证失败");
            }

        } catch (Exception e) {
            map.put("statue", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @PostMapping("/test")
    public Map<String, Object> test(String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("statue", true);
        map.put("msg", "认证成功");
        return map;
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody User user) {
        if (userService.updateUser(user) == 1) {
            return "修改成功！";
        } else {
            return "修改失败！";
        }
    }

    @GetMapping("/selectPage")
    public List<User> userListAndPage(int currentPage, int pageSize) {
        return userService.userListAndPage((currentPage - 1) * pageSize, pageSize);
    }

    @GetMapping("/count")
    public Integer countUser() {
        return userService.countUser();
    }
}
