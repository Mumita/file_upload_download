package com.yumita.dao;

import com.yumita.entity.FileupdownFileAndUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileupdownFileAndUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param ufId 主键
     * @return 实例对象
     */
    FileupdownFileAndUser queryById(Object ufId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FileupdownFileAndUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param fileupdownFileAndUser 实例对象
     * @return 对象列表
     */
    List<FileupdownFileAndUser> queryAll(FileupdownFileAndUser fileupdownFileAndUser);

    /**
     * 新增数据
     *
     * @param fileupdownFileAndUser 实例对象
     * @return 影响行数
     */
    int insert(FileupdownFileAndUser fileupdownFileAndUser);

    /**
     * 修改数据
     *
     * @param fileupdownFileAndUser 实例对象
     * @return 影响行数
     */
    int update(FileupdownFileAndUser fileupdownFileAndUser);

    /**
     * 通过主键删除数据
     *
     * @param ufId 主键
     * @return 影响行数
     */
    int deleteById(Object ufId);

}