package com.fno.back.workflow.mapper;


import org.apache.ibatis.annotations.Param;

public interface FlowUserMapper {


    public void deleteMemberShip(@Param("userId") String userId, @Param("groupId") String groupId);



}
