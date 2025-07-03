package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.RolePermission;

import java.util.List;

public interface RolePermissionService extends IService<RolePermission> {
    List<RolePermission> findByRoleId(Long roleId);
}
