package com.yumita.service.impl;

import com.yumita.dao.FileupdownFileDao;
import com.yumita.entity.FileupdownFileAndUser;
import com.yumita.dao.FileupdownFileAndUserDao;
import com.yumita.service.FileupdownFileAndUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FileupdownFileAndUser)表服务实现类
 *
 * @author makejava
 * @since 2022-07-01 00:29:20
 */
@Service("fileupdownFileAndUserService")
public class FileupdownFileAndUserServiceImpl implements FileupdownFileAndUserService {
    @Resource
    private FileupdownFileDao fileupdownFileDao;

    @Resource
    private FileupdownFileAndUserDao fileupdownFileAndUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param ufId 主键
     * @return 实例对象
     */
    @Override
    public FileupdownFileAndUser queryById(Object ufId) {
        return this.fileupdownFileAndUserDao.queryById(ufId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<FileupdownFileAndUser> queryAllByLimit(int offset, int limit) {
        return this.fileupdownFileAndUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param fileupdownFileAndUser 实例对象
     * @return 实例对象
     */
    @Override
    public FileupdownFileAndUser insert(FileupdownFileAndUser fileupdownFileAndUser) {
        this.fileupdownFileAndUserDao.insert(fileupdownFileAndUser);
        return fileupdownFileAndUser;
    }

    /**
     * 修改数据
     *
     * @param fileupdownFileAndUser 实例对象
     * @return 实例对象
     */
    @Override
    public FileupdownFileAndUser update(FileupdownFileAndUser fileupdownFileAndUser) {
        this.fileupdownFileAndUserDao.update(fileupdownFileAndUser);
        return this.queryById(fileupdownFileAndUser.getUfId());
    }

    /**
     * 通过主键删除数据
     *
     * @param ufId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object ufId) {
        return this.fileupdownFileAndUserDao.deleteById(ufId) > 0;
    }

    @Override
    public void save(FileupdownFileAndUser fileupdownFileAndUser, String newFilename) {
        int fileId = this.fileupdownFileDao.selectIdByNewFilename(newFilename);
        fileupdownFileAndUser.setFileId(fileId);
        this.fileupdownFileAndUserDao.insert(fileupdownFileAndUser);
    }
}