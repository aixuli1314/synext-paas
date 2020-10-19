package co.synext.config.nim.entity.req.message;

import co.synext.config.nim.entity.INimBaseMsg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 提示消息
 * </p>
 *
 * 格式:
 *
 *  {
 *    "msg":"您收到一份礼物"
 *  }
 *
 * @author xu.ran
 * @since 2020-04-09
 */
@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
public class AlertMsg implements INimBaseMsg {

    private String msg;

}
