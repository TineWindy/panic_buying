package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDAO {

    /**
     * 插入一条抢购信息
     * @param
     * @return
     * 返回受影响的行数，1代表插入成功，0代表插入失败
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据主键查询抢购信息
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdAndPhone(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);


}
