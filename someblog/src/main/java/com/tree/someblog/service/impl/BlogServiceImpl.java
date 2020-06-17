package com.tree.someblog.service.impl;

import com.tree.someblog.entity.Blog;
import com.tree.someblog.mapper.BlogMapper;
import com.tree.someblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
