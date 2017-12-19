package com.peramdy.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author peramdy
 * @date 2017/12/18
 */
public class SerializeUtils {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();
    private static Objenesis objenesis = new ObjenesisStd(true);


    public static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.getSchema(clazz);
            if (schema != null) {
                cachedSchema.put(clazz, schema);
            }
        }
        return schema;
    }

    /**
     * serialize object
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> byte[] serializeObject(T t) {
        Class<T> clazz = (Class<T>) t.getClass();
        Schema<T> schema = getSchema(clazz);
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] protostuff = new byte[0];
        try {
            protostuff = ProtobufIOUtil.toByteArray(t, schema, buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return protostuff;
    }


    /**
     * serialize list
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> byte[] serializeList(List<T> list) {

        if (list == null || list.isEmpty()) {
            throw new RuntimeException("序列化对象列表(" + list + ")参数异常！");
        }

        Class<T> clazz = (Class<T>) list.get(0).getClass();
        Schema<T> schema = getSchema(clazz);
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] protostuff = null;
        try {
            ProtobufIOUtil.writeListTo(bos, list, schema, buffer);
            protostuff = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return protostuff;
    }


    /**
     * deserialize object
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        T obj = objenesis.newInstance(clazz);
        Schema<T> schema = getSchema(clazz);
        try {
            ProtobufIOUtil.mergeFrom(data, obj, schema);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


    /**
     * deserialize list
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> deserializeList(byte[] data, Class<T> clazz) {
        if (data == null || data.length == 0) {
            throw new RuntimeException("反序列化对象发生异常，byte序列为空！");
        }
        try {
            Schema<T> schema = getSchema(clazz);
            List<T> result = null;
            result = ProtobufIOUtil.parseListFrom(new ByteArrayInputStream(data), schema);
            return result;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

}
