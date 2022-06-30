package com.yumita.service.impl;

import com.yumita.entity.FileupdownFile;
import com.yumita.dao.FileupdownFileDao;
import com.yumita.service.FileupdownFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FileupdownFile)表服务实现类
 *
 * @author makejava
 * @since 2022-06-29 00:36:36
 */
@Service("fileupdownFileService")
public class FileupdownFileServiceImpl implements FileupdownFileService {
    @Resource
    private FileupdownFileDao fileupdownFileDao;

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    @Override
    public FileupdownFile queryById(Integer fileId) {
        return this.fileupdownFileDao.queryById(fileId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<FileupdownFile> queryAllByLimit(int offset, int limit) {
        return this.fileupdownFileDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param fileupdownFile 实例对象
     * @return 实例对象
     */
    @Override
    public FileupdownFile insert(FileupdownFile fileupdownFile) {
        this.fileupdownFileDao.insert(fileupdownFile);
        return fileupdownFile;
    }

    /**
     * 修改数据
     *
     * @param fileupdownFile 实例对象
     * @return 实例对象
     */
    @Override
    public FileupdownFile update(FileupdownFile fileupdownFile) {
        this.fileupdownFileDao.update(fileupdownFile);
        return this.queryById(fileupdownFile.getFileId());
    }

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer fileId) {
        return this.fileupdownFileDao.deleteById(fileId) > 0;
    }
}