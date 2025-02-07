package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaBdCar;

/**
 * 车辆管理Mapper接口
 *
 */
public interface OaBdCarMapper 
{
    /**
     * 查询车辆管理
     * 
     * @param id 车辆管理主键
     * @return 车辆管理
     */
    public OaBdCar selectOaBdCarById(Long id);

    /**
     * 查询车辆管理列表
     * 
     * @param oaBdCar 车辆管理
     * @return 车辆管理集合
     */
    public List<OaBdCar> selectOaBdCarList(OaBdCar oaBdCar);

    /**
     * 新增车辆管理
     * 
     * @param oaBdCar 车辆管理
     * @return 结果
     */
    public int insertOaBdCar(OaBdCar oaBdCar);

    /**
     * 修改车辆管理
     * 
     * @param oaBdCar 车辆管理
     * @return 结果
     */
    public int updateOaBdCar(OaBdCar oaBdCar);

    /**
     * 删除车辆管理
     * 
     * @param id 车辆管理主键
     * @return 结果
     */
    public int deleteOaBdCarById(Long id);

    /**
     * 批量删除车辆管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaBdCarByIds(Long[] ids);
}
