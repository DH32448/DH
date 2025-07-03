package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.RolePermissionMapper;
import com.example.entity.dto.RolePermission;
import com.example.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
    /**
     * 查询角色权限id 角色有多权限
     * @param roleId
     * @return
     */
    public List<RolePermission> findByRoleId(Long roleId){
        return this.query()
                .eq("role_id",roleId)
                .list();
    }
}
