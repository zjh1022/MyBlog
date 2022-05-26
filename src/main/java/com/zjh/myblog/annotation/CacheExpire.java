package com.zjh.myblog.annotation;


import com.zjh.myblog.enums.TimeType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

import static com.zjh.myblog.enums.TimeType.SECONDS;

/**
 * @className: CacheExpire
 * @description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheExpire {

    /**
     * expire time, default 60s
     */
    @AliasFor("expire")
    long value() default 60L;

    /**
     * expire time, default 60s
     */
    @AliasFor("value")
    long expire() default 60L;

    /**
     * 时间单位
     *
     * @return
     */
    TimeType type() default SECONDS;

}
