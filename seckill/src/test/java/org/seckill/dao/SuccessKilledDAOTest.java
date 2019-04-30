package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDAOTest {

    @Resource
    private SuccessKilledDAO successKilledDAO;

    @Test
    public void insertSuccessKilled() {
        System.out.println(successKilledDAO.insertSuccessKilled(1000, 1234));
    }

    @Test
    public void queryByIdAndPhone() {
        System.out.println(successKilledDAO.queryByIdAndPhone(1000, 1234));
    }
}