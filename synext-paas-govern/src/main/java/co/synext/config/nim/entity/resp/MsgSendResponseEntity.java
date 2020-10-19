package co.synext.config.nim.entity.resp;

import co.synext.config.nim.entity.NimBaseRespEntity;
import lombok.*;

import java.io.Serializable;

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
public class MsgSendResponseEntity extends NimBaseRespEntity {

    private Data data;

    @Getter
    @Setter
    public static class Data implements Serializable {

        private String msgid;
        private Long timetag;
        private Boolean antispam;

    }

}
