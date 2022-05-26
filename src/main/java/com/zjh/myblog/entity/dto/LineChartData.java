package com.zjh.myblog.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @className: LineChartData
 * @description: 前端Dashboard折线图数据
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class LineChartData<T> implements Serializable {
    public static final String BLOG_LINE = "blog";
    public static final String COMMENT_LINE = "comment";
    public static final String CLASSIFY_LINE = "classify";
    public static final String VISITOR_LINE = "visitor";
    /**
     * 期望值,即前一段时间的值
     */
    List<T> expectedData;
    /**
     * 实际值,即当前时间的值
     */
    List<T> actualData;
    /**
     * X轴数据
     */
    List<String> axisData;
}
