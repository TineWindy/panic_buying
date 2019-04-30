package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDAOTest {

    @Autowired(required = false)
    private SeckillDAO seckillDAO;

    @Test
    public void queryById() {
        Seckill seckill = seckillDAO.queryById(1000);
        System.out.println(seckill);
    }

    @Test
    public void queryAll() {
        List<Seckill> seckillList = seckillDAO.queryAll();
        for(Seckill i : seckillList) {
            System.out.println(i);
        }
    }

    @Test
    public void reduceNum() {
        int res = seckillDAO.reduceNum(1000);
        System.out.println(seckillDAO.queryById(1000));
    }
}