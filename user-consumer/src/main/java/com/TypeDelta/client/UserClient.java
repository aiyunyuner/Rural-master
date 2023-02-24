package com.TypeDelta.client;


import com.TypeDelta.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "USER-SERVER", fallback = UserClientFallback.class)
public interface UserClient {
    @PostMapping("/user/insert")
    User insertOneUser(User user);

    @PostMapping("/user/delete")
    String deleteOneUser(User user);

    @PostMapping("/user/login")
    User loginUser(User user);

    @GetMapping("/user/selectAll")
    List<User> selectAll();

    @PostMapping("/user/userlogin")
    public Map<String, Object> tokenUserLogin(User user);

    @PostMapping("/user/update")
    public String updateUser(User user);

    @GetMapping("/user/selectPage")
    public List<User> userListAndPage(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize);

    @GetMapping("/user/count")
    public Integer countUser();
}
