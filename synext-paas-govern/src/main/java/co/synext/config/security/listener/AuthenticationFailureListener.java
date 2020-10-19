package co.synext.config.security.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.*;
import org.springframework.security.oauth2.client.filter.OAuth2AuthenticationFailureEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {
    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
    	StringBuilder message = new StringBuilder();
        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
        	message.append("提供的凭据是错误的，用户名或者密码错误");
        } else if (event instanceof AuthenticationFailureCredentialsExpiredEvent) {
        	message.append("验证通过，但是密码过期");
        } else if (event instanceof AuthenticationFailureDisabledEvent) {
        	message.append("验证过了但是账户被禁用");
        } else if (event instanceof AuthenticationFailureExpiredEvent) {
        	message.append("验证通过了，但是账号已经过期");
        } else if (event instanceof AuthenticationFailureLockedEvent) {
        	message.append("账户被锁定");
        } else if (event instanceof AuthenticationFailureProviderNotFoundEvent) {
        	message.append("配置错误，没有合适的AuthenticationProvider来处理登录验证");
        } else if (event instanceof AuthenticationFailureProxyUntrustedEvent) {
        	message.append("代理不受信任，用于Oauth、CAS这类三方验证的情形，多属于配置错误");
        } else if (event instanceof AuthenticationFailureServiceExceptionEvent) {
        	message.append("其他任何在AuthenticationManager中内部发生的异常都会被封装成此类");
        } else if (event instanceof OAuth2AuthenticationFailureEvent) {
        	message.append("Oauth2认证失败");
        }
        System.out.println(message);
        return;
    }

}