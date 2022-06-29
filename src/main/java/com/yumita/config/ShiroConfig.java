package com.yumita.config;

import com.yumita.shiro.realm.UpdownUserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;

@Configuration
public class ShiroConfig {
    @Autowired
    private UpdownUserRealm updownUserRealm;

    /*
    * `SecurityManager即安全管理器`，对全部的subject进行安全管理，它是shiro的核心，负责对所有的subject进行安全管理。
    * */
    @Bean
    public DefaultWebSecurityManager getSecurityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 为安全管理器注入user相关的realm
        manager.setRealm(updownUserRealm);
        return manager;
    }
    /*
     * Shiro过滤器配置
     * `SecurityManager即安全管理器`，对全部的subject进行安全管理，它是shiro的核心，负责对所有的subject进行安全管理。
     * 通过SecurityManager可以完成subject的认证、授权等。
     * 实质上SecurityManager是通过Authenticator进行认证，通过Authorizer进行授权，通过SessionManager进行会话管理等。
     * */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean(); // new一个过滤器对象
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager); //为过滤器对象设置manager
        //设置过滤
        HashMap<String, Filter> jwtFilterMap = new HashMap<>();
        //设置jwtFilter过滤器，用来过滤从前端发送来的带有jwt的请求
        jwtFilterMap.put("jwt", jwtFilter); // 如果请求头带有jwt，就交给jwtFilter处理
        shiroFilterFactoryBean.setFilters(jwtFilterMap);
        HashMap<String, String> filter = new HashMap<>();
        filter.put("/login", "anon"); // 如果请求是"/login"，就放它通过
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filter);

    }
}
