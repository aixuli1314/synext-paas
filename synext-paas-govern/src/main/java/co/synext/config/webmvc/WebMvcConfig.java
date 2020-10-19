package co.synext.config.webmvc;

import cn.hutool.core.util.NumberUtil;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.constant.Constant;
import co.synext.common.enums.Enums;
import co.synext.common.enums.Enums.UserTypeEnum;
import co.synext.common.exception.BizException;
import co.synext.common.utils.JsonUtils;
import co.synext.config.security.details.LoginUser;
import co.synext.config.security.helper.LoginUserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private AntPathMatcher pathMatcher = new AntPathMatcher();

  //定制资源映射
    @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
        	File directory = new File(Constant.MAIN_RESOURCES_PATH);
        	String saveResourceDir = directory.getCanonicalPath();
			//意思是：url中读取到/upload时，就会自动将/upload解析成D:/idea/java_workspace/image/upload
			registry.addResourceHandler("/processes/**").addResourceLocations("file:"+saveResourceDir+"/processes/");
		} catch (IOException e) {
			e.printStackTrace();
		}
     }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                boolean auth = false;
                String requestURI = request.getRequestURI();
                LoginUser loginUser = LoginUserHelper.getCurrentLoginUser();
                if (loginUser != null) {
                    log.debug("当前登录用户：{}", loginUser);
                    for (Enums.UserTypeEnum userTypeEnum : Enums.UserTypeEnum.values()) {
                        //xu.ran 判断的当前路径是否需要匹配特定用户
                        boolean matched = pathMatcher.match(userTypeEnum.getAllowAccessUrl(), requestURI);
                        if (matched) {
                            if (NumberUtil.compare(userTypeEnum.getCode(), loginUser.getUserType()) != 0) {
                            	response.setStatus(HttpStatus.OK.value());
                                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                                response.getWriter().write(JsonUtils.writeValueAsString(
                                ReturnDatas.builder().statusCode(Constant.TOKEN_ERROR)
                                .message("访问失败，用户类型不匹配，此功能只允许[" + userTypeEnum.name() + "]访问").build()));
                                return false;
                            }
                            return true;
                        }
                    }
                    //开放接口必须登陆访问
                    boolean urlmatched = pathMatcher.match("/open/**", requestURI);
                    if(urlmatched) {
                    	return true;
                    }
                    //系统管理员权限
                    if (NumberUtil.compare(UserTypeEnum.系统管理.getCode(), loginUser.getUserType()) == 0) {
                        Set<String> permissions = loginUser.getPermissions();

                        for(String url : permissions) {
                            boolean matched = pathMatcher.match(url+"/**", requestURI);
                            if (matched) {
                                auth = true;
                                break;
                            }

                        }

                        if (auth) {
                            return true;
                        } else {
                        	response.setStatus(HttpStatus.OK.value());
                            response.setHeader("Content-Type", "application/json;charset=UTF-8");
                            response.getWriter().write(JsonUtils.writeValueAsString(
                            ReturnDatas.builder().statusCode(Constant.TOKEN_ERROR)
                            .message("没有访问权限!").build()));
                            return false;
                        }
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
