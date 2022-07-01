package com.yumita.controller;

import com.yumita.entity.FileupdownFileAndUser;
import com.yumita.service.FileupdownFileAndUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (FileupdownFileAndUser)表控制层
 *
 * @author makejava
 * @since 2022-07-01 00:29:20
 */
@RestController
@RequestMapping("fileupdownFileAndUser")
public class FileupdownFileAndUserController {
    /**
     * 服务对象
     */
    @Resource
    private FileupdownFileAndUserService fileupdownFileAndUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FileupdownFileAndUser selectOne(Object id) {
        return this.fileupdownFileAndUserService.queryById(id);
    }

}