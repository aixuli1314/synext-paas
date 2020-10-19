package co.synext.common.base.convert;


public abstract class Converter<A, B> {

    public abstract B doForward(A a);

    public abstract A doBackward(B b);
}
