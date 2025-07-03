package com.example.sql;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.dao.AccountMapper;
import com.example.entity.dto.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Slf4j
@SpringBootTest
public class AccountSqlTest {
    @Resource
    AccountMapper accountMapper;
    @Test
    void insert(){
        Account account = new Account("20250624ium3h8wwx.jpeg", LocalDate.now(), LocalDateTime.now(),"admin@163.com",0,0,LocalDateTime.now(),"123","15684579685",1,LocalDateTime.now(),"1234","admin");
        int insert = accountMapper.insert(account);
        if (insert > 0) log.info("插入成功:{}",account);
        else log.error("插入失败:{}",account);
    }
    @Test
    void updateIsDelete(){
        UpdateWrapper<Account> updateWrapper = Wrappers.<Account>update()
                .set("is_deleted", 0)
                .eq("username", "admin");

        int update = accountMapper.update(null, updateWrapper);
        if (update > 0) {
            log.info("更新成功: {}", updateWrapper);
        } else {
            log.warn("更新失败或无匹配记录: {}", updateWrapper);
        }
    }
}
