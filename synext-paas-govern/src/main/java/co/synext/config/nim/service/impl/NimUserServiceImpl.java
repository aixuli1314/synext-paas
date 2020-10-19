package co.synext.config.nim.service.impl;

import co.synext.config.nim.AbstractNimHttpRequest;
import co.synext.config.nim.entity.NimBaseRespEntity;
import co.synext.config.nim.entity.req.*;
import co.synext.config.nim.entity.resp.UserRefersResponseEntity;
import co.synext.config.nim.service.INimUserService;
import org.springframework.stereotype.Service;

@Service
public class NimUserServiceImpl extends AbstractNimHttpRequest implements INimUserService {

    @Override
    public NimBaseRespEntity createNimUser(UserCreateRequestEntity createUserRequestModel) {
        return execute(createUserUrl, createUserRequestModel, NimBaseRespEntity.class);
    }

    @Override
    public NimBaseRespEntity updateNimUser(UserUpdateRequestEntity userUpdateRequestEntity) {
        return execute(updateUserUrl, userUpdateRequestEntity, NimBaseRespEntity.class);
    }

    @Override
    public UserRefersResponseEntity refreshNimUser(UserRefersRequestEntity refersResponseEntity) {
        return execute(refreshUserUrl, refersResponseEntity, UserRefersResponseEntity.class);
    }

    @Override
    public NimBaseRespEntity blockNimUser(UserBlockRequestEntity userBlockRequestEntity) {
        return execute(blockUserUrl, userBlockRequestEntity, NimBaseRespEntity.class);
    }

    @Override
    public NimBaseRespEntity unblockNimUser(UserUnBlockRequestEntity userUnBlockRequestEntity) {
        return execute(unblockUserUrl, userUnBlockRequestEntity, NimBaseRespEntity.class);
    }

    @Override
    public NimBaseRespEntity updateNimUserInfo(UserInfoUpdateRequestEntity updateUserInfoRequestEntity) {
        return execute(updateUserInfoUrl, updateUserInfoRequestEntity, NimBaseRespEntity.class);
    }
}
