package com.atguigu.gmall.search.repository;

import com.atguigu.gmall.model.list.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangwqiang
 * date 2022/9/4
 * @version 1.0
 */
@Repository
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
