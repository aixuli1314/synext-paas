package co.synext.config.nim.entity.resp;

import co.synext.config.nim.entity.NimBaseRespEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

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
public class MsgAttachBatchSendResponseEntity extends NimBaseRespEntity {

    private List<String> unregister;
    
}
