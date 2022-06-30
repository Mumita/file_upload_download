package com.yumita.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class FileupdownUser implements Serializable {
    private static final long serialVersionUID = 857055265997213402L;
    //用户id
    private Integer userId;
    //用户名
    private String userUsername;
    //用户密码
    private String userPassword;
    //用户md5盐
    private String userSalt;

}