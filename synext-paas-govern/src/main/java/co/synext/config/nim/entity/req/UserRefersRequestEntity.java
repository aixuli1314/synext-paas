package co.synext.config.nim.entity.req;

import co.synext.config.nim.entity.NimBaseReqEntity;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
public class UserRefersRequestEntity extends NimBaseReqEntity {

    private String accid;

}
