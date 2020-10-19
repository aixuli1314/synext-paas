package co.synext.common.base.service;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import co.synext.common.utils.WarpsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author xu.ran
 * @date 2020/4/14 19:59
 * @description: TODO
 */
public class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    @Autowired(required = false)
    protected RestTemplate restTemplate;

    protected static Snowflake snowflake;

    protected LambdaQueryWrapper<T> getLambdaQueryWrapper() {
        return new LambdaQueryWrapper<>();
    }

    protected <T> T copy2(Object object, Class<T> clazz) {
        return WarpsUtils.copyTo(object, clazz);
    }

    protected <T> T convert2(Object object, Class<T> clazz) {
        return WarpsUtils.convertTo(object, clazz);
    }

    protected String getWorkId() {
        if (snowflake == null) {
            snowflake = IdUtil.createSnowflake(0, 0);
        }
        return snowflake.nextId()+"";
    }

}
