package com.tree.someblog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author human1024
 * @Date 2020/6/15 14:44
 * 封装可以公开的信息
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;

    private String username;

    private String avatar;

    private String email;
}
