package com.yumita.dao;

import com.yumita.entity.FileupdownFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (FileupdownFile)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-29 00:36:35
 */
@Mapper
@Repository
public interface FileupdownFileDao {

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    FileupdownFile queryById(Integer fileId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FileupdownFile> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param fileupdownFile 实例对象
     * @return 对象列表
     */
    List<FileupdownFile> queryAll(FileupdownFile fileupdownFile);

    /**
     * 新增数据
     *
     * @param fileupdownFile 实例对象
     * @return 影响行数
     */
    int insert(FileupdownFile fileupdownFile);

    /**
     * 修改数据
     *
     * @param fileupdownFile 实例对象
     * @return 影响行数
     */
    int update(FileupdownFile fileupdownFile);

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 影响行数
     */
    int deleteById(Integer fileId);

}