package co.synext.module.system.vo;

import co.synext.mybatis.entity.TUser;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FiscoUserVO {
    private String did;
    private String credential;
    private String userInfo;
}
