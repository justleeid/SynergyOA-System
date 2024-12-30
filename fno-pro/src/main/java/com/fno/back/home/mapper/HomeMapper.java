package com.fno.back.home.mapper;

import java.util.List;
import java.util.Map;

/***
 * @des
 * @author Ly
 * @date 2023/6/4
 */

public interface HomeMapper {


    public Map<String,Long> getItemCountData();


    public List<Map> queryLastUpdateList();
}
