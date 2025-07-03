package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.UserRoleMapper;
import com.example.entity.dto.UserRole;
import com.example.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper,UserRole> implements UserRoleService {
    /**
     * 查询用户角色id
     * @param userId
     * @return
     */
    public List<UserRole> findByUserId(int userId){
        return this.query()
                .eq("user_id",userId)
                .list();
    }
}
