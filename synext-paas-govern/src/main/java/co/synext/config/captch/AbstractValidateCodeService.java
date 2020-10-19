package co.synext.config.captch;

import cn.hutool.core.util.StrUtil;
import co.synext.common.constant.SecurityConstant;
import co.synext.common.exception.ValidationCodeException;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;


/**
 * @author xu.ran
 * @date 2020/12/18 21:32
 * @description: TODO
 */
@AllArgsConstructor
public abstract class AbstractValidateCodeService implements ValidateCodeService {

    protected StringRedisTemplate redisTemplate;


    protected abstract String buildCacheKey(String key);

    protected void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value, SecurityConstant.DEFAULT_VALIDATE_CODE_TIME, TimeUnit.SECONDS);
    }

    protected String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    protected void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void verifyCaptcha(String deviceId, String inputCodeValue) {
        String cacheCode = get(buildCacheKey(deviceId));
        if (StringUtil.isNotEmpty(cacheCode)) {
            if (!StrUtil.equals(cacheCode, inputCodeValue, true)) {
                throw new ValidationCodeException("验证码输入错误！");
            } else {
                //验证成功移除验证码
                remove(buildCacheKey(deviceId));
            }
        } else {
            throw new ValidationCodeException("验证码已失效！");
        }
    }

    @Override
    public void verifySmsCode(String deviceId, String mobile, String inputCodeValue) {
        verifyCaptcha(deviceId + ":" + mobile, inputCodeValue);
    }
}
