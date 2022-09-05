package com.atguigu.gmall.common.constant;

/**
 * @author wangwqiang
 * date 2022/8/30
 * @version 1.0
 */
public class SysRedisConst {

    public static final String SKU_INFO_PREFIX = "sku:info:";
    public static final String NULL_VAL = "x";
    public static final String BLOOM_SKUID = "bloom:skuId";
    public static final String LOCK_SKU_DETAIL = "lock:sku:detail:";
    public static final long SKU_DETAIL_TTL = 60 * 60 * 24 * 7L;
    public static final String CACHE_CATEGORYS = "categorys";
    public static final int SEARCH_PAGE_SIZE = 8;
    public static long NULL_VAL_TTl = 60 * 30L;
}
