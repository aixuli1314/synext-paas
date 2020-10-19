package co.synext.config.nim.entity.req;

import co.synext.config.nim.entity.NimBaseReqEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
public class MsgRecallOneWayRequestEntity extends NimBaseReqEntity {

    private String deleteMsgid;
    private Long timetag;
    private Integer type;
    private String from;
    private String to;
    private String msg;

}
