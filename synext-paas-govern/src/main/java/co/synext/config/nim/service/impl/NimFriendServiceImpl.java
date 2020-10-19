package co.synext.config.nim.service.impl;

import co.synext.config.nim.AbstractNimHttpRequest;
import co.synext.config.nim.entity.NimBaseRespEntity;
import co.synext.config.nim.entity.req.*;
import co.synext.config.nim.entity.resp.FriendGetResponseEntity;
import co.synext.config.nim.entity.resp.FriendListBlackAndMuteListResponseEntity;
import co.synext.config.nim.service.INimFriendService;
import org.springframework.stereotype.Service;

@Service
public class NimFriendServiceImpl extends AbstractNimHttpRequest implements INimFriendService {

    @Override
    public NimBaseRespEntity add(FriendAddRequestEntity friendAddRequestEntity) {
        return execute(addFriendUrl, friendAddRequestEntity, NimBaseRespEntity.class);
    }

    @Override
    public NimBaseRespEntity update(FriendUpdateRequestEntity friendUpdateRequestEntity) {
        return execute(updateFriendUrl, friendUpdateRequestEntity, NimBaseRespEntity.class);
    }

    @Override
    public NimBaseRespEntity delete(FriendDeleteRequestEntity friendDeleteRequestEntity) {
        return execute(deleteFriendUrl, friendDeleteRequestEntity, NimBaseRespEntity.class);
    }

    @Override
    public FriendGetResponseEntity get(FriendGetRequestEntity friendGetRequestEntity) {
        return execute(getFriendUrl, friendGetRequestEntity, FriendGetResponseEntity.class);
    }

    @Override
    public NimBaseRespEntity setSpecialRelation(FriendSetSpecialRelationRequestEntity friendSetSpecialRelationRequestEntity) {
        return execute(setSpecialRelationUrl, friendSetSpecialRelationRequestEntity, NimBaseRespEntity.class);
    }

    @Override
    public FriendListBlackAndMuteListResponseEntity listBlackAndMuteList(FriendListBlackAndMuteListRequestEntity friendListBlackAndMuteListRequestEntity) {
        return execute(listBlackAndMuteListUrl, friendListBlackAndMuteListRequestEntity, FriendListBlackAndMuteListResponseEntity.class);
    }
}
