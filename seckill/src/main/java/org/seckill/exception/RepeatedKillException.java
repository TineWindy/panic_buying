package org.seckill.exception;

/**
 * 重复抢购异常
 * created by Visionary on 2019/4/22
 */
public class RepeatedKillException extends SeckillException {

    public RepeatedKillException(String message) {
        super(message);
    }

    public RepeatedKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
