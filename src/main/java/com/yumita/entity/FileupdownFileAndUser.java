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
public class FileupdownFileAndUser implements Serializable {
    private static final long serialVersionUID = -63625362135245184L;
    //用户、文件关联id
    private Object ufId;
    //文件id
    private Integer fileId;
    //用户id
    private Integer userId;

}