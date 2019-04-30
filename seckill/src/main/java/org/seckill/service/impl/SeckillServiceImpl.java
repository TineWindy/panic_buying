package org.seckill.service.impl;

import org.seckill.dao.SeckillDAO;
import org.seckill.dao.SuccessKilledDAO;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatedKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * created by Visionary on 2019/4/22
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillDAO seckillDAO;
    @Autowired
    private SuccessKilledDAO successKilledDAO;

    // TODO
    // TO UNDERSTAND
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String slat = "asduyia&*@+NMFYij!{}[]``[";

    public Seckill queryById(long seckillId) {
        return seckillDAO.queryById(seckillId);
    }

    public List<Seckill> getSeckillList() {
        return seckillDAO.queryAll();
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDAO.queryById(seckillId);

        if(seckill==null) {
            return new Exposer(false, seckillId);
        }

        Date nowTime = new Date();
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        if(nowTime.getTime()>endTime.getTime() || nowTime.getTime()<startTime.getTime()) {
            return new Exposer(false, seckillId, startTime.getTime(), endTime.getTime(), nowTime.getTime());
        }

        String md5 = this.getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "//" + this.slat;
        return (String)DigestUtils.md5DigestAsHex(base.getBytes());
    }

    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillClosedException, RepeatedKillException {
        if(md5==null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }

        // TODO
        // 先进行减库存操作，成功后进行插入抢购信息操作，过程中有情况就抛出异常
        // 插入抢购信息异常如何保证库存回滚？
        // Spring 事务理解
        try {
            Date nowTime = new Date();
            int updateCount = seckillDAO.reduceNum(seckillId, nowTime);
            if(updateCount <= 0) {
                throw new SeckillClosedException("seckill is close");
            } else {
                int insertCount = successKilledDAO.insertSuccessKilled(seckillId, userPhone);
                if(insertCount<=0) {
                    throw new RepeatedKillException("seckill repeated");
                } else {
                    SuccessKilled successKilled = successKilledDAO.queryByIdAndPhone(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner error" + e.getMessage());
        }


    }
}
