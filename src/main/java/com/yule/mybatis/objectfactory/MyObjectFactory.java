package com.yule.mybatis.objectfactory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;
import java.util.Properties;

/**
 * @author yule
 * @date 2018/8/12 19:34
 */
public class MyObjectFactory extends DefaultObjectFactory {

    private static final long serialVersionUID = -8855120656740914948L;

    @Override
    public void setProperties(Properties properties) {
        System.out.println("使用我自己的对象工厂 定制属性");
        super.setProperties(properties);
    }

    @Override
    public <T> T create(Class<T> type) {
        System.out.println("使用我自己的对象工厂 构建单个对象");
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        System.out.println("使用我自己的对象工厂 构建对象列表");
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public <T> boolean isCollection(Class<T> aClass) {
        return false;
    }
}
