package co.synext.config.pay;

import com.egzosn.pay.ali.api.AliPayService;
import com.egzosn.pay.common.bean.CertStoreType;
import com.egzosn.pay.common.http.HttpConfigStorage;
import com.egzosn.pay.wx.api.WxPayService;
import co.synext.config.pay.properties.AliPayConfigStorageProperties;
import co.synext.config.pay.properties.WxPayConfigStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author xu.ran
 * @date 2020/11/30 20:19
 * @description: TODO
 */
@Configuration
@EnableConfigurationProperties({AliPayConfigStorageProperties.class, WxPayConfigStorageProperties.class})
public class PaymentConfig {

    @Resource
    AliPayConfigStorageProperties aliPayConfigStorageProperties;

    @Resource
    WxPayConfigStorageProperties wxPayConfigStorageProperties;

    @Bean
    public AliPayService aliPayService() {
        HttpConfigStorage httpConfigStorage = new HttpConfigStorage();
        httpConfigStorage.setMaxTotal(20);
        httpConfigStorage.setDefaultMaxPerRoute(10);
        return new AliPayService(aliPayConfigStorageProperties, httpConfigStorage);
    }

    @Bean
    public WxPayService wxPayService() {
        //https证书设置，退款必须 方式一
        HttpConfigStorage httpConfigStorage = new HttpConfigStorage();
        httpConfigStorage.setMaxTotal(20);
        httpConfigStorage.setDefaultMaxPerRoute(10);
        httpConfigStorage.setKeystore("证书信息串");
        httpConfigStorage.setStorePassword("证书密码");
        //设置ssl证书对应的存储方式，这里默认为文件地址
        httpConfigStorage.setCertStoreType(CertStoreType.PATH);
        return new WxPayService(wxPayConfigStorageProperties, httpConfigStorage);
    }

}
