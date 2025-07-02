package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 账户实体类
 */
@Data
@NoArgsConstructor
@TableName("account") // 映射数据表名称
public class Account {

    @TableId(type = IdType.AUTO) // 主键自增
    private Long id;

    private String avatarUrl; // 头像URL

    private LocalDate birthday; // 生日

    @TableField("create_time") // 创建时间字段映射
    private LocalDateTime createTime;

    private String email; // 邮箱

    private Integer gender; // 性别（0-未知，1-男，2-女）

    @TableLogic // 逻辑删除标记
    @TableField("is_deleted")
    private Integer deleted; // 逻辑删除标记（0-未删除，1-已删除）

    @TableField("last_login_time") // 最后登录时间
    private LocalDateTime lastLoginTime;

    private String password; // 加密密码

    private String phone; // 手机号

    private Integer status; // 账户状态（1-正常，0-冻结，2-注销）

    @TableField("update_time") // 更新时间
    private LocalDateTime updateTime;

    @TableField("user_id") // 用户唯一标识
    private String userId;

    @TableField("username") // 用户名
    private String username;

    public Account(String avatarUrl, LocalDate birthday, LocalDateTime createTime, String email, Integer gender, Integer deleted, LocalDateTime lastLoginTime, String password, String phone, Integer status, LocalDateTime updateTime, String userId, String username) {
        this.avatarUrl = avatarUrl;
        this.birthday = birthday;
        this.createTime = createTime;
        this.email = email;
        this.gender = gender;
        this.deleted = deleted;
        this.lastLoginTime = lastLoginTime;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.updateTime = updateTime;
        this.userId = userId;
        this.username = username;
    }
}
