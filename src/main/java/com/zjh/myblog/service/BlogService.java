package com.zjh.myblog.service;

import com.zjh.myblog.constant.CacheConstants;
import com.zjh.myblog.entity.Blog;
import com.zjh.myblog.entity.Comment;
import com.zjh.myblog.entity.Tag;
import com.zjh.myblog.entity.vo.BlogQuery;
import com.zjh.myblog.mapper.BlogMapper;
import com.zjh.myblog.mapper.CommentMapper;
import com.zjh.myblog.mapper.TagMappingMapper;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-02-05 14:22
 */
@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagMappingMapper tagMappingMapper;

    /**
     * 查询博客
     * @param id
     * @return
     */
    public Blog selectBlogById(Long id) {
        Blog blog = blogMapper.selectBlogById(id);
        blog.setTagTitleList(getTagTitleListByBlogId(id));
        return blog;
    }

    /**
     * 查询博客列表
     *
     * @param blog 博客
     * @return 博客集合
     */
    public List<Blog> selectBlogList(Blog blog) {
        List<Blog> blogList = blogMapper.selectBlogList(blog);
        if (blogList.isEmpty()) {
            return blogList;
        }
        for (Blog temp : blogList) {
            temp.setTagTitleList(getTagTitleListByBlogId(temp.getId()));
        }
        return blogList;
    }

    /**
     * 根据id获取title集合
     *
     * @param blogId blog的id
     * @return title集合
     */
    private List<String> getTagTitleListByBlogId(Long blogId) {
        List<Tag> tagList = tagService.selectTagListByBlogId(blogId);
        return tagList.stream().map(Tag::getTitle).collect(Collectors.toList());
    }

    /**
     *新增博客
     * @param blog
     * @return 受影响的行数
     */
    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'BlogList'")
    public int insertBlog(Blog blog) {
        blog.setCreateBy(SecurityUtils.getUsername());
        int count = blogMapper.insertBlog(blog);
        tagService.updateTagMapping(blog.getId(), blog.getTagTitleList());
        return count;
    }


    /**
     * 修改博客
     * @param blog
     * @return
     */
    @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'BlogList'")
    public int updateBlog(Blog blog) {
        blog.setUpdateBy(SecurityUtils.getUsername());
        int count = blogMapper.updateBlog(blog);
        tagService.updateTagMapping(blog.getId(), blog.getTagTitleList());
        return count;
    }

    
//    @Caching(evict = {
//            @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'BlogList'"),
//            @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'HotList'"),
//            @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'SupportList'"),
//    })
//
//    @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'BlogList'")
//    public int deleteBlogByIds(String ids) {
//        String username = SecurityUtils.getUsername();
//        return blogMapper.deleteBlogByIds(ConvertUtils.toStrArray(ids), username);
//    }

    
    @Caching(evict = {
            @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'BlogList'"),
            @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'HotList'"),
            @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'SupportList'"),
            @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'BlogItem_'+#id")
    })
    public int deleteBlogById(Long id) {
        String username = SecurityUtils.getUsername();
        tagMappingMapper.deleteByBlogId(id);
        return blogMapper.deleteBlogById(id, username);
    }




    @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'BlogList'")
    public List<String> selectBlogTagList(String query) {
        Tag tag = new Tag();
        tag.setTitle(query);
        List<Tag> tagList = tagService.selectTagList(tag);
        return tagList.stream().map(Tag::getTitle).collect(Collectors.toList());
    }

    /**
     * 前台查询blog列表
     * @param blogQuery
     * @return
     */
    public List<Blog> selectBlogList(BlogQuery blogQuery) {
        List<Blog> blogList = blogMapper.selectBlogListQuery(blogQuery);
        for (Blog blog : blogList) {
            blog.setTagList(tagService.selectTagListByBlogId(blog.getId()));
        }
        return blogList;
    }

    /**
     *获取前台显示的Blog
     * @param id
     * @return
     */
    public Blog selectBlogDetailById(Long id) {
        Blog blog = blogMapper.selectBlogByIdQuery(id);
        blog.setTagList(tagService.selectTagListByBlogId(id));
        //获取commentList
        blog.setCommentList(commentMapper.selectCommentListByPageId(id));
        //设置点击数量+1
        blogMapper.incrementBlogClick(id);
        return blog;
    }

    /**
     * 获取评论
     *
     * @param id id
     * @return 评论列表
     */
    private List<Comment> getCommentListByPageId(Long id) {
        List<Comment> commentList = commentMapper.selectCommentListByPageId(id);
        for (Comment comment : commentList) {
            if (comment.getParentId() != null) {
                comment.setParentComment(commentMapper.selectCommentById(comment.getParentId()));
            }
        }
        return commentList;
    }

    /**
     * 增加blog的like数量
     * @param id
     * @return
     */
    public int incrementBlogLike(Long id) {
        return blogMapper.incrementBlogLike(id);
    }

    /**
     * 获取blog对应的comment
     * @param id
     * @return
     */
    public List<Comment> selectBlogCommentListByBlogId(Long id) {
        return getCommentListByPageId(id);
    }
}
