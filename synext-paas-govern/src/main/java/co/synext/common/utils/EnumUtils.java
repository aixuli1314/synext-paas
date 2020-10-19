package co.synext.common.utils;

import co.synext.common.base.BaseEnum;

public class EnumUtils {

    public static <T extends BaseEnum> T getEnumByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}