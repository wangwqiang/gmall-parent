package com.atguigu.gmall.search;

import com.atguigu.gmall.search.bean.Person;
import com.atguigu.gmall.search.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author wangwqiang
 * date 2022/9/3
 * @version 1.0
 */
@SpringBootTest
public class ESTest {
    @Autowired
    PersonRepository personRepository;

    @Test
    public void queryTest(){
        List<Person> allPerson = personRepository.findAllBy();
//        for (Person person : allPerson) {
//            System.out.println(person);
//        }

        List<Person> personByAddress = personRepository.findPersonByAddress("西安市");
//        for (Person person : personByAddress) {
//            System.out.println(person);
//        }

        List<Person> personList = personRepository.findPersonByAgeGreaterThanAndAddressLike(18, "北京市");
//        for (Person person : personList) {
//            System.out.println(person);
//        }

        List<Person> personList1 = personRepository.findPersonByAgeGreaterThanEqualAndAgeLessThanEqualAndAddressLike(18, 20, "上海市");
//        for (Person person : personList1) {
//            System.out.println(person);
//        }

        List<Person> personByAddr = personRepository.findPersonByAddr("上海市");
        for (Person person : personByAddr) {
            System.out.println(person);
        }
    }

    @Test
    public void save(){
        Person person2 = new Person();
        person2.setId(2L);
        person2.setName("李四");
        person2.setAge(19);
        person2.setAddress("北京市朝阳区");

        Person person3 = new Person();
        person3.setId(3L);
        person3.setName("赵六");
        person3.setAge(20);
        person3.setAddress("西安市高新区");

        Person person4 = new Person();
        person4.setId(4L);
        person4.setName("张三丰");
        person4.setAge(21);
        person4.setAddress("西安市雁塔区");

        Person person5 = new Person();
        person5.setId(5L);
        person5.setName("赵七");
        person5.setAge(18);
        person5.setAddress("上海市松江区");

        personRepository.save(person2);
        personRepository.save(person3);
        personRepository.save(person4);
        personRepository.save(person5);
    }
}
