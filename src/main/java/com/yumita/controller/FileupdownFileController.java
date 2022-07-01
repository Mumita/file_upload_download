package com.yumita.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.IdUtil;
import com.yumita.entity.FileupdownFile;
import com.yumita.entity.FileupdownFileAndUser;
import com.yumita.entity.FileupdownUser;
import com.yumita.service.FileupdownFileAndUserService;
import com.yumita.service.FileupdownFileService;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("file")
public class FileupdownFileController {
    @Resource
    private FileupdownFileService fileupdownFileService;
    @Resource
    private FileupdownFileAndUserService fileupdownFileAndUserService;

    @PostMapping("upload")
    public String upload(MultipartFile file, HttpSession session) {
        FileupdownUser user = (FileupdownUser) session.getAttribute("user");
        System.out.println("user："+user);
        // 获取文件的原始文件名
        System.out.println("file："+file);
        String originalFileName = file.getOriginalFilename();
        // 获取文件后缀
        String ext = "." + FileNameUtil.extName(originalFileName);
        // 生成新文件名称
        String newFileName = DateUtil.now()
                .replace("-", "")
                .replace(":", "").replace(" ", "")
                + IdUtil.randomUUID().replace("-", "")
                + ext;
        // 获取文件大小
        String size = Convert.toStr(file.getSize());
        // 获取文件种类
        String contentType = file.getContentType();
        // 是否为图片
        String isImg = contentType.contains("image")?"是":"否";

        // 处理文件上传
        try {
            // 获取文件本地存储位置
            String filesPath = ResourceUtils.getURL("classpath:").getPath()+ "/static/files";
            System.out.println(filesPath);
            // 根据日期生成逐个文件夹
            String datePath = filesPath
                    + "/"
                    + DateUtil.today().replace("-", "");
            System.out.println(datePath);
            // 创建文件夹文件对象
            File dataDir = new File(datePath);
            System.out.println(dataDir+"======"+dataDir.getPath());
            // 判断是否存在，若不存在则创建多级目录
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }
            // 将文件上传到本地
            file.transferTo(new File(dataDir , newFileName));
            // 将文件数据保存到数据库
            FileupdownFile fileupdownFile = new FileupdownFile()
                    .setFileNewfilename(newFileName)
                    .setFileSize(size)
                    .setFileOldfilename(originalFileName)
                    .setFileExt(ext)
                    .setFilePath("/files" + "/" + DateUtil.today().replace("-", ""))
                    .setFileType(contentType)
                    .setFileUploadtime(new Date())
                    .setFileDowncounts(0)
                    .setFileIsimg(isImg);
            this.fileupdownFileService.save(fileupdownFile);
            FileupdownFileAndUser fileupdownFileAndUser = new FileupdownFileAndUser().setUserId(user.getUserId());
            this.fileupdownFileAndUserService.save(fileupdownFileAndUser, newFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/file/showAll";
    }

    /*
    * 进入文件管理页面
    * */
    @RequestMapping("showAll")
    public String showAll(HttpSession session) {
        FileupdownUser user = (FileupdownUser) session.getAttribute("user");
        ArrayList<FileupdownFile> listByUserId = (ArrayList<FileupdownFile>) this.fileupdownFileService.findListByUserId(user.getUserId());
        session.setAttribute("fileList", listByUserId);
        session.setAttribute("fileListSize", listByUserId.size());
        return "/showAll";
    }
}