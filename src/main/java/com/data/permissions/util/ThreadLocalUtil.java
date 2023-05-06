package com.data.permissions.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: TaiLin
 * @Date: 2022/10/10
 * @Version 1.0
 */
@Slf4j
public class ThreadLocalUtil {

    private static final ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(() -> new HashMap<>(10));


    private static final ThreadLocal<String> dataPermissions = ThreadLocal.withInitial(() -> new String());

    public static String getDataPermissions() {
        return dataPermissions.get();
    }

    public static void setDataPermissions(String value) {
        dataPermissions.set(value);
    }
    public static void removeDataPermissions() {
        dataPermissions.remove();
    }


    public static Map<String, Object> getThreadLocal() {
        return threadLocal.get();
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        return map.get(key);
    }

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        map.put(key, value);
    }

    public static void set(Map<String, Object> keyValueMap) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement father = stackTrace[1];
        StackTraceElement log = stackTrace[2];
        String tag = null;
        for (int i = 1; i < stackTrace.length; i++) {
            StackTraceElement e = stackTrace[i];
            if (!e.getClassName().equals(log.getClassName())) {
                tag = e.getClassName() + "." + e.getMethodName();
                break;
            }
        }
        if (tag == null) {
            tag = log.getClassName() + "." + log.getMethodName();

        }
        System.err.println(String.format("My father  is %s.%s", father.getClassName() ,father.getMethodName()));
        System.err.println(String.format("My grandpa is %s",tag));
        Map<String, Object> map = threadLocal.get();
        map.putAll(keyValueMap);
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static <T> T remove(String key) {
        Map<String, Object> map = threadLocal.get();
        return (T) map.remove(key);
    }

}
