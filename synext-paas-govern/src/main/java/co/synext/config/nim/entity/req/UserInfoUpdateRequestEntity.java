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
public class UserInfoUpdateRequestEntity extends NimBaseReqEntity {

    private String accid;
    private String name;
    private String icon;
    private String sign;
    private String email;
    private String birth;
    private String mobile;
    private String gender;
    private String ex;

}
