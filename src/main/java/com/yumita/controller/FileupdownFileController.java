package com.yumita.controller;

import com.yumita.entity.FileupdownFile;
import com.yumita.service.FileupdownFileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (FileupdownFile)表控制层
 *
 * @author makejava
 * @since 2022-06-29 00:36:37
 */
@RestController
@RequestMapping("fileupdownFile")
public class FileupdownFileController {
    /**
     * 服务对象
     */
    @Resource
    private FileupdownFileService fileupdownFileService;


}