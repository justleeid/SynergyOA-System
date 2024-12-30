package com.fno.back.oa.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaBdCarMapper;
import com.fno.back.oa.domain.OaBdCar;

/**
 * 车辆管理Service业务层处理
 * 
 * @author fno
 * @date 2023-05-29
 */
@Service
public class OaBdCarService
{
    @Autowired
    private OaBdCarMapper oaBdCarMapper;

    /**
     * 查询车辆管理
     * 
     * @param id 车辆管理主键
     * @return 车辆管理
     */
    public OaBdCar selectOaBdCarById(Long id)
    {
        return oaBdCarMapper.selectOaBdCarById(id);
    }

    /**
     * 查询车辆管理列表
     * 
     * @param oaBdCar 车辆管理
     * @return 车辆管理
     */
    public List<OaBdCar> selectOaBdCarList(OaBdCar oaBdCar)
    {
        //租户
        oaBdCar.setTenantId(SecurityUtils.getTenantId());
        return oaBdCarMapper.selectOaBdCarList(oaBdCar);
    }

    /**
     * 新增车辆管理
     * 
     * @param oaBdCar 车辆管理
     * @return 结果
     */
    public int insertOaBdCar(OaBdCar oaBdCar)
    {
        oaBdCar.setCreateTime(DateUtils.getNowDate());
        //租户
        oaBdCar.setTenantId(SecurityUtils.getTenantId());
        return oaBdCarMapper.insertOaBdCar(oaBdCar);
    }

    /**
     * 修改车辆管理
     * 
     * @param oaBdCar 车辆管理
     * @return 结果
     */
    public int updateOaBdCar(OaBdCar oaBdCar)
    {
        oaBdCar.setUpdateTime(DateUtils.getNowDate());
        return oaBdCarMapper.updateOaBdCar(oaBdCar);
    }

    /**
     * 批量删除车辆管理
     * 
     * @param ids 需要删除的车辆管理主键
     * @return 结果
     */
    public int deleteOaBdCarByIds(Long[] ids)
    {
        return oaBdCarMapper.deleteOaBdCarByIds(ids);
    }

    /**
     * 删除车辆管理信息
     * 
     * @param id 车辆管理主键
     * @return 结果
     */
    public int deleteOaBdCarById(Long id)
    {
        return oaBdCarMapper.deleteOaBdCarById(id);
    }
}
