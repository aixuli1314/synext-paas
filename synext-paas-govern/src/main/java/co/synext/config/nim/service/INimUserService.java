package co.synext.config.nim.service;

import co.synext.config.nim.entity.NimBaseRespEntity;
import co.synext.config.nim.entity.req.*;
import co.synext.config.nim.entity.resp.UserRefersResponseEntity;

/**
 * <p>
 * 网易云信用户接口
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-09
 */
public interface INimUserService {

    //创建网易云通信ID
    String createUserUrl = "https://api.netease.im/nimserver/user/create.action";

    //更新网易云通信token
    String updateUserUrl = "https://api.netease.im/nimserver/user/update.action";

    //重置网易云通信token
    String refreshUserUrl = "https://api.netease.im/nimserver/user/refreshToken.action";

    //封禁网易云通信ID
    String blockUserUrl = "https://api.netease.im/nimserver/user/block.action";

    //解禁网易云通信ID
    String unblockUserUrl = "https://api.netease.im/nimserver/user/unblock.action";

    //更新用户名片
    String updateUserInfoUrl = "https://api.netease.im/nimserver/user/updateUinfo.action";

    /**
     * 创建网易云信用户
     *
     * @param createUserRequestModel
     * @return
     */
    NimBaseRespEntity createNimUser(UserCreateRequestEntity createUserRequestModel);

    /**
     * 更新网易云通信token
     *
     * @param userUpdateRequestEntity
     * @return
     */
    NimBaseRespEntity updateNimUser(UserUpdateRequestEntity userUpdateRequestEntity);

    /**
     * 重置网易云通信token
     *
     * @param refersResponseEntity
     * @return
     */
    UserRefersResponseEntity refreshNimUser(UserRefersRequestEntity refersResponseEntity);

    /**
     * 封禁网易云通信ID
     *
     * @param userBlockRequestEntity
     * @return
     */
    NimBaseRespEntity blockNimUser(UserBlockRequestEntity userBlockRequestEntity);

    /**
     * 解禁网易云通信ID
     *
     * @param userUnBlockRequestEntity
     * @return
     */
    NimBaseRespEntity unblockNimUser(UserUnBlockRequestEntity userUnBlockRequestEntity);

    /**
     * 更新用户名片
     *
     * @param updateUserInfoRequestEntity
     * @return
     */
    NimBaseRespEntity updateNimUserInfo(UserInfoUpdateRequestEntity updateUserInfoRequestEntity);

}
