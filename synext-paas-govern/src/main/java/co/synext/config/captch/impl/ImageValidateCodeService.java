package co.synext.config.captch.impl;

import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Maps;
import com.wf.captcha.GifCaptcha;
import co.synext.common.constant.Constant;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.constant.SecurityConstant;
import co.synext.common.exception.ValidationCodeException;
import co.synext.config.captch.AbstractValidateCodeService;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * @author xu.ran
 * @date 2020/4/27 14:10
 * @description: 验证码服务
 */
@Slf4j
@Service("imageValidateCodeService")
public class ImageValidateCodeService extends AbstractValidateCodeService {

    @Autowired
    public ImageValidateCodeService(StringRedisTemplate redisTemplate) {
        super(redisTemplate);
    }

    public ReturnDatas create() {
        GifCaptcha captcha = new GifCaptcha(100, 35, 4);
        captcha.setCharType(captcha.TYPE_NUM_AND_UPPER);
        String key = IdUtil.randomUUID();
        put(buildCacheKey(key), captcha.text());
        Map validInfo = Maps.newHashMap();
        validInfo.put("deviceId", key);
        validInfo.put("base64Image", captcha.toBase64());
        return ReturnDatas.ok(validInfo);
    }


    @Override
    protected String buildCacheKey(String s) {
        if (StringUtil.isAllEmpty(s)) throw new ValidationCodeException("参数错误！");
        return Constant.JCLOUDS_PREFFIX + SecurityConstant.REDIS_VALIDATE_CODE_KEY + s;
    }
}
