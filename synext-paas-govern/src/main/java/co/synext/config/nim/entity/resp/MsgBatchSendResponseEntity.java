package co.synext.config.nim.entity.resp;

import co.synext.config.nim.entity.NimBaseRespEntity;
import lombok.*;

import java.util.List;
import java.util.Map;

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
public class MsgBatchSendResponseEntity extends NimBaseRespEntity {

    private Map<String, String> msgids;
    private Long timetag;
    private List<String> unregister;
    
}
