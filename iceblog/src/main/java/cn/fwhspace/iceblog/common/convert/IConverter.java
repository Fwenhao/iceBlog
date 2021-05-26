package cn.fwhspace.iceblog.common.convert;

import java.util.List;

/**
 * @ClassName: IConverter
 * @Description: DTO POJO转换器
 * @Author zhangjiahao
 * @Date 2020/11/10
 * @Version V1.0
 */
public interface IConverter {
    /**
     * 集合转换器
     * @param sources 源集合
     * @param targetClass 目标类对象
     * @param <T> 目标类对象
     * @param <S> 源类对象
     * @return 目标对象集合
     */
     <T, S> List<T> convert(List<S> sources, Class<T> targetClass);

    /**
     * 普通对象转换器
     * @param sources 源对象
     * @param targetClass 目标类对象
     * @param <T> 目标类对象
     * @param <S> 源类对象
     * @return 目标对象
     */
     <T, S> T convert(S sources, Class<T> targetClass);

}
