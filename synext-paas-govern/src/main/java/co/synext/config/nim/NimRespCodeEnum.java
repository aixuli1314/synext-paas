package co.synext.config.nim;

import co.synext.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * dddd
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-10
 */
@Getter
@AllArgsConstructor
public enum NimRespCodeEnum implements BaseEnum {

    操作成功(200, "操作成功"),
    版本不对(201, "客户端版本不对，需升级sdk"),
    被封禁(301, "被封禁"),
    用户名或密码错误(302, "用户名或密码错误"),
    IP限制(315, "IP限制"),
    非法操作或没有权限(403, "非法操作或没有权限"),
    对象不存在(404, "对象不存在"),
    参数长度过长(405, "参数长度过长"),
    对象只读(406, "对象只读"),
    客户端请求超时(408, "客户端请求超时"),
    参数错误(414, "参数错误"),
    客户端网络问题(415, "客户端网络问题"),
    频率控制(416, "频率控制"),
    重复操作(417, "重复操作"),
    数量超过上限(419, "数量超过上限"),
    账号被禁用(422, "账号被禁用"),
    帐号被禁言(423, "帐号被禁言"),
    HTTP重复请求(431, "HTTP重复请求"),
    服务器内部错误(500, "服务器内部错误"),
    服务器繁忙(503, "服务器繁忙"),
    消息撤回时间超限(508, "消息撤回时间超限"),
    无效协议(509, "无效协议"),
    服务不可用(514, "服务不可用");

    private Integer code;
    private String message;

}

