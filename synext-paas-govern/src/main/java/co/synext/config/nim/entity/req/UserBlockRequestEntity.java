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
public class UserBlockRequestEntity extends NimBaseReqEntity {

    private String accid;
    private String needkick;

}
