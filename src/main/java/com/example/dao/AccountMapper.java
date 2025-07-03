package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.dto.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户数据访问接口
 * 继承MyBatis-Plus的BaseMapper，提供基础CRUD操作
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    // 逻辑删除状态更新方法
    // int updateDeletedStatus(@Param("id") Long id, @Param("deleted") Integer deleted);
}
