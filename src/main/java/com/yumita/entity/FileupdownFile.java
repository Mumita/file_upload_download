package com.yumita.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class FileupdownFile implements Serializable {
    private static final long serialVersionUID = 388537682468068727L;
    //文件id
    private Integer fileId;
    //旧文件名称
    private String fileOldfilename;
    //新文件名称
    private String fileNewfilename;
    //文件后缀
    private String fileExt;
    //文件路径
    private String filePath;
    //文件大小
    private String fileSize;
    //文件种类
    private String fileType;
    //是否为图片
    private String fileIsimg;
    //下载次数
    private Integer fileDowncounts;
    //上传时间
    private Date fileUploadtime;
}