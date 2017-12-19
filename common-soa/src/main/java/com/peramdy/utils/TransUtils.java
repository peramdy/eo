package com.peramdy.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author peramdy
 * @date 2017/12/15
 */
public class TransUtils {

    /**
     * simple converter
     *
     * @param source
     * @param destination
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> D simpleConverter(S source, Class<D> destination) {

        if (source == null) {
            return null;
        }

        D des = null;
        Field[] fields = source.getClass().getDeclaredFields();
        try {
            des = destination.newInstance();
            for (Field field : fields) {
                String fieldName = field.getName();
                String append = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                String getMethodName = "get" + append;
                String setMethodName = "set" + append;
                Method getMethod = source.getClass().getDeclaredMethod(getMethodName);
                getMethod.setAccessible(true);
                Object getValue = getMethod.invoke(source);
                Method setMethod = destination.getDeclaredMethod(setMethodName, field.getType());
                setMethod.setAccessible(true);
                setMethod.invoke(des, getValue);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return des;
    }

}
