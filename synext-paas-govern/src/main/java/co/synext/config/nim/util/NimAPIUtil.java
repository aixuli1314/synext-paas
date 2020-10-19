package co.synext.config.nim.util;

import cn.hutool.core.util.IdUtil;
import co.synext.config.nim.util.EncodeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public class NimAPIUtil {

    public static String getNonce() {
        return IdUtil.fastUUID();
    }

    public static String getCurTime() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public static String getCheckSum(String nonce, String curTime, String appSecret) {
        return EncodeUtils.getCheckSum(appSecret, nonce, curTime);
    }
}
