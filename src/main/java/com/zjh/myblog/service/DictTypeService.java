package com.zjh.myblog.service;

import com.zjh.myblog.constant.Constants;
import com.zjh.myblog.entity.DictType;
import com.zjh.myblog.mapper.DictDataMapper;
import com.zjh.myblog.mapper.DictTypeMapper;
import com.zjh.myblog.utlis.SecurityUtils;
import com.zjh.myblog.utlis.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description 字典类型
 * @auther zhengjianghai
 * @create 2022-01-27 17:18
 */
@Service
public class DictTypeService {
    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    public List<DictType> selectDictTypeList(DictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    public List<DictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    public DictType selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    /**
     * 根据字典类型查询信息
     * @param dictType
     * @return
     */
    public DictType selectDictTypeByType(String dictType) {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    /**
     *通过字典ID删除字典信息
     * @param dictId
     * @return
     */
    public int deleteDictTypeById(Long dictId) {
        String loginUsername = SecurityUtils.getUsername();
        return dictTypeMapper.deleteDictTypeById(dictId, loginUsername);
    }

    /**
     * 新增字典信息
     * @param dictType
     * @return
     */
    public int insertDictType(DictType dictType) {
        return dictTypeMapper.insertDictType(dictType);
    }

    
    @Transactional
    public int updateDictType(DictType dictType) {
        DictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        return dictTypeMapper.updateDictType(dictType);
    }

    /**
     *校验字典类型称是否唯一
     * @param dict
     * @return
     */
    public String checkDictTypeUnique(DictType dict) {
        Long dictId = StringUtils.isNull(dict.getId()) ? -1L : dict.getId();
        DictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getId().longValue() != dictId.longValue()) {
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }
}
