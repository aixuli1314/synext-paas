package co.synext.config.nim.entity.req;

import co.synext.config.nim.entity.NimBaseReqEntity;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
public class UserCreateRequestEntity extends NimBaseReqEntity {

    private String accid;
    private String name;
    private String props;
    private String icon;
    private String token;
    private String sign;
    private String email;
    private String birth;
    private String mobile;
    private String gender;
    private String ex;

}
