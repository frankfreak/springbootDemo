package com.ffk.springBootDemo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;

import java.io.IOException;

/**
 * Created by zhanghan18 on 17/07/2019.
 */
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //在序列化时忽略值为 null 的属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static String toJsonStr(Object obj) {
        String res = null;
        try{
            res = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e){
            Throwables.propagate(e);
        }
        return res;
    }

    public static <T> T readValue(String jsonStr){
        T res = null;
        try {
            res = mapper.readValue(jsonStr,new TypeReference<T>(){});
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return res;
    }

    public static <T> T readValue(String content, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(content, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("json parse error content: " + content, e);
        }
    }

    public static <T> T readValue(String jsonStr, Class<T> zClass){
        T res = null;
        try {
            res = mapper.readValue(jsonStr, zClass);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return res;
    }

    public static JsonNode readAsJsonNode(String jsonStr){
        JsonNode res = null;
        try {
            res = mapper.readTree(jsonStr);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return res;
    }
}
