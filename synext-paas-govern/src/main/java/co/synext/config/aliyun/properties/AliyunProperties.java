package co.synext.config.aliyun.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunProperties {

    private String accessKeySecret;
    private String accessKeyId;
    private String regionId = "cn-shanghai";
    private SmsProperties smsNotice;
    private SmsProperties smsVerify;
    private OssProperties oss;

    @Data
    public static class SmsProperties {
        private String signName;
        private String templateCode;
    }

    @Data
    public static class OssProperties {
        private String folderName;
        private String bucketName;
        private String endpoint;
        private String cdnDomain;
    }
}

