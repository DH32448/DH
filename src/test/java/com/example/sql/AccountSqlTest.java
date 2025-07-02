package com.example.sql;

import com.example.dao.AccountMapper;
import com.example.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Slf4j
@SpringBootTest
public class AccountSqlTest {
    @Resource
    AccountMapper accountMapper;
    @Transactional
    @Test
    void insert(){
        Account account = new Account("20250624ium3h8wwx.jpeg", LocalDate.now(), LocalDateTime.now(),"admin@163.com",0,0,LocalDateTime.now(),"123","15684579685",1,LocalDateTime.now(),"1234","admin");
        int insert = accountMapper.insert(account);
        if (insert > 0) log.info("插入成功:{}",account);
        else log.error("插入失败:{}",account);
    }
}
