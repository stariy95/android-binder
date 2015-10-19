package com.kendamasoft.binder.utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * Created by stariy on 16.10.15.
 */
public class ReflectionUtils {

    static private final String TAG = ReflectionUtils.class.getSimpleName();

    public static boolean isMethodHasParameter(Method method, Class parameterType) {
        Class<?>[] parameters = method.getParameterTypes();
        return parameters.length >= 1 && parameters[0].isAssignableFrom(parameterType);
    }

    @SuppressWarnings("unchecked")
    public static <T> T safeCallMethod(Object object, Method method, T defaultResult, Object... args) {
        try {
            return (T)method.invoke(object, args);
        } catch (Exception ex) {
            return defaultResult;
        }
    }

    @SuppressWarnings("unchecked")
    public static void safeCallMethodWithoutResult(Object object, Method method, Object... args) {
        try {
            method.invoke(object, args);
        } catch (Exception ex) {
            Log.w(TAG, ex);
        }
    }

    public static String setterNameForField(Field field) {
        return "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
        /*
        if(field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
            return "is" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
        } else {
            return "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
        }
        */
    }

    public static String formatMember(Member member) {
        return member.getDeclaringClass().getSimpleName() + "#" + member.getName();
    }
}
