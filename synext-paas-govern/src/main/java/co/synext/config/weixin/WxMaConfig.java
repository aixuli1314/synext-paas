package co.synext.config.weixin;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaRedisConfigImpl;
import co.synext.config.weixin.properties.WxMaProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@AllArgsConstructor
@Configuration
@ConditionalOnClass(WxMaService.class)
@EnableConfigurationProperties(WxMaProperties.class)
@ConditionalOnProperty(prefix = "wx.miniapp", value = "enabled", matchIfMissing = true)
public class WxMaConfig {

    private WxMaProperties properties;

    @Bean
    @ConditionalOnMissingBean(WxMaService.class)
    public WxMaService service() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(StringUtils.trimToNull(this.properties.getAppId()));
        config.setSecret(StringUtils.trimToNull(this.properties.getAppSecret()));
        config.setToken(StringUtils.trimToNull(this.properties.getToken()));
        config.setAesKey(StringUtils.trimToNull(this.properties.getAesKey()));
        config.setMsgDataFormat(StringUtils.trimToNull(this.properties.getMsgDataFormat()));
        final WxMaServiceImpl service = new WxMaServiceImpl();
        service.setWxMaConfig(config);
        return service;
    }

}
