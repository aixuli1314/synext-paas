package co.synext.config.nim.entity.resp;

import co.synext.config.nim.entity.NimBaseRespEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FriendGetResponseEntity extends NimBaseRespEntity {

    private Integer size;
    private List<Friend> friends;

    @Getter
    @Setter
    public static class Friend implements Serializable {

        private Long createtime;
        private Boolean bidirection;
        private String faccid;
        private String alias;

    }

}
