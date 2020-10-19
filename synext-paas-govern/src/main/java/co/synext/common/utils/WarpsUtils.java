package co.synext.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WarpsUtils {

    public static <T> T copyTo(Object object, Class<T> clazz) {
        try {
            T vo = ReflectUtil.newInstance(clazz);
            BeanUtil.copyProperties(object, vo);
            return vo;
        } catch (Exception e) {
            log.error("错误的转换：Warps.convertValue() --->" + e.getMessage());
            return null;
        }
    }

    public static <T> T convertTo(Object bean, Class<T> clazz) {
        try {
            ObjectMapper mapper = SpringContextHolder.getBean(ObjectMapper.class);
            return mapper.convertValue(bean, clazz);
        } catch (Exception e) {
            log.error("错误的转换：Warps.convertValue() --->" + e.getMessage());
            return null;
        }
    }
}
