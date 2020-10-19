package co.synext.config.nim;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import co.synext.config.nim.entity.NimBaseReqEntity;
import co.synext.config.nim.entity.NimBaseRespEntity;
import co.synext.config.nim.exception.NimException;
import co.synext.config.nim.util.NimAPIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * http请求
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-09
 */
public abstract class AbstractNimHttpRequest implements INimHttpRequest<NimBaseReqEntity> {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${netease.im.appId}")
    private String appKey;
    @Value("${netease.im.appSecret}")
    private String appSecret;

    @Override
    public HttpEntity builderHttpEntity(NimBaseReqEntity requestEntity) {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String curTime = NimAPIUtil.getCurTime();
        String nonce = NimAPIUtil.getNonce();
        String CheckSum = NimAPIUtil.getCheckSum(nonce, curTime, appSecret);

        requestHeaders.add("AppKey", appKey);
        requestHeaders.add("CurTime", curTime);
        requestHeaders.add("Nonce", nonce);
        requestHeaders.add("CheckSum", CheckSum);

        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap();
        requestBody.setAll(BeanUtil.beanToMap(requestEntity, false, true));

        return new HttpEntity<>(requestBody, requestHeaders);
    }

    @Override
    public <T extends NimBaseRespEntity> T execute(String url, NimBaseReqEntity requestEntity, Class<T> responseClass) {
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, builderHttpEntity(requestEntity), responseClass);
        NimBaseRespEntity nimDefaultRespEntity = responseEntity.getBody();
        if (NumberUtil.compare(nimDefaultRespEntity.getCode(), 200) != 0) {
            throw new NimException("网易云信接口请求失败 - {状态码:" + nimDefaultRespEntity.getCode() + ",信息:" + nimDefaultRespEntity.getMessage() + "}");
        }
        return (T) nimDefaultRespEntity;
    }
}
