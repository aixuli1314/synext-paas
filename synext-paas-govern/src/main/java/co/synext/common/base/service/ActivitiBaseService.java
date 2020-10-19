package co.synext.common.base.service;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import co.synext.common.utils.WarpsUtils;

/**
 * @author xu.ran
 * @date 2020/4/14 19:59
 * @description: TODO
 */
public class ActivitiBaseService  {
	protected static Snowflake snowflake;
    public <T> T copy2(Object object, Class<T> clazz) {
        return WarpsUtils.copyTo(object, clazz);
    }
    protected String getWorkId() {
        if (snowflake == null) {
            snowflake = IdUtil.createSnowflake(0, 0);
        }
        return snowflake.nextId()+"";
    }
}
