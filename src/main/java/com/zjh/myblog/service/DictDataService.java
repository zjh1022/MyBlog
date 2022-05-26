package com.zjh.myblog.service;

import com.zjh.myblog.entity.DictData;
import com.zjh.myblog.mapper.DictDataMapper;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 字典数据
 * @auther zhengjianghai
 * @create 2022-01-27 17:18
 */
@Service
public class DictDataService {

    @Autowired
    private DictDataMapper dictDataMapper;

    /**
     *查询字典数据集合
     * @param dictData
     * @return
     */
    public List<DictData> selectDictDataList(DictData dictData) {
        return dictDataMapper.selectDictDataList(dictData);
    }


    /**
     *根据字典类型查询字典数据
     * @param dictType
     * @return
     */
    public List<DictData> selectDictDataByType(String dictType) {
        return dictDataMapper.selectDictDataByType(dictType);
    }


    /**
     *根据字典类型和字典键值查询字典数据信息
     * @param dictType
     * @param dictValue
     * @return
     */
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     * @param dictCode
     * @return
     */
    public DictData selectDictDataById(Long dictCode) {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 根据字典数据ID删除信息
     * @param dictCode
     * @return
     */
    public int deleteDictDataById(Long dictCode) {
        String loginUsername = SecurityUtils.getUsername();
        return dictDataMapper.deleteDictDataById(dictCode, loginUsername);
    }

    /**
     *新增保存字典数据信息
     * @param dictData
     * @return
     */
    public int insertDictData(DictData dictData) {
        return dictDataMapper.insertDictData(dictData);
    }

    /**
     * 修改保存字典数据信息
     * @param dictData
     * @return
     */
    public int updateDictData(DictData dictData) {
        return dictDataMapper.updateDictData(dictData);
    }
}
