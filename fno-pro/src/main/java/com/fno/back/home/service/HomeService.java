package com.fno.back.home.service;

import com.fno.back.home.mapper.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/***
 * @des
 * @author Ly
 * @date 2023/6/4
 */

@Service
public class HomeService {

    @Autowired
    private HomeMapper homeMapper;

    public Map<String,Long> getItemCountData(){
        return homeMapper.getItemCountData();
    }


    public List<Map> queryLastUpdateList(){
        return homeMapper.queryLastUpdateList();
    }
}
