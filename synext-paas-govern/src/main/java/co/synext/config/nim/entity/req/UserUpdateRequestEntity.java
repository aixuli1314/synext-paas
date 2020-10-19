package co.synext.config.nim.entity.req;

import co.synext.config.nim.entity.NimBaseReqEntity;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
public class UserUpdateRequestEntity extends NimBaseReqEntity {

    private String accid;
    private String props;
    private String token;

}
