package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {
    List<UserRole> findByUserId(int userId);
}
