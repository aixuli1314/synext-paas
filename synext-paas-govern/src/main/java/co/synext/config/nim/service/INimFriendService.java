package co.synext.config.nim.service;

import co.synext.config.nim.entity.NimBaseRespEntity;
import co.synext.config.nim.entity.req.*;
import co.synext.config.nim.entity.resp.FriendGetResponseEntity;
import co.synext.config.nim.entity.resp.FriendListBlackAndMuteListResponseEntity;

/**
 * <p>
 *  用户关系托管接口
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-09
 */
public interface INimFriendService {

    //加好友
    String addFriendUrl = "https://api.netease.im/nimserver/friend/add.action";

    //更新好友相关信息
    String updateFriendUrl = "https://api.netease.im/nimserver/friend/update.action";

    //删除好友
    String deleteFriendUrl= "https://api.netease.im/nimserver/friend/delete.action";

    //获取好友关系
    String getFriendUrl = "https://api.netease.im/nimserver/friend/get.action";

    //设置黑名单/静音
    String setSpecialRelationUrl= "https://api.netease.im/nimserver/user/setSpecialRelation.action";


    //查看指定用户的黑名单和静音列表
    String listBlackAndMuteListUrl= "https://api.netease.im/nimserver/user/listBlackAndMuteList.action";

    /**
     * 加好友
     *
     * @param friendAddRequestEntity
     * @return
     */
    NimBaseRespEntity add(FriendAddRequestEntity friendAddRequestEntity);

    /**
     * 更新好友相关信息
     *
     * @param friendUpdateRequestEntity
     * @return
     */
    NimBaseRespEntity update(FriendUpdateRequestEntity friendUpdateRequestEntity);


    /**
     * 删除好友
     *
     * @param friendDeleteRequestEntity
     * @return
     */
    NimBaseRespEntity delete(FriendDeleteRequestEntity friendDeleteRequestEntity);

    /**
     * 获取好友关系
     *
     * @param friendGetRequestEntity
     * @return
     */
    FriendGetResponseEntity get(FriendGetRequestEntity friendGetRequestEntity);

    /**
     * 设置黑名单/静音
     *
     * @param friendSetSpecialRelationRequestEntity
     * @return
     */
    NimBaseRespEntity setSpecialRelation(FriendSetSpecialRelationRequestEntity friendSetSpecialRelationRequestEntity);

    /**
     * 查看指定用户的黑名单和静音列表
     *
     * @param friendListBlackAndMuteListRequestEntity
     * @return
     */
    FriendListBlackAndMuteListResponseEntity listBlackAndMuteList(FriendListBlackAndMuteListRequestEntity friendListBlackAndMuteListRequestEntity);



}
