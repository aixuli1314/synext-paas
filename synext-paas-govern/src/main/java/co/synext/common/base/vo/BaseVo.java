package co.synext.common.base.vo;

import co.synext.common.base.convert.Converter;

/**
 * @author xu.ran
 * @date 2020/1/30 0:15
 * @description: TODO
 */
public class BaseVo<V,E>  extends Converter<V, E> {


    @Override
    public E doForward(V v) {
        return null;
    }

    @Override
    public V doBackward(E e) {
        return null;
    }
}
