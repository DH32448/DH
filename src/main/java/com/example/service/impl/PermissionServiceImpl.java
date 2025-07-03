package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.PermissionMapper;
import com.example.entity.dto.Permission;
import com.example.service.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    public Permission findById(Long id){
        return this.query()
                .eq("id",id)
                .one();
    }
}
