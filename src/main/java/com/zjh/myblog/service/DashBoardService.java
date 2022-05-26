package com.zjh.myblog.service;

import com.zjh.myblog.annotation.CacheExpire;
import com.zjh.myblog.entity.DictData;
import com.zjh.myblog.entity.dto.LineChartData;
import com.zjh.myblog.enums.TimeType;
import com.zjh.myblog.mapper.DashBoardMapper;
import com.zjh.myblog.utlis.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-02-07 15:05
 */
@Service
@Slf4j
public class DashBoardService {

    private static final Pattern pattern = Pattern.compile("[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.xyz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)");

    @Autowired
    DashBoardMapper dashBoardMapper;
    @Autowired
    private DictDataService dictDataService;
    @Autowired
    private LinkService linkService;


    private static final String INTERNAL_KEY = "bianxiaofeng.com";

    /**
     * 获取首页Panel Group的数据
     *
     * @return 数据map
     */
    public Map<String, Long> getPanelGroupData() {
        Map<String, Long> resultMap = new HashMap<>();
        Long visitorCount = dashBoardMapper.getVisitorCount();
        Long blogCount = dashBoardMapper.getBlogCount();
        Long categoryCount = dashBoardMapper.getCategoryCount();
        Long commentCount = dashBoardMapper.getCommentCount();
        resultMap.put("visitorCount", visitorCount);
        resultMap.put("blogCount", blogCount);
        resultMap.put("categoryCount", categoryCount);
        resultMap.put("commentCount", commentCount);
        return resultMap;
    }

    /**
     * 根据type获取折线图的数据
     *
     * @param type 类型
     * @return 折线图数据
     */
    @Cacheable(value = "DashBoard", key = "'LineChartData'+ #type")
    @CacheExpire(expire = 3, type = TimeType.HOURS)
    public LineChartData<Long> getLineChartData(String type) {
        LineChartData lineChartData;
        switch (type) {
            case LineChartData.BLOG_LINE:
                lineChartData = getBlogLineChartData();
                break;
            case LineChartData.VISITOR_LINE:
                lineChartData = getVisitorLineChartData();
                break;
            case LineChartData.CLASSIFY_LINE:
                lineChartData = getClassifyLineChartData();
                break;
            case LineChartData.COMMENT_LINE:
                lineChartData = getCommentLineChartData();
                break;

            default:
                lineChartData = new LineChartData();
        }
        log.info("get line chart data \n{}", lineChartData);
        return lineChartData;
    }

    
    @Cacheable(value = "DashBoard", key = "'SpiderData'")
    public List<Map<String, Long>> getSpiderData() {
        return dashBoardMapper.getSpiderData();
    }


    private Map<String, String> getOperateTypeString() {
        List<DictData> operateTypeList = dictDataService.selectDictDataByType("sys_oper_type");
        return operateTypeList.stream().collect(Collectors.toMap(DictData::getDictValue, DictData::getDictLabel));
    }





    /**
     * 获取访问折线图数据
     *
     * @return 折线图数据
     */
    private LineChartData getVisitorLineChartData() {
        //Get days before the current time mark one week.
        List<String> actualDataDayList = DateUtils.getPastDaysList(7);
        List<Long> actualData = new LinkedList<>();
        for (String day : actualDataDayList) {
            Long count = dashBoardMapper.getVisitorCountByCreateTime(day);
            actualData.add(count);
        }
        //Get days before the select time mark one week.
        List<String> expectedDataDayList = DateUtils.getPastDaysList(7, 7);
        List<Long> expectedData = new LinkedList<>();
        for (String day : expectedDataDayList) {
            Long count = dashBoardMapper.getVisitorCountByCreateTime(day);
            expectedData.add(count);
        }
        LineChartData<Long> lineChartData = new LineChartData<>();
        lineChartData.setActualData(actualData);
        lineChartData.setExpectedData(expectedData);
        lineChartData.setAxisData(actualDataDayList);
        return lineChartData;
    }

    private LineChartData getBlogLineChartData() {
        //Get days before the current time mark one week.
        List<String> actualDataDayList = DateUtils.getPastDaysList(7);
        List<Long> actualData = new LinkedList<>();
        for (String day : actualDataDayList) {
            Long count = dashBoardMapper.getBlogCountByCreateTime(day);
            actualData.add(count);
        }
        //Get days before the select time mark one week.
        List<String> expectedDataDayList = DateUtils.getPastDaysList(7, 7);
        List<Long> expectedData = new LinkedList<>();
        for (String day : expectedDataDayList) {
            Long count = dashBoardMapper.getBlogCountByCreateTime(day);
            expectedData.add(count);
        }
        LineChartData<Long> lineChartData = new LineChartData<>();
        lineChartData.setActualData(actualData);
        lineChartData.setExpectedData(expectedData);
        lineChartData.setAxisData(actualDataDayList);
        return lineChartData;
    }

    private LineChartData getClassifyLineChartData() {
        //Get days before the current time mark one week.
        List<String> actualDataDayList = DateUtils.getPastDaysList(7);
        List<Long> actualData = new LinkedList<>();
        for (String day : actualDataDayList) {
            Long count = dashBoardMapper.getCategoryCountByCreateTime(day);
            actualData.add(count);
        }
        //Get days before the select time mark one week.
        List<String> expectedDataDayList = DateUtils.getPastDaysList(7, 7);
        List<Long> expectedData = new LinkedList<>();
        for (String day : expectedDataDayList) {
            Long count = dashBoardMapper.getCategoryCountByCreateTime(day);
            expectedData.add(count);
        }
        LineChartData<Long> lineChartData = new LineChartData<>();
        lineChartData.setActualData(actualData);
        lineChartData.setExpectedData(expectedData);
        lineChartData.setAxisData(actualDataDayList);
        return lineChartData;
    }

    private LineChartData getCommentLineChartData() {
        //Get days before the current time mark one week.
        List<String> actualDataDayList = DateUtils.getPastDaysList(7);
        List<Long> actualData = new LinkedList<>();
        for (String day : actualDataDayList) {
            Long count = dashBoardMapper.getCommentCountByCreateTime(day);
            actualData.add(count);
        }
        //Get days before the select time mark one week.
        List<String> expectedDataDayList = DateUtils.getPastDaysList(7, 7);
        List<Long> expectedData = new LinkedList<>();
        for (String day : expectedDataDayList) {
            Long count = dashBoardMapper.getCommentCountByCreateTime(day);
            expectedData.add(count);
        }
        LineChartData<Long> lineChartData = new LineChartData<>();
        lineChartData.setActualData(actualData);
        lineChartData.setExpectedData(expectedData);
        lineChartData.setAxisData(actualDataDayList);
        return lineChartData;
    }

}
