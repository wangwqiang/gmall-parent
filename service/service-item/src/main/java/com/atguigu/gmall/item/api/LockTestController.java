package com.atguigu.gmall.item.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.lock.RedisDistLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wangwqiang
 * date 2022/8/30
 * @version 1.0
 */
@RestController
@RequestMapping("/lock")
public class LockTestController {
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    RedisDistLock redisDistLock;

    int i;
    /**
     *读写锁，写数据
     * @return
     */
    @GetMapping("/rw/write")
    public Result readWriteValue(){
        //1.拿到读写锁
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("rw-lock");
        //2.获取到写锁
        RLock writeLock = readWriteLock.writeLock();
        //3.加写锁
        writeLock.lock();
        //4.操作业务
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
        i = 888;
        //5.解锁
        writeLock.unlock();
        return Result.ok();
    }

    /**
     * 读写锁，读数据
     * @return
     */
    @GetMapping("/rw/read")
    public Result readValue(){
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("rw-lock");
        RLock rLock = readWriteLock.readLock();
        rLock.lock();
        int x = i;
        rLock.unlock();
        return Result.ok(x);
    }

}
