package co.synext.config.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import co.synext.config.aliyun.properties.AliyunProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;

@EnableConfigurationProperties(AliyunProperties.class)
public class BaseAliyunTools {

    @Autowired
    protected AliyunProperties aliyunProperties;
    protected static DefaultAcsClient client;

    @PostConstruct
    public void init() {
        DefaultProfile profile = DefaultProfile.getProfile(aliyunProperties.getRegionId(), aliyunProperties.getAccessKeyId(), aliyunProperties.getAccessKeySecret());
        client = new DefaultAcsClient(profile);
    }

}
