package co.synext.config.nim.entity.resp;

import co.synext.config.nim.entity.NimBaseRespEntity;
import lombok.*;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FriendListBlackAndMuteListResponseEntity extends NimBaseRespEntity {

    private List<String> mutelist;
    private List<String> blacklist;

}
