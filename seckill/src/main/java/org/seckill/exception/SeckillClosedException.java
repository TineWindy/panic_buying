package org.seckill.exception;

/**
 * 抢购关闭异常
 * created by Visionary on 2019/4/22
 */
public class SeckillClosedException extends SeckillException {

    public SeckillClosedException(String message) {
        super(message);
    }

    public SeckillClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}
