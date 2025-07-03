package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.AccountMapper;
import com.example.entity.dto.Account;
import com.example.entity.dto.Permission;
import com.example.entity.dto.RolePermission;
import com.example.entity.dto.UserRole;
import com.example.service.AccountService;
import com.example.service.PermissionService;
import com.example.service.RolePermissionService;
import com.example.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper,Account> implements AccountService {
    @Resource
    UserRoleService userRoleService;
    @Resource
    RolePermissionService rolePermissionService;
    @Resource
    PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.selectByUsernameOrEmailOrPhone(username);
        if (account == null) throw new UsernameNotFoundException("用户名或密码错误");
        //先查用户角色id，根据id再查权限id,再查权限实体
        List<UserRole> byUserId = userRoleService.findByUserId(Integer.parseInt(account.getUserId()));
        //查权限id
        List<RolePermission> byRoleId = byUserId.stream().flatMap(userRole ->
                rolePermissionService.findByRoleId(userRole.getRoleId()).stream())
                .collect(Collectors.toList());
        //权限id查权限实体
        List<Permission> permissionList = byRoleId.stream()
                .map(rolePermission ->
                        permissionService.findById(rolePermission.getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return User
                .withUsername(username)
                .password(account.getPassword())
                .authorities(permissionList.stream().map(permission -> new SimpleGrantedAuthority(permission.getCode())).collect(Collectors.toList()))
                .build();
    }

    /**
     * 查询用户
     * @param username
     * @return
     */
    private Account selectByUsernameOrEmailOrPhone(String username){
        return this.query()
                .eq("username",username).or()
                .eq("email",username).or()
                .eq("phone",username)
                .one();
    }


}
