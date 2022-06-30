package com.yumita.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.yumita.entity.FileupdownUser;
import com.yumita.response.Result;
import com.yumita.service.FileupdownUserService;
import com.yumita.token.JwtToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * (FileupdownUser)表控制层
 *
 * @author makejava
 * @since 2022-06-29 22:25:48
 */
@RestController
@RequestMapping("/user")
public class FileupdownUserController {
    /**
     * 服务对象
     */
    @Resource
    private FileupdownUserService fileupdownUserService;

    /**
     * 用户登录
     * */
    @CrossOrigin
    @GetMapping("/login")
    public String login(@RequestBody FileupdownUser user) {
        if (user.getUserPassword() == null || user.getUserUsername() == null) {
            Result result = new Result().setMessage("用户名或密码错误！").setCode(405);
            return JSONUtil.toJsonStr(result);
        }
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();

        /*
         * 利用hutool生成jwt
         * */
        HashMap<String, Object> payload = new HashMap<>();
        // 签发/生效时间
        DateTime now = DateTime.now();
        // 过期时间
        DateTime newTime = now.offsetNew(DateField.MONTH, 1);
        // 设置签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 设置过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        // 负荷
        payload.put("username", user.getUserUsername());
        payload.put("password", user.getUserPassword());
        // 设置签名的算法(123456是hs256算法所需要的key值)
        final JWTSigner signer = JWTSignerUtil.hs256("123456".getBytes());
        // 生成jwt字符串
        String jwt = JWT.create().addPayloads(payload).setSigner(signer).sign();
        System.out.println("jwt字符串"+jwt);
        // 重写JwtToken，并创建提交给shiro
        JwtToken jwtToken = new JwtToken(jwt, user.getUserPassword());
        System.out.println("jwt:"+jwtToken);
        // 交给shiro中的realm进行认证
        try{
            subject.login(jwtToken);
            // 如果认证错误就返回错误响应
        }catch (UnknownAccountException e) {
            return JSONUtil.toJsonStr(new Result().setCode(401).setMessage("用户名错误！"));
        }catch (IncorrectCredentialsException e) {
            return JSONUtil.toJsonStr(new Result().setCode(401).setMessage("密码错误！"));
        }
        //以下都是登录成功的操作
        HashMap<String, Object> map = new HashMap<>();
        FileupdownUser fileUser = fileupdownUserService.getUserByUsername(user.getUserUsername());
        fileUser.setUserPassword(null);
        map.put("user", fileUser);
        map.put("token", jwt);
        return JSONUtil.toJsonStr(new Result().setMessage("登录成功！").setCode(200).setData(map));
    }

    /**
     * 用户注册
     * */
    @CrossOrigin
    @PostMapping("/register")
    public String register(@RequestBody FileupdownUser user) {
        this.fileupdownUserService.insert(user);
        return JSONUtil.toJsonStr(new Result().setMessage("注册成功！").setCode(200));
    }

}