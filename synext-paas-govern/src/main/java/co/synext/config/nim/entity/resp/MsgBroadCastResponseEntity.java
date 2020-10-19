package co.synext.config.nim.entity.resp;

import co.synext.config.nim.entity.NimBaseRespEntity;
import lombok.*;

import java.io.Serializable;
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
public class MsgBroadCastResponseEntity extends NimBaseRespEntity {

    private Msg msg;

    @Getter
    @Setter
    public static class Msg implements Serializable {

        private String body;
        private Long expireTime;
        private Boolean isOffline;
        private Long broadcastId;
        private List<String> targetOs;

    }

}
