package com.zjh.myblog.controller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.Blog;
import com.zjh.myblog.entity.Comment;
import com.zjh.myblog.entity.Link;
import com.zjh.myblog.entity.vo.BlogQuery;
import com.zjh.myblog.mapper.DashBoardMapper;
import com.zjh.myblog.service.FrontService;
import com.zjh.myblog.utlis.StringUtils;
import com.zjh.myblog.utlis.http.HttpUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther zhengjianghai
 * @create 2022-01-26 9:40
 */
@RestController
@RequestMapping("/f")
public class FrontController extends BaseController {
    private static final String QQ_QUERY_URL = "https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg";

    @Autowired
    FrontService frontService;

    @Autowired
    private DashBoardMapper dashBoardMapper;

    @ApiOperation("查看友链")
    @GetMapping("/link")
    public AjaxResult link() {
        return AjaxResult.success(frontService.selectLinkList());
    }

    @GetMapping("/link/support")
    public AjaxResult supportLink() {
        return AjaxResult.success(frontService.selectSupportLinkList());
    }


    @PutMapping("/link/{id}")
    public AjaxResult linkRedirect(@PathVariable Integer id) {
        return AjaxResult.success(frontService.incrementLinkClick(id));
    }

    /**
     * 申请link
     */
    @PostMapping("/link")
    public AjaxResult insertLink(@RequestBody Link link) {
        return toAjax(frontService.insertLink(link));
    }

//    /**
//     * 网站菜单
//     */
//    @GetMapping("/menus")
//    public AjaxResult menu() {
//        List<FrontMenu> menuList = new ArrayList<>();
//        menuList.add(new FrontMenu("时光轴", 1, false, "/archive"));
//        menuList.add(new FrontMenu("友链", 2, false, "/link"));
//        menuList.add(new FrontMenu("留言", 3, false, "/leaveComment"));
//        menuList.add(new FrontMenu("关于", 4, false, "/about"));
//        return AjaxResult.success(menuList);
//    }

    /**
     * 获取分类
     */
    @GetMapping("/categories")
    public AjaxResult categories() {
        return AjaxResult.success(frontService.selectCategoryList());
    }

    /**
     * 推荐阅读
     */
    @GetMapping("/support")
    public AjaxResult support() {
        return AjaxResult.success(frontService.selectSupportBlogList());
    }

    /**
     * 获取前5热门博客
     */
    @GetMapping("/hot")
    public AjaxResult hot() {
        return AjaxResult.success(frontService.selectHotBlogList());
    }

    /**
     * get tag cloud
     */
    @GetMapping("/tag")
    public AjaxResult tag() {
        return AjaxResult.success(frontService.selectTagList());
    }

    /**
     * 获取轮播图
     */
    @GetMapping("carousel")
    public AjaxResult getCarousel() {
        return AjaxResult.success(frontService.selectCarouselList());
    }

    /**
     * 公告
     */
    @GetMapping("notice")
    public AjaxResult getNotice() {
        return AjaxResult.success(frontService.selectNoticeList());
    }

    /**
     * get comment use info by QQNum
     */
    @GetMapping("comment/qqNum/{qqNum}")
    public AjaxResult getByQQNum(@PathVariable Long qqNum) {
        String json = HttpUtils.sendGet(QQ_QUERY_URL, "uins=" + qqNum, "GBK");
        Map<String, String> qqInfo = new HashMap<>();
        if (!StringUtils.isEmpty(json)) {
            json = json.replaceAll("portraitCallBack|\\\\s*|\\t|\\r|\\n", "");
            json = json.substring(1, json.length() - 1);
            JSONObject object = JSON.parseObject(json);
            JSONArray array = object.getJSONArray(String.valueOf(qqNum));
            qqInfo.put("avatar", "https://q1.qlogo.cn/g?b=qq&nk=" + qqNum + "&s=40");
            qqInfo.put("email", qqNum + "@qq.com");
            qqInfo.put("nickName", array.getString(6));
        }
        return AjaxResult.success(qqInfo);
    }


    @ApiOperation("发表评论")
    @PostMapping("comment")
    public AjaxResult comment(@RequestBody Comment comment) {
        return toAjax(frontService.insertComment(comment));
    }

    /**
     * get comment list by page Id
     */
    @GetMapping("/comment/{id}")
    public AjaxResult commentBlog(@PathVariable Long id) {
        List<Comment> commentList = frontService.selectCommentListByPageId(id);
        return AjaxResult.success(commentList);
    }

    @ApiOperation("点赞评论")
    @PutMapping("/comment/good/{id}")
    public AjaxResult goodComment(@PathVariable Long id) {
        return toAjax(frontService.incrementCommentGood(id));
    }

    @ApiOperation("踩评论")
    @PutMapping("/comment/bad/{id}")
    public AjaxResult badComment(@PathVariable Long id) {
        return toAjax(frontService.incrementCommentBad(id));
    }

    @ApiOperation("点赞博客")
    @PutMapping("/blog/like/{id}")
    public AjaxResult likeBlog(@PathVariable Long id) {
        return AjaxResult.success(frontService.incrementBlogLike(id));
    }

    /**
     * 查看博客
     * @param id
     * @return
     */
    @GetMapping("/blog/{id}")
    public AjaxResult blogDetail(@PathVariable Long id) {
        Blog blog = frontService.selectBlogDetailById(id);
        frontService.incrementBlogClick(id);
        return AjaxResult.success(blog);
    }

    /**
     * 首页
     * @param blogQuery
     * @return
     */
    @GetMapping("/blog")
    public TableDataInfo blog(BlogQuery blogQuery) {
        startPage();
        List<Blog> blogList = frontService.selectBlogList(blogQuery);
        return getDataTable(blogList);
    }

    @GetMapping("/frontBlog")
    public TableDataInfo frontBlog(BlogQuery blogQuery) {
        startPage();
        List<Blog> blogList = frontService.selectBlogList(blogQuery);
        return getDataTable(blogList);
    }

    @GetMapping("/about")
    public AjaxResult about() {
        return AjaxResult.success(frontService.selectAbout());
    }

    @GetMapping("/archive")
    public TableDataInfo archive(BlogQuery blogQuery) {
        startPage();
        List<Blog> blogList = frontService.selectBlogArchive(blogQuery);
        return getDataTable(blogList);
    }

    @ApiOperation("获取访客人数")
    @GetMapping("/visitCount")
    public AjaxResult visitCount(){
        return AjaxResult.success(dashBoardMapper.getVisitorCount());
    }

}
