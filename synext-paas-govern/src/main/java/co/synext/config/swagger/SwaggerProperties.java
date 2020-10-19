/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package co.synext.config.swagger;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties("swagger")
public class SwaggerProperties {

	private String basePackage = "";

	private List<String> basePath = new ArrayList<>();

	private List<String> excludePath = new ArrayList<>();

	private String title = "";

	private String description = "";

	private String version = "";

	private String license = "";

	private String licenseUrl = "";

	private String termsOfServiceUrl = "";

	private String host = "";

	private Contact contact = new Contact();

	private Authorization authorization = new Authorization();

	@Data
	@NoArgsConstructor
	public static class Contact {

		private String name = "";
		private String url = "";
		private String email = "";

	}

	@Data
	@NoArgsConstructor
	public static class Authorization {

		private String name = "";
		private String authRegex = "^.*$";
		private List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		private List<String> tokenUrlList = new ArrayList<>();

	}

	@Data
	@NoArgsConstructor
	public static class AuthorizationScope {
		private String scope = "";
		private String description = "";

	}
}
