package com.yumita.service;

import com.yumita.entity.FileupdownUser;
import java.util.List;

/**
 * (FileupdownUser)表服务接口
 *
 * @author makejava
 * @since 2022-06-29 22:25:46
 */
public interface FileupdownUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    FileupdownUser queryById(Integer userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FileupdownUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param fileupdownUser 实例对象
     * @return 实例对象
     */
    FileupdownUser insert(FileupdownUser fileupdownUser);

    /**
     * 修改数据
     *
     * @param fileupdownUser 实例对象
     * @return 实例对象
     */
    FileupdownUser update(FileupdownUser fileupdownUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer userId);

    /**
     * 通过用户名查找用户
     */
    FileupdownUser getUserByUsername(String username);


}