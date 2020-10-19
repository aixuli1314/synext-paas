package co.synext.config.security.properties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;


@ConfigurationProperties(prefix = "security.oauth2")
public class PermitUrlProperties {

	private static final String[] ENDPOINTS = { 
			"/**/actuator/**" , "/**/actuator/**/**" ,  //断点监控
			"/**/v2/api-docs/**", "/**/swagger-ui.html", "/**/swagger-resources/**", "/**/webjars/**", //swagger
			"/**/druid/**", "/**/favicon.ico", "/**/prometheus", "/configuration/ui",  "/configuration/security",
			"/swagger-ui.html", "/webjars/**", "/doc.html","/js/**","/css/**","/health","/error/**","/processes/**",
	};

	private String[] ignored;

	public String[] getIgnored() {
		if (ignored == null || ignored.length == 0) {
			return ENDPOINTS;
		}

		List<String> list = new ArrayList<>();
		for (String url : ENDPOINTS) {
			list.add(url);
		}
		for (String url : ignored) {
			list.add(url);
		}

		return list.toArray(new String[list.size()]);
	}

	public void setIgnored(String[] ignored) {
		this.ignored = ignored;
	}

}
