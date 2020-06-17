package com.tree.someblog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tree.someblog.common.dto.LoginDto;
import com.tree.someblog.common.dto.UserDto;
import com.tree.someblog.common.lang.Result;
import com.tree.someblog.entity.User;
import com.tree.someblog.service.UserService;
import com.tree.someblog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author human1024
 * @Date 2020/6/15 15:27
 */
@RestController
public class AccountController {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");

        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("用户名或密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

    @PostMapping("/register")
    public Result save(@RequestBody UserDto userDto) {
        /*
        * 只需要用户名，密码，邮箱，其它的都自己注入
        * 其它：头像，暂时不要
        * status;
           created;
           LocalDateTime lastLogin;
        * */
        System.out.println("register come in");
        System.out.println(userDto.toString());
        User user = new User();
        BeanUtil.copyProperties(userDto,user);
        String password = SecureUtil.md5(userDto.getPassword());
        user.setPassword(password)
            .setAvatar(null)
            .setStatus(0)
            .setCreated(LocalDateTime.now())
            .setLastLogin(LocalDateTime.now());
        System.out.println(user.toString());
        boolean isSave = userService.save(user);
        userService.saveOrUpdate(user);
        //注册成功
        if(isSave){
            userDto.setPassword(null);
            return Result.success(userDto);
        }
        //注册失败
        return Result.success(null);
    }

}
