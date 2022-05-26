package com.zjh.myblog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**博客相关配置
 * @auther zhengjianghai
 * @create 2022-01-21 14:46
 */
@Component
@ConfigurationProperties("blog")
public class BlogConfig {
    /**
     * application name
     */
    private String name;

    /**
     * 版本
     */
    private String version;

    /**
     * 版权所有
     */
    private String copyrightYear;

    /**
     * 文件上传路径
     */
    private static String profile;

    /**
     * enable address get
     */
    private static boolean addressEnabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        BlogConfig.profile = profile;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        BlogConfig.addressEnabled = addressEnabled;
    }
}
