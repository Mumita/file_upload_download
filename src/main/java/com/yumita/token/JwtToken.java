package com.yumita.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.springframework.stereotype.Component;

//糅合JWT
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Component
//如果要糅合JWT到token，就需要实现接口
public class JwtToken implements HostAuthenticationToken, RememberMeAuthenticationToken {
    private String token;
    private char[] password;
    private boolean rememberMe;
    private String host;

    public JwtToken(String token) {
        this.token = token;
    }

    public JwtToken(String token, char[] password) {
        this(token, (char[])password, false, (String)null);
    }

    public JwtToken(String token, String password) {
        this(token, (char[])(password != null ? password.toCharArray() : null), false, (String)null);
    }

    public JwtToken(String token, char[] password, String host) {
        this(token, password, false, host);
    }

    public JwtToken(String token, String password, String host) {
        this(token, password != null ? password.toCharArray() : null, false, host);
    }

    public JwtToken(String token, char[] password, boolean rememberMe) {
        this(token, (char[])password, rememberMe, (String)null);
    }

    public JwtToken(String token, String password, boolean rememberMe) {
        this(token, (char[])(password != null ? password.toCharArray() : null), rememberMe, (String)null);
    }

    public JwtToken(String username, String password, boolean rememberMe, String host) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, host);
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public boolean isRememberMe() {
        return false;
    }

    /*
     * 返回主身份信息
     * */
    @Override
    public Object getPrincipal() {
        return token;
    }

    /*
     * 返回凭证
     * */
    @Override
    public Object getCredentials() {
        return password;
    }
}