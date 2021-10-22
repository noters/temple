package com.development.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class StringUtil {

    public static String getRandom36() {
        String uuid = UUID.randomUUID().toString();
        return uuid.toLowerCase();
    }

    public static String getRandom32() {
        return getRandom36().replaceAll("-", "");
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getRenminbiCent(String price) {
        BigDecimal bigDecimal = new BigDecimal(price);
        BigDecimal cent = new BigDecimal("100");
        BigDecimal result = bigDecimal.multiply(cent);
        return result.stripTrailingZeros().toPlainString();
    }

    public static Map<String, String> getObjectToMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new LinkedHashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = field.get(obj);
            if (fieldValue == null){
                fieldValue = "";
            }
            String value = String.valueOf(fieldValue);
            String key = fieldName.replaceAll("[A-Z]", "_$0").toLowerCase();
            map.put(key, value);
        }
        return map;
    }
}
