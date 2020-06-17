package com.tree.someblog.service.impl;

import com.tree.someblog.entity.User;
import com.tree.someblog.mapper.UserMapper;
import com.tree.someblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author human1024
 * @since 2020-06-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
