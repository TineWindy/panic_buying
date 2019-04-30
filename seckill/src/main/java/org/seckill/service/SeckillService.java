package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatedKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * 抢购业务接口设计
 * created by Visionary on 2019/4/22
 */
public interface SeckillService {

    /**
     * 根据 ID 获取 Seckill
     *
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);


    List<Seckill> getSeckillList();

    /**
     * url 暴露
     *
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);


    /**
     * 执行抢购，
     * 使用 md5 加密验证
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillClosedException, RepeatedKillException;

}
