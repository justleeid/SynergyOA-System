package com.fno.system.service.impl;

import com.fno.common.utils.SecurityUtils;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkflowIdentityService {

    @Autowired
    private IdentityService identityService;





    /***
     * 添加用户
     * @param userId
     * @param nickName
     */
    public boolean addUser(String userId,String nickName){
        User user = identityService.createUserQuery().userId(userId).tenantId(SecurityUtils.getTenantId().toString()).singleResult();
        if(user==null){
            user = identityService.newUser(userId);
            user.setFirstName(nickName);
            user.setLastName("");
            user.setDisplayName(nickName);
            user.setTenantId(SecurityUtils.getTenantId().toString());
            //保存用户到数据库
            identityService.saveUser(user);
        }
        return true;
    }

    public boolean addUser(String userId,String tenantId,String nickName){
        User user = identityService.createUserQuery().userId(userId).tenantId(tenantId).singleResult();
        if(user==null){
            user = identityService.newUser(userId);
            user.setFirstName(nickName);
            user.setLastName("");
            user.setDisplayName(nickName);
            user.setTenantId(tenantId);
            //保存用户到数据库
            identityService.saveUser(user);
        }
        return true;
    }


    /***
     * 添加职位
     * @param groupKey
     * @param groupName
     */
    public boolean addGroup(String groupId,String groupName){
        Group group = identityService.createGroupQuery().groupId(groupId).singleResult();//Activiti职位
        if(group==null){
            group = identityService.newGroup(groupId);
            group.setName(groupName);
            group.setType("");
            identityService.saveGroup(group);//建立组
        }
        return true;
    }

    /***
     * 创建职位和人员的关系
     * @param userId
     * @param nickName
     * @param groupKey
     * @param groupName
     */
    public boolean createRelationShip(String userId,String nickName,String groupId,String groupName){
        this.addUser(userId,nickName);//创建用户，没有用户会新增
        this.addGroup(groupId,groupName);//创建分组，没有分组的话
        identityService.createMembership(userId,groupId);//建立组和用户关系
        return true;
    }


    /***
     * 移除职位和人员的关系
     * @param userId
     * @param groupId
     */
    public boolean removeRelationShip(String userId, String groupId){
        identityService.deleteMembership(userId, groupId);//删除关系
        return true;
    }

    /***
     * 删除用户
     * @param userId
     */
    public boolean deleteUser(String userId){
        identityService.deleteUser(userId);
        this.deleteUserAllPost(userId);
        return true;
    }

    /***
     * 删除职位
     * @param groupId
     */
    public boolean deleteGroup(String groupId) {
        identityService.deleteGroup(groupId);
        return true;
    }



    /***
     * 删除用户的所有职位
     * @param userId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserAllPost(String userId) {
        List<Group> groupList = identityService.createGroupQuery().groupMember(userId).list();
        if(groupList!=null&&groupList.size()>0){
            for(Group g : groupList){
                identityService.deleteMembership(userId, g.getId());
            }
        }
    }


    /***
     * 删除职位下所有用户
     * @param groupId
     */
    public boolean deleteRoleAllUser(String groupId) {
        List<User> userList = identityService.createUserQuery().memberOfGroup(groupId).list();
        if(userList!=null&&userList.size()>0){
            for(User u : userList){
                identityService.deleteMembership(u.getId(),groupId);
            }
        }
        return true;
    }
}
