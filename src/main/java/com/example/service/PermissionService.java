package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.Permission;

public interface PermissionService extends IService<Permission> {
    Permission findById(Long id);
}
