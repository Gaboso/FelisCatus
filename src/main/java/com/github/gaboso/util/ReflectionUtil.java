package com.github.gaboso.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtil {

    private ReflectionUtil() {
    }

    public static Class<?> getGenericClass(Object obj, int index) {
        return getGenericClass(obj.getClass(), index);
    }

    private static Class<?> getGenericClass(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();

        if (genType instanceof ParameterizedType) {
            ParameterizedType pramType = (ParameterizedType) genType;
            Type[] params = pramType.getActualTypeArguments();

            if ((params != null) && (params.length > index))
                return (Class<?>) params[index];
        }

        return null;
    }

}