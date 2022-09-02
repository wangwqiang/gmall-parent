package com.atguigu.starter.cache.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

/**
 * @author wangwqiang
 * date 2022/8/28
 * @version 1.0
 */
public class Jsons {
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 把对象转为json字符串
     * @param object
     * @return
     */
    public static String toStr(Object object) {
        try {
            String s = mapper.writeValueAsString(object);
            return s;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * 将字符串转为对象
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> T toObj(String jsonStr, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonStr)){
            return null;
        }
        T t = null;
        try {
            t = mapper.readValue(jsonStr,clazz);

            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 带复杂泛型的json逆转。这个可以直接兼容
     * toObj(String jsonStr, Class<T> clz)
     * @param jsonStr
     * @param tr
     * @param <T>
     * @return
     */
    public static<T> T toObj(String jsonStr, TypeReference<T> tr){
        if(StringUtils.isEmpty(jsonStr)){
            return null;
        }
        T t = null;
        try {

            t = mapper.readValue(jsonStr, tr);
            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
