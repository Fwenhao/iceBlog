package cn.fwhspace.iceblog.common.convert;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: BaseConverterUtils
 * @Description: 拷贝转换器
 * @Author zhangjiahao
 * @Date 2020/11/10
 * @Version V1.0
 */

@Component
public class BaseConverterUtils implements IConverter{
    @SneakyThrows
    @Override
    public  <T, S> T convert(S source, Class<T> targetClass) {
        //获取输出对象的属性列表
        Field[] targetClassDeclaredFields = targetClass.getDeclaredFields();
        //加入父类的属性集合
        targetClassDeclaredFields = mergeSuperDeclaredFields(targetClassDeclaredFields,targetClass);
        Map<String, Object> map = new HashMap<>(1);

        //获取输入对象的属性列表
        Field[] sourceFields =source.getClass().getDeclaredFields();
        //加入父类的属性集合
        sourceFields = mergeSuperDeclaredFields(sourceFields,source.getClass());
        for (Field f : sourceFields) {
            //便利执行  匹配输入对象的属性名
                //设置私有属性可见性
                f.setAccessible(true);
                try {
                    //属性一致则将输入对象的属性值存放到的map的以对应属性名为key的
                    map.put(f.getName(), f.get(source));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        //反射获取输出对象实例
        T t = null;
        try {
            t = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        for (Field field : targetClassDeclaredFields) {
                field.setAccessible(true);
                Object obj = map.get(field.getName());
                if(obj!=null && !"serialVersionUID".equals(field.getName())){
                    String[] fieldTypeName = field.getType().toString().split("\\.");
                    String[] mapClass = map.get(field.getName()).getClass().toString().split("\\.");
                    String absoluteType = fieldTypeName[fieldTypeName.length - 1].toLowerCase();
                    String absoluteClass = mapClass[mapClass.length - 1].toLowerCase();
                    if(absoluteType.equals(absoluteClass)){
                        try {
                            field.set(t,map.get(field.getName()));
                        } catch (IllegalAccessException e) {
                           e.printStackTrace();
                        }
                    }else{
                        boolean longToString = "string".equals(absoluteType) &&("long".equals(absoluteClass));
                        if(longToString){
                            field.set(t,map.get(field.getName()).toString());
                        }
                        boolean stringToLong = "long".equals(absoluteType) &&("string".equals(absoluteClass));
                        if(stringToLong){
                            field.set(t, Long.valueOf(map.get(field.getName()).toString()));
                        }
                    }

                }

            }
            //返回输出对象
            return t;
    }

    @Override
    public <T, S> List<T> convert(List<S> s, Class<T> clz) {
        List<T> targets = new ArrayList<>();
        s.forEach(p->targets.add(convert(p,clz)));
        return targets;
    }

    private Field[] mergeSuperDeclaredFields(Field[] fields,Class clz){
        Field[] superFields = clz.getSuperclass().getDeclaredFields();
        List<Field> fieldList = Arrays.stream(fields).collect(Collectors.toList());
        fieldList.addAll(Arrays.stream(superFields).collect(Collectors.toList()));
        return fieldList.toArray(new Field[0]);
    }
}
