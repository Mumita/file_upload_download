package com.yumita.dao;

import com.yumita.entity.FileupdownUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (FileupdownUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-29 22:25:46
 */
@Mapper
@Repository
public interface FileupdownUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    FileupdownUser queryById(Integer userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FileupdownUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param fileupdownUser 实例对象
     * @return 对象列表
     */
    List<FileupdownUser> queryAll(FileupdownUser fileupdownUser);

    /**
     * 新增数据
     *
     * @param fileupdownUser 实例对象
     * @return 影响行数
     */
    int insert(FileupdownUser fileupdownUser);

    /**
     * 修改数据
     *
     * @param fileupdownUser 实例对象
     * @return 影响行数
     */
    int update(FileupdownUser fileupdownUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

    FileupdownUser getByUsername(String username);
}