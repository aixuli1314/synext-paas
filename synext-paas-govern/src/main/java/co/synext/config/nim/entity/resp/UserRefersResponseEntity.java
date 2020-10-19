package co.synext.config.nim.entity.resp;

import co.synext.config.nim.entity.NimBaseRespEntity;
import lombok.*;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserRefersResponseEntity extends NimBaseRespEntity {

    private Info info;

    @Getter
    @Setter
    public static class Info implements Serializable {
        private String token;
        private String accid;
    }

}
