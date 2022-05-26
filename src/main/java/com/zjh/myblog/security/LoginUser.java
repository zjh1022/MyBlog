package com.zjh.myblog.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zjh.myblog.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @auther zhengjianghai
 * @create 2022-01-20 16:12
 */
@Data
@AllArgsConstructor
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginUser implements UserDetails {


    /**
     * token
     */
    private String token;

    /**
     * login time
     */
    private Long loginTime;

    /**
     * expire time
     */
    private Long expireTime;

    /**
     * Login IP address
     */
    private String ip;

    /**
     * location
     */
    private String location;

    /**
     * Browser type
     */
    private String browser;

    /**
     * operating system
     */
    private String os;

    /**
     * Permission list
     */
    private Set<String> permissions;

    /**
     * User information
     */
    private User user;


    public LoginUser() {
    }

    public LoginUser(User user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * Whether the account has not expired, which cannot be verified
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Specifies whether the user is unlocked. Locked users cannot authenticate
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (passwords) have expired, which prevents authentication
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Available, disabled users cannot authenticate
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
