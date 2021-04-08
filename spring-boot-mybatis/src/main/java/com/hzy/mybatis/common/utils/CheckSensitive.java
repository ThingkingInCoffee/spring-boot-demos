package com.hzy.mybatis.common.utils;

import com.hzy.mybatis.common.annotation.DecryptField;
import com.hzy.mybatis.common.annotation.EncryptField;
import com.hzy.mybatis.common.annotation.SensitiveBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

@Slf4j
public class CheckSensitive {


    /**
     * 查看这个类是否带有敏感实体类注解，有就返回true，否则返回false
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> boolean sensitiveBean(T t) {
        // 判断是否带有敏感实体类的注解
        if (t.getClass().isAnnotationPresent(SensitiveBean.class)) {
            log.info("带有敏感实体类的注解");
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p>声明这是一个泛型方法,让所有参数都能够调用这个方法扫描带有解密注解的字段，
     * 进行解密，然后显示在前端页面中</p>
     *
     * @param <T>
     */
    public static <T> void decryptField(T t) {
        // 获取对象的域
        Field[] declaredFields = t.getClass().getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0) {
            // 遍历这些字段
            for (Field field : declaredFields) {
                // 如果这个字段存在解密注解就进行解密
                if (field.isAnnotationPresent(DecryptField.class) && field.getType().toString().endsWith("String")) {
                    field.setAccessible(true);
                    try {
                        // 获取这个字段的值
                        String fieldValue = (String) field.get(t);
                        // 判断这个字段的数值是否不为空
                        if (StringUtils.isNotEmpty(fieldValue)) {
                            // 首先调用一个方法判断这个数据是否是未经过加密的，如果可以解密就进行解密，解密失败就返回元数据
//                            boolean canDecrypt;
//                            canDecrypt = UpdateUtils.judgeDataForView(fieldValue);
//                            if (canDecrypt) {
                            // 进行解密
                            String encryptData = fieldValue.substring(0, fieldValue.length() - 2);
                            if (encryptData.equals("解密失败")) {
                                log.error("该字段不是被加密的字段,需要联系管理员进行修改数据");
                            }
                            // 将值反射到对象中
                            field.set(t, encryptData);
//                            } else {
//                                // 不能解密的情况下，就不对这个对象做任何操作，即默认显示元数据
//                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * <p>声明这是一个泛型方法,让所有参数都能够调用这个方法扫描带有加密注解的字段，
     * 进行加密，然后存在数据库中</p>
     *
     * @param <T>
     */
    public static <T> void encryptField(T t) {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0) {
            for (Field field : declaredFields) {
                // 查找当字段带有加密注解,并且字段类型为字符串类型
                if (field.isAnnotationPresent(EncryptField.class) && field.getType().toString().endsWith("String")) {
                    field.setAccessible(true);
                    String fieldValue = null;
                    try {
                        // 获取这个值
                        fieldValue = (String) field.get(t);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    // 判断这个值是否为空
                    if (StringUtils.isNotEmpty(fieldValue)) {
                        try {
                            // 不为空时，就进行加密
                            field.set(t, fieldValue + "++");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
