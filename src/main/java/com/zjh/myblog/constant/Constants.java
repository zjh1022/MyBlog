package com.zjh.myblog.constant;

/**
 * @auther zhengjianghai
 * @create 2022-01-20 17:20
 */
public class Constants {

    private Constants() {
    }

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 退出登录
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码缓存key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码过期时间（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * token
     */
    public static final String TOKEN = "token";

    /**
     * the prefix of token
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * the prefix of token
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * common constant for success
     */
    public static final Boolean SUCCESS = true;
    /**
     * common constant for failed
     */
    public static final Boolean FAILED = false;


    /**
     * 校验返回结果码
     */
    public final static String UNIQUE = "0";
    public final static String NOT_UNIQUE = "1";
}
