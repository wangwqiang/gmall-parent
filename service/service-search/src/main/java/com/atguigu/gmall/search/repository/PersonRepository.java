package com.atguigu.gmall.search.repository;

import com.atguigu.gmall.search.bean.Person;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CrudRepository
 * PagingAndSortingRepository
 */
@Repository
public interface PersonRepository extends ElasticsearchRepository<Person,Long> {
    //查询所有
    List<Person> findAllBy();
    //根据地址查询
    List<Person> findPersonByAddress(String address);
    //查询大于18岁 且地址在北京市的人
    List<Person> findPersonByAgeGreaterThanAndAddressLike(Integer age, String address);
    //查询年龄大于等于18 小于等于20 且地址在上海市的人
    List<Person> findPersonByAgeGreaterThanEqualAndAgeLessThanEqualAndAddressLike(Integer age, Integer age2, String address);
    //使用DSL语句查询
    @Query("{\n" +
            "    \"match\": {\n" +
            "      \"address\": \"?0\"\n" +
            "    }\n" +
            "  }")
    List<Person> findPersonByAddr(String address);

}
