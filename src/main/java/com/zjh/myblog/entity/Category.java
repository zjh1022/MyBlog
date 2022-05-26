package com.zjh.myblog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
    
@auther zhengjianghai 
    
@create 2022-01-20 19:26

*/
/**
    * 分类表
    */
@ApiModel(value="分类表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends BaseEntity implements Serializable {

    private Long id;

    /**
     * 分类名称
     */
    @Length(min = 2, max = 50, message = "分类名称长度为{min}~{max}个字符")
    private String title;

    /**
     * 描述
     */

    private String description;

    /**
     * 是否推荐
     */
    @NotNull(message = "推荐设置不能为空")
    private Boolean support;
    /**
     * 分类的类型
     */
    private Integer type;

    private List<Blog> blogList;
}