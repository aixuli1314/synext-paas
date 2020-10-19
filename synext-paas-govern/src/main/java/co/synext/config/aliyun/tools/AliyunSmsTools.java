package co.synext.config.aliyun.tools;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import co.synext.common.enums.Enums;
import co.synext.config.aliyun.BaseAliyunTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AliyunSmsTools extends BaseAliyunTools {

    public void send(String phoneNumbers, Enums.SmsTypeEnum typeEnum, JSONObject templateParam) throws ClientException {
        CommonRequest request = new CommonRequest();
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        if(Enums.SmsTypeEnum.通知.getCode().equals(typeEnum.getCode())) {
            request.putQueryParameter("SignName", aliyunProperties.getSmsNotice().getSignName());
            request.putQueryParameter("TemplateCode", aliyunProperties.getSmsNotice().getTemplateCode());
        }else {
            request.putQueryParameter("SignName", aliyunProperties.getSmsVerify().getSignName());
            request.putQueryParameter("TemplateCode", aliyunProperties.getSmsVerify().getTemplateCode());
        }
        if (templateParam != null)
            request.putQueryParameter("TemplateParam", templateParam.toJSONString());
        CommonResponse response = client.getCommonResponse(request);
        String data = response.getData();
        log.info("发送阿里云短信 - {}", data);
    }


}
