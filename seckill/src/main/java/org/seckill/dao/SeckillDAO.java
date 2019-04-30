package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

public interface SeckillDAO {


    /**
     * 根据ID查询商品信息
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    List<Seckill> queryAll();

    /**
     * 对某一个商品的库存减一
     * @param seckillId
     * @return
     * 受影响的行数，该函数取值只有1或0
     */
    int reduceNum(@Param("seckillId") long seckillId, @Param("nowTime") Date nowTime);

}
