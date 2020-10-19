package co.synext.config.pay.properties;

import com.egzosn.pay.wx.api.WxPayConfigStorage;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author xu.ran
 * @date 2020/11/30 20:40
 * @description: TODO
 */
@ConfigurationProperties(prefix = "pay.wx")
public class WxPayConfigStorageProperties extends WxPayConfigStorage {
}
