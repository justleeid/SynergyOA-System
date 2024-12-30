package com.fno.back.t.service;

import java.util.List;
import java.util.Map;

import com.fno.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.t.mapper.TEnterpriseMapper;
import com.fno.back.t.domain.TEnterprise;
import com.fno.back.t.service.TEnterpriseService;

/**
 * 企业Service业务层处理
 *
 * @author fno
 * @date 2024-02-27
 */
@Service
public class TEnterpriseService
{
    @Autowired
    private TEnterpriseMapper tEnterpriseMapper;

    /**
     * 查询企业
     *
     * @param id 企业主键
     * @return 企业
     */
    public TEnterprise selectTEnterpriseById(Long id)
    {
        return tEnterpriseMapper.selectTEnterpriseById(id);
    }

    /**
     * 查询企业列表
     *
     * @param tEnterprise 企业
     * @return 企业
     */
    public List<TEnterprise> selectTEnterpriseList(TEnterprise tEnterprise)
    {
        return tEnterpriseMapper.selectTEnterpriseList(tEnterprise);
    }

    /**
     * 新增企业
     *
     * @param tEnterprise 企业
     * @return 结果
     */
    public int insertTEnterprise(TEnterprise tEnterprise)
    {
                tEnterprise.setCreateTime(DateUtils.getNowDate());
            return tEnterpriseMapper.insertTEnterprise(tEnterprise);
    }

    /**
     * 修改企业
     *
     * @param tEnterprise 企业
     * @return 结果
     */
    public int updateTEnterprise(TEnterprise tEnterprise)
    {
                tEnterprise.setUpdateTime(DateUtils.getNowDate());
        return tEnterpriseMapper.updateTEnterprise(tEnterprise);
    }

    /**
     * 批量删除企业
     *
     * @param ids 需要删除的企业主键
     * @return 结果
     */
    public int deleteTEnterpriseByIds(Long[] ids)
    {
        return tEnterpriseMapper.deleteTEnterpriseByIds(ids);
    }

    /**
     * 删除企业信息
     *
     * @param id 企业主键
     * @return 结果
     */
    public int deleteTEnterpriseById(Long id)
    {
        return tEnterpriseMapper.deleteTEnterpriseById(id);
    }


    /****
     * 查询所有年份
     * @return
     */
    public List<String> getYear(){
        return tEnterpriseMapper.getYear();
    }


    public List<Map> getData(){
        return tEnterpriseMapper.getData();
    }

    public List<Map> getPTData(){
        return tEnterpriseMapper.getPTData();
    }

    public List<Map> getPlaceData(){
        return tEnterpriseMapper.getPlaceData();
    }
}
