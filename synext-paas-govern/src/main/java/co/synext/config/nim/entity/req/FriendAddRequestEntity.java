package co.synext.config.nim.entity.req;

import co.synext.config.nim.entity.NimBaseReqEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * 参数	  类型	   必须	   说明
 * accid  String	是	用户帐号，最大长度32字符，必须保证一个 APP内唯一
 */
@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
public class FriendAddRequestEntity  extends NimBaseReqEntity {

    private String accid;
    private String faccid;
    private Integer type;
    private String msg;
    private String serverex;

}
