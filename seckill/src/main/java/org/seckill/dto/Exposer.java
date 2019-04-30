package org.seckill.dto;

/**
 * created by Visionary on 2019/4/22
 */
public class Exposer {

    private boolean exposed;

    private String md5;

    private long seckillId;

    private long start;

    private long end;

    private long now;

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, long start, long end, long now) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.start = start;
        this.end = end;
        this.now = now;
    }
}
