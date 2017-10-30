/*
 * 鼎方公司源代码，版权归鼎方公司所有。
 * 创建日期 : 2017年10月28日
 * 修改历史 : 
 *     1. [2017年10月28日]创建文件 by gehanbiao
 */
package com.activiti.demo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 转换器
 * @author gehanbiao
 * @version 2017年10月28日
 */
public class BeanUtils {

    /**
     * 反射Map到对应实体类属性
     * @return
     */
    public static Object reflectionCopyJavaBean(Map<?, ?> map,Object obj){
        if (obj == null) {
            return null;
        }

        if(map == null || map.size() == 0) {
            return obj;
        }
        //传入对象
        Class<? extends Object> clazz = obj.getClass();
        // 取得本类的全部属性
        Field[] fields = clazz.getDeclaredFields();
        // 取得本类的全部方法
        Method[] methods = clazz.getDeclaredMethods();
        //属性名
        String fieldName = null;
        //方法名
        String methodName = null;
        //返回对象
        Object val = null;
        //效率不够高  需修改
        try {
            for(Field field : fields){
                fieldName = field.getName();
                val = map.get(fieldName);
                if(null == val ){
                     continue;
                }
                //获取属性名
                methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                for(Method method : methods){
                    if(methodName.equals(method.getName())){
                        method.invoke(obj,val);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("封装属性" + fieldName + "出错，错误消息：" + e.getMessage());
            return null;
        }

        return obj;
    }

    public static Object reflectionCopyJavaBean(Map<?, ?> map,Class<?> clazz){
        //传入对象
        Object obj = null;
        //获取本类所有属性
        Field[] fields = clazz.getDeclaredFields();
        //获取本类所有方法
        Method[] methods = clazz.getDeclaredMethods();
        //属性名
        String fieldName = null;
        //方法名
        String methodName = null;
        //Map Value属性类型
        String type = null;
        //实际对象 属性类型
        String paraType = null;
        //返回对象
        Object val =null;
        try{
            obj = clazz.newInstance();
            if(StringUtils.isNull(map)){
                return obj;
            }

            for (int i = 0; i < fields.length; i++) {
                fieldName = fields[i].getName();
                val = map.get(fieldName);
                if(StringUtils.isNull(val)){
                    continue;
                }
                type = val.getClass().getSimpleName();
                methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);

                for(int j = 0; j < methods.length; j++){
                    String name = methods[j].getName();
                    if(methodName.equals(name)){
                        paraType = methods[j].getParameterTypes()[0].getSimpleName();
                        if(type.equals(paraType)){
                            methods[j].invoke(obj,val);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e){
            System.err.println("封装属性" + fieldName + "出错，错误消息：" + e.getMessage());
            return null;
        }
        return obj;
    }
    
    
    
}
