package co.synext.config.nim.entity.resp;

import co.synext.config.nim.entity.NimBaseRespEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 发送消息响应
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-10
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MsgFileUploadResponseEntity extends NimBaseRespEntity {

    private String url;

}
