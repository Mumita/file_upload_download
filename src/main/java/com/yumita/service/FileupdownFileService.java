package com.yumita.service;

import com.yumita.entity.FileupdownFile;
import java.util.List;

/**
 * (FileupdownFile)表服务接口
 *
 * @author makejava
 * @since 2022-06-29 00:36:36
 */
public interface FileupdownFileService {

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    FileupdownFile queryById(Integer fileId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FileupdownFile> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param fileupdownFile 实例对象
     * @return 实例对象
     */
    FileupdownFile insert(FileupdownFile fileupdownFile);

    /**
     * 修改数据
     *
     * @param fileupdownFile 实例对象
     * @return 实例对象
     */
    FileupdownFile update(FileupdownFile fileupdownFile);

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer fileId);

    List<FileupdownFile> findListByUserId(Integer userId);

    void save(FileupdownFile fileupdownFile);
}