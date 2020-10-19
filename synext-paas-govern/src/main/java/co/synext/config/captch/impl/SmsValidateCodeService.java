package co.synext.config.captch.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import co.synext.common.constant.Constant;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.config.aliyun.tools.AliyunSmsTools;
import co.synext.common.constant.SecurityConstant;
import co.synext.common.exception.ValidationCodeException;
import co.synext.config.captch.AbstractValidateCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xu.ran
 * @date 2020/4/27 14:10
 * @description: 验证码服务
 */
@Slf4j
@Service("smsValidateCodeService")
public class SmsValidateCodeService extends AbstractValidateCodeService {

    private final static Integer MAX_SEND_NUM = 10;

    @Resource
    private AliyunSmsTools aliyunSmsTools;

    @Autowired
    public SmsValidateCodeService(StringRedisTemplate redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public ReturnDatas create() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String deviceId = request.getParameter(SecurityConstant.VALIDATE_PARAMETER_NAME_DEVICE_ID);
        String mobile = request.getParameter(SecurityConstant.DEFAULT_PARAMETER_NAME_MOBILE);
        try {
            //如果deviceId为空，随机生成一个deviceId
            if (StringUtils.isEmpty(deviceId))
                return ReturnDatas.ok(IdUtil.randomUUID());
            preCheck(deviceId, mobile);
            //获取随机数值
            String value = getRandomCode(SecurityConstant.DEFAULT_VALIDATE_CODE_SIZE);
            JSONObject templateParam = new JSONObject();
            templateParam.put("code", mobile);
            log.info("向手机号{}发送验证码{}成功", mobile, value);
            //发送验证码
//            aliyunSmsTools.send(mobile, Enums.SmsTypeEnum.验证码, templateParam);
            //设置缓存
            put(buildCacheKey(deviceId + ":" + mobile), value);
            return ReturnDatas.ok("发送短信成功");
        } catch (Exception e) {
            return ReturnDatas.error(e.fillInStackTrace().toString());
        }
    }

    private String buildSendCountCacheKey(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            throw new ValidationCodeException("手机号不能为空！");
        }
        return Constant.JCLOUDS_PREFFIX + SecurityConstant.REDIS_VALIDATE_CODE_KEY + "_send_count" + ":" + mobile;
    }

    private void preCheck(String deviceId, String mobile) throws ValidationCodeException {
        //检查验证码是否过期
        if (redisTemplate.hasKey(buildCacheKey(deviceId + ":" + mobile))) {
            throw new ValidationCodeException("请不要重复获取验证码！");
        }
        //检查当日验证发送是否上线
        String sendCountCacheKey = buildSendCountCacheKey(mobile);
        if (!redisTemplate.hasKey(sendCountCacheKey)) {
            redisTemplate.opsForValue().set(sendCountCacheKey, String.valueOf(1));
        } else {
            Integer count = Integer.parseInt(redisTemplate.opsForValue().get(sendCountCacheKey));
            //短信发送是否上限
            if (count > MAX_SEND_NUM) {
                Date tomorrow = DateUtil.tomorrow();
                Date today = new Date();
                Long exp = DateUtil.between(tomorrow, today, DateUnit.HOUR);
                //设置过期时间
                redisTemplate.expire(sendCountCacheKey, exp, TimeUnit.HOURS);
                throw new ValidationCodeException("此号码" + mobile + "当日短信发送次数已上限！");
            } else {
                redisTemplate.opsForValue().set(sendCountCacheKey, String.valueOf(count + 1));
            }
        }
    }

    private String getRandomCode(int size) {
        String randomCode = "";
        for (int i : NumberUtil.generateRandomNumber(0, 9, size)) {
            randomCode += i;
        }
        return randomCode;
    }


    @Override
    protected String buildCacheKey(String key) {
        return Constant.JCLOUDS_PREFFIX + SecurityConstant.REDIS_VALIDATE_CODE_KEY + ":" + key;
    }
}
