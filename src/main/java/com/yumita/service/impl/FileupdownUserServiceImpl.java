package com.yumita.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.yumita.entity.FileupdownUser;
import com.yumita.dao.FileupdownUserDao;
import com.yumita.service.FileupdownUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FileupdownUser)表服务实现类
 *
 * @author makejava
 * @since 2022-06-29 22:25:48
 */
@Service
public class FileupdownUserServiceImpl implements FileupdownUserService {
    @Resource
    private FileupdownUserDao fileupdownUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public FileupdownUser queryById(Integer userId) {
        return this.fileupdownUserDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<FileupdownUser> queryAllByLimit(int offset, int limit) {
        return this.fileupdownUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param fileupdownUser 实例对象
     * @return 实例对象
     */
    @Override
    public FileupdownUser insert(FileupdownUser fileupdownUser) {
        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String salt = IdUtil.randomUUID();// 随机生成盐
        fileupdownUser.setUserSalt(salt);
        String dbPassword = SecureUtil.md5(fileupdownUser.getUserPassword() + salt);
        fileupdownUser.setUserPassword(dbPassword);
        this.fileupdownUserDao.insert(fileupdownUser);
        return fileupdownUser;
    }

    /**
     * 修改数据
     *
     * @param fileupdownUser 实例对象
     * @return 实例对象
     */
    @Override
    public FileupdownUser update(FileupdownUser fileupdownUser) {
        this.fileupdownUserDao.update(fileupdownUser);
        return this.queryById(fileupdownUser.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.fileupdownUserDao.deleteById(userId) > 0;
    }

    @Override
    public FileupdownUser getUserByUsername(String username) {
        return this.fileupdownUserDao.getByUsername(username);
    }
}