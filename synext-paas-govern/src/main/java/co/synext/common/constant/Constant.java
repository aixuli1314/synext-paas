package co.synext.common.constant;

/**
 * @author xuran
 * @date 2020/4/13 14:00
 * @description: 系统常量
 */
public class Constant {

    public static final String JCLOUDS_PREFFIX = "synext_";

    public static final String JCLOUDS_LICENSE = "made by synext";

    public static final String OAUTH_PREFFIX = "oauth:";

    public static final String CORP_ID = "corpId";

    public static final String SPLIT_PLACE_HOLDER= "#";

    public static final Integer SUCCESS_CODE = 200;

    public static final Integer ERROR_CODE = 30000;
    //Token失效
    public static final Integer TOKEN_ERROR = 30004;

    public static final String CORS_ALLOW_HEADERS = "Origin, X-Requested-With, Content-Type, Accept, x-token, authorization";

    public static final String CORS_ALLOW_METHODS = "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH";
    
    public static final String MAIN_RESOURCES_PATH = "src/main/resources";
    public static final String MAIN_RESOURCES_BPMNPATH = "src/main/resources/processes";
    public static final String FISCO_WEBSDK_URL = "http://127.0.0.1:8080";
    
}
