package co.synext.config.nim.entity.req.message;

import co.synext.config.nim.entity.INimBaseMsg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 *
 * 文本消息
 *
 * {
 *   "msg":"哈哈哈"//消息内容
 * }
 */
@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
public class TextMsg implements INimBaseMsg {

    public static final int TYPE = 0;

    private String msg;

}
