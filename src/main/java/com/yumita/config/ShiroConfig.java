package com.yumita.config;

import com.yumita.shiro.filter.JwtFilter;
import com.yumita.shiro.matcher.UserCredentialsMatcher;
import com.yumita.shiro.realm.UpdownUserRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;

@Configuration
public class ShiroConfig {
    // 注入用户realm
    @Bean(name = "updownUserRealm")
    public UpdownUserRealm getUpdownUserRealm() {
        UpdownUserRealm updownUserRealm = new UpdownUserRealm();
        return updownUserRealm;
    }

    //注入自定义的密码匹配器
    @Bean(name = "userCredentialsMatcher")
    public UserCredentialsMatcher getUserCredentialsMatcher() {
        UserCredentialsMatcher userCredentialsMatcher = new UserCredentialsMatcher();
        return userCredentialsMatcher;
    }

    /*
    * `SecurityManager即安全管理器`，对全部的subject进行安全管理，它是shiro的核心，负责对所有的subject进行安全管理。
    * */
    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("updownUserRealm")UpdownUserRealm updownUserRealm, @Qualifier("userCredentialsMatcher") UserCredentialsMatcher userCredentialsMatcher){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 为用户realm设置密码适配器
        updownUserRealm.setCredentialsMatcher(userCredentialsMatcher);
        System.out.println("密码适配chenggon"+userCredentialsMatcher);
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
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager, JwtFilter jwtFilter){
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
        return shiroFilterFactoryBean;
    }

    //设置jwt过滤器
    @Bean
    public JwtFilter getJwtFilter() {
        return new JwtFilter();
    }

    // 开启注解代理
    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
