package co.synext.config.pay.properties;

import com.egzosn.pay.ali.api.AliPayConfigStorage;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xu.ran
 * @date 2020/11/30 20:42
 * @description: TODO
 */
@ConfigurationProperties(prefix = "pay.alipay")
public class AliPayConfigStorageProperties extends AliPayConfigStorage {
}
