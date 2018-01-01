package com.chengwenbi.util;

import org.apache.commons.beanutils.BeanUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: chengwenbi
 * @date:   2018/1/1 16:30
 */
public class ListUtil {

    /**
     * 拼接属性
     *
     * @param lists
     * @param fieldName
     * @param separator
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> String joinProperty(List<T> lists, String fieldName, String separator)
            throws NoSuchFieldException, IntrospectionException, InvocationTargetException,
            IllegalAccessException {

        if (isEmpty(lists)) return "";

        StringBuilder buffer = new StringBuilder();

        int start = 0;

        int end = lists.size();

        Iterator<T> iterator = lists.iterator();

        while (iterator.hasNext()) {

            start++;

            T entity = iterator.next();

            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, entity.getClass());

            Method method = propertyDescriptor.getReadMethod();

            buffer.append(method.invoke(entity));

            if (start < end) buffer.append(separator);
        }
        return buffer.toString();
    }

    /**
     * 根据属性字段名和属性value值返回model
     *
     * @param lists
     * @param fieldName
     * @param value
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> T findEntityByFieldValue(List<T> lists, String fieldName, String value)
            throws NoSuchFieldException, IntrospectionException, InvocationTargetException,
            IllegalAccessException {

        if (lists == null || lists.size() == 0) {
            return null;
        }

        Iterator<T> iterator = lists.iterator();

        while (iterator.hasNext()) {

            T entity = iterator.next();

            Object object = getProperty(entity, fieldName);

            if (object != null && object.toString().equals(value)) {

                return entity;
            }
        }

        return null;
    }

    /**
     * 根据属性字段名和属性value值返回model
     *
     * @param lists
     * @param fieldName
     * @param value
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> List<T> findListByFieldValue(List<T> lists, String fieldName, String value)
            throws NoSuchFieldException, IntrospectionException, InvocationTargetException,
            IllegalAccessException {

        List<T> result = new ArrayList<>(lists.size());

        if (lists == null || lists.size() == 0) {
            return result;
        }

        Iterator<T> iterator = lists.iterator();

        while (iterator.hasNext()) {

            T entity = iterator.next();

            Object object = getProperty(entity, fieldName);

            if (object != null && object.toString().equals(value)) {

                result.add(entity);
            }
        }

        return result;
    }

    /**
     * copy list中的对象到一个新对象中
     *
     * @param lists
     * @param targetClass
     * @param <S>
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public static <S, T> List<T> copyList(List<S> lists, Class<T> targetClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        List<T> copys = new ArrayList<>();

        for (S sourceClass : lists) {

            T target = targetClass.newInstance();

            BeanUtils.copyProperties(target, sourceClass);

            copys.add(target);

        }

        return copys;

    }

    /**
     * 通过反射获取value值
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> Object getProperty(T entity, String fieldName)
            throws InvocationTargetException, IllegalAccessException, IntrospectionException {

        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, entity.getClass());

        Method method = propertyDescriptor.getReadMethod();

        Object object = method.invoke(entity);

        return object;
    }

    /**
     * 判断集合是否不为null
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {

        return collection == null || collection.size() == 0;
    }
}
