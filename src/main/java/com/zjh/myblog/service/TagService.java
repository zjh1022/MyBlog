package com.zjh.myblog.service;

import com.zjh.myblog.entity.Tag;
import com.zjh.myblog.entity.TagMapping;
import com.zjh.myblog.mapper.TagMapper;
import com.zjh.myblog.utlis.ConvertUtils;
import com.zjh.myblog.utlis.SecurityUtils;
import com.zjh.myblog.utlis.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-02-05 14:37
 */
@Service
public class TagService {
    @Autowired
    TagMapper tagMapper;


    /**
     * 获取Tag列表
     * @param tag
     * @return
     */
    public List<Tag> selectTagList(Tag tag) {
        return tagMapper.selectTagList(tag);
    }

    
    public int insertTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }

    
    public Tag selectTagById(Long id) {
        return tagMapper.selectTagById(id);
    }

    
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    
    public int deleteTagByIds(String ids) {
        return tagMapper.deleteTagByIds(ConvertUtils.toLongArray(ids), SecurityUtils.getUsername());
    }

    /**
     *删除Tag的mapping,里面设置的有哪个字段的值,便以那个值作为条件进行删除
     * @param tagMapping
     * @return
     */
    public int deleteTagMapping(TagMapping tagMapping) {
        return tagMapper.deleteTagMapping(tagMapping);
    }

    /**
     * 根据Tag的title 和 type搜索Tag
     * @param title
     * @return
     */
    public Tag selectTagByTitle(String title) {
        return tagMapper.selectTagByTitle(title);
    }

    /**
     * 新增Tag Mapping映射关系
     * @param tagMapping
     * @return
     */
    public int insertTagMapping(TagMapping tagMapping) {
        return tagMapper.insertTagMapping(tagMapping);
    }

    /**
     * 更新TagMapping
     * @param id
     * @param tagTitleList
     */
    public void updateTagMapping(Long id, List<String> tagTitleList) {
        //删除该Id下的所有关联
        TagMapping tagMapping = TagMapping.builder()
                .build();
        deleteTagMapping(tagMapping);

        if (ObjectUtils.isNotEmpty(tagTitleList)) {
            for (String title : tagTitleList) {
                //搜索所有的tag
                Tag tag = selectTagByTitle(title.trim());
                if (tag != null) {
                    tagMapping.setTagId(tag.getId());
                    tagMapping.setBlogId(id);
                    insertTagMapping(tagMapping);
                } else {
                    Tag temp = new Tag(title.trim(), StringUtils.format("rgba({}, {}, {}, {})", getRandomNum(255), getRandomNum(255), getRandomNum(255), 1));
                    insertTag(temp);
                    tagMapping.setBlogId(id);
                    tagMapping.setTagId(temp.getId());
                    insertTagMapping(tagMapping);
                }
            }
        }
    }

    
    public List<Tag> selectTagListByBlogId(Long id) {
        return tagMapper.selectTagListByType(id);
    }

    /**
     * 获取随机数
     *
     * @param num 最大范围
     * @return 随机数
     */
    private int getRandomNum(int num) {
        Random random = new Random();
        return random.nextInt(num);
    }

}
