package com.tree.someblog.util;

import com.tree.someblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author human1024
 * @Date 2020/6/15 15:24
 */
public class ShiroUtil {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
