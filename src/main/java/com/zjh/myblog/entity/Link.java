package com.zjh.myblog.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
    
@auther zhengjianghai 
    
@create 2022-01-20 19:26

*/
/**
    * 友链表
    */
@ApiModel(value="友链表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Link extends BaseEntity implements Serializable {
    /**
     * id
     */

    private Long id;

    /**
     * 友链名称
     */
    @Length(min = 3, max = 50, message = "名称长度为{min}~{max}")
    private String title;

    /**
     * 友链地址
     */
    @URL(message = "请输入正确的Url地址")
    private String url;

    /**
     * 友链描述
     */
    @Length(min = 3, max = 500, message = "友链描述长度为{min}~{max}")
    private String description;

    /**
     * 网站图片
     */
    @URL(message = "请输入正确的网站图片地址")
    private String headerImg;

    /**
     * 1表示审核通过,0表示未审核
     */
    private Boolean status;

    /**
     * 是否显示友链
     */
    private Boolean display;

    /**
     * 站长邮箱地址
     */
    @Email(message = "请输入正确的站长邮箱地址")
    private String email;

    /**
     * 权重
     */
    private Long weight;
    /**
     * 用于在首页Panel上显示
     */
    private Boolean support;

    private static final long serialVersionUID = 1L;
}