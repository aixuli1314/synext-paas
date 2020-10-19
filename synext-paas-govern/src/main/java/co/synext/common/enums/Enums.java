package co.synext.common.enums;

import co.synext.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Enums {

    @Getter
    @AllArgsConstructor
    public enum UserTypeEnum implements BaseEnum<Integer> {
    	//0访客,1注册企业,2企业个人,3专家,4科技局后台,9系统管理员',
    	访客(0, "visitor", "/visitor/**"),
    	注册企业(1, "company", "/company/**"),
    	企业个人(2, "member", "/member/**"),
        专家用户(3, "expert", "/expert/**"),
    	科技局后台(4, "admin", "/admin/**"),
    	系统管理(9, "system", "/system/**");

        public Integer code;
        public String alias;
        public String allowAccessUrl;

    }

    @Getter
    @AllArgsConstructor
    public enum NoticeTypeEnum implements BaseEnum<Integer> {
        系统通知(1),
        企业认证通知(2);
        public Integer code;
    }


    @Getter
    @AllArgsConstructor
    public enum ActiveStateEnum implements BaseEnum<Integer> {
        启用(1),
        禁用(0),
        删除(-1);
        public Integer code;
    }
    @Getter
    @AllArgsConstructor
    public enum ActiveStateString implements BaseEnum<String> {
    	启用("1"),
    	禁用("0"),
    	删除("-1");
    	public String code;
    }
    @Getter
    @AllArgsConstructor
    public enum YesOrNo implements BaseEnum<String> {
    	是("1"),
    	否("0");
    	public String code;
    }
    @Getter
    @AllArgsConstructor
    public enum ApplyFlowName implements BaseEnum<String> {
    	前资助项目流程("BeforSubsidize"),
    	后补助项目流程("AfterSubsidize");
    	public String code;
    	
    	public static String getApplyFlowName(String code) {
			switch (code) {
			case "BeforSubsidize":
				return 前资助项目流程.toString();
			case "AfterSubsidize":
				return 后补助项目流程.toString();
			default:
				return null;
			}
		}
    }
    @Getter
    @AllArgsConstructor
    public enum AuditStatus implements BaseEnum<String> {
    	审批中("PENDING"),
    	审批成功("APPROVAL_SUCCESS"),
    	审批失败("APPROVAL_FAILED"),
    	待审批("WAITING_FOR_APPROVAL");
    	public String code;
    	
    	public static String getApplyFlowName(String code) {
    		switch (code) {
    		case "PENDING":
    			return 审批中.toString();
    		case "APPROVAL_SUCCESS":
    			return 审批成功.toString();
    		case "APPROVAL_FAILED":
    			return 审批失败.toString();
    		case "WAITING_FOR_APPROVAL":
    			return 待审批.toString();
    		default:
    			return null;
    		}
    	}
    }

    @Getter
    @AllArgsConstructor
    public enum OrderStateEnum implements BaseEnum<Integer> {

        待支付("UNPAY", 0),
        已完成("PAY", 1),
        已取消("CANCEL", -1);

        public String name;
        public Integer code;

    }


    @Getter
    @AllArgsConstructor
    public enum PayTypeEnum implements BaseEnum<Integer> {

        微信扫码支付("wechat", 1),
        支付宝网页支付("alipayHtml", 2),
        支付宝扫码支付("alipay", 3);

        public String name;
        public Integer code;

    }


    @Getter
    @AllArgsConstructor
    public enum SmsTypeEnum implements BaseEnum<Integer> {

        验证码("验证码", 1),
        通知("通知", 2);

        public String name;
        public Integer code;
    }

    
    /**
     * 支付方式
     * */
    @Getter
    @AllArgsConstructor
    public enum Payment implements BaseEnum<Integer> {

        支付宝("支付宝",1),微信("微信",2);

        public String name;
        public Integer code;
        public static String getPaymentName(Integer type) {
            switch (type) {
                case 1:
                    return 支付宝.name;
                case 2:
                    return 微信.name;
                default:
                    return null;
            }
        }

    }
    /**
     * 支付方式
     * */
    @Getter
    @AllArgsConstructor
    public enum TaskDefKey implements BaseEnum<String> {
    	
    	企业管理员审核("enterpriseAudit"),县区级科技局受理("areaKJJAudit"),市级科技局审核("cityKJJAudit"),市级科技局中期评估("metaphaseAudit"),科技局结题审核("conclusionAudit");
    	
    	public String code;
    }
    

}
