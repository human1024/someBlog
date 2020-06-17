package com.tree.someblog.common.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author human1024
 * @Date 2020/6/17 15:40
 */
@Data
@Accessors(chain = true)
public class UserDto implements Serializable {

    private String username;
//暂时没有
//    private String avatar;

    private String email;
    private String password;
}
