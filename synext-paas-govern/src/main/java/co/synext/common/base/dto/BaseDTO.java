package co.synext.common.base.dto;


/**
 * @author xu.ran
 * @date 2020/1/30 0:15
 * @description: TODO
 */
public abstract class BaseDTO<D, E> {

    public abstract E convertToEntity();

    public abstract D convertFor(E e);

}
