/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package co.synext.common.constant;

/**
 * @author xu.ran
 * @date 2020/4/22 22:47
 * @description: TODO
 */
public class SecurityConstant {

    /***
     * ==================================================================================================
     * 客户端sql 开始
     * ==================================================================================================
     */
    private static final String CLIENT_FIELDS_FOR_UPDATE = "resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    private static final String CLIENT_FIELDS = "client_secret, " + CLIENT_FIELDS_FOR_UPDATE;

    private static final String BASE_FIND_STATEMENT = "select client_id, " + CLIENT_FIELDS
            + " from t_oauth_client_details";

    private static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    private static final String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";


    public static final String FIND_CLIENT_DETAILS_SQL = DEFAULT_FIND_STATEMENT;

    public static final String SELECT_CLIENT_DETAILS_SQL  = DEFAULT_SELECT_STATEMENT;

    /**
     * ====================================================================================================
     * 客户端sql 结束
     * ====================================================================================================
     */

    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String VALIDATE_PARAMETER_NAME_CODE = "code";

    /**
     * 验证码设备编号
     */
    public static final String  VALIDATE_PARAMETER_NAME_DEVICE_ID = "deviceId";


    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    /**
     * 默认的手机验证码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/oauth/mobile/token";


    /**
     * Redis 验证码前缀
     * */
    public static final String  REDIS_VALIDATE_CODE_KEY = "validate_code";

    /**
     * 验证码长度
     * */
    public static final int   DEFAULT_VALIDATE_CODE_SIZE = 6;

    /**
     * 验证码有效期
     */
    public static final int   DEFAULT_VALIDATE_CODE_TIME = 60;


    /**
     * 测试客户端
     */
    public static final String  DEFAULT_TEST_CLIENT = "test";

    //登陆页面
    public static final String  LOGIN_PAGE = "/templates/login.html";

    //登陆地址
    public static final String  LOGIN_PROCESS_URL = "/doLogin";

    //登陆页面
    public static final String  GRANT_PAGE = "/grant";
}
