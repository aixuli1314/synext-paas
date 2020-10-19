package co.synext.config.nim.entity.req.message;


import co.synext.config.nim.entity.INimBaseMsg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 自定义消息
 * </p>
 *
 * 格式:
 *
 * {
 *   "myKey":myValue
 * }
 *
 * @author xu.ran
 * @since 2020-04-09
 */
@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
public class CustomMag  implements INimBaseMsg {

    public static final int TYPE = 100;

    private String myKey;

}
