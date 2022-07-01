package com.yumita.service;

import com.yumita.entity.FileupdownFileAndUser;
import java.util.List;

/**
 * (FileupdownFileAndUser)表服务接口
 *
 * @author makejava
 * @since 2022-07-01 00:29:20
 */
public interface FileupdownFileAndUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param ufId 主键
     * @return 实例对象
     */
    FileupdownFileAndUser queryById(Object ufId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FileupdownFileAndUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param fileupdownFileAndUser 实例对象
     * @return 实例对象
     */
    FileupdownFileAndUser insert(FileupdownFileAndUser fileupdownFileAndUser);

    /**
     * 修改数据
     *
     * @param fileupdownFileAndUser 实例对象
     * @return 实例对象
     */
    FileupdownFileAndUser update(FileupdownFileAndUser fileupdownFileAndUser);

    /**
     * 通过主键删除数据
     *
     * @param ufId 主键
     * @return 是否成功
     */
    boolean deleteById(Object ufId);

}