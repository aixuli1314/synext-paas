package co.synext.module.demo.controller;


import co.synext.common.base.resp.ReturnDatas;
import co.synext.config.nim.entity.req.*;
import co.synext.config.nim.entity.resp.UserRefersResponseEntity;
import co.synext.config.nim.service.INimUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "网易云信测试接口")
@RestController
@RequestMapping("/demo")
public class TestController {

    @Autowired
    private INimUserService nimUserService;

    @ApiOperation("创建接口")
    @GetMapping("create")
    public ReturnDatas create() {
        return ReturnDatas.ok(nimUserService.createNimUser(UserCreateRequestEntity.builder()
                .accid("123456")
                .build()));
    }

    @ApiOperation("更新接口")
    @GetMapping("update")
    public ReturnDatas updeate() {
        return ReturnDatas.ok(nimUserService.updateNimUser(UserUpdateRequestEntity.builder()
                .accid("123456")
                .token("hahahhahah")
                .build()));
    }

    @ApiOperation("重置接口")
    @GetMapping("refresh")
    public ReturnDatas<UserRefersResponseEntity> refresh() {
        return ReturnDatas.ok(nimUserService.refreshNimUser(UserRefersRequestEntity.builder()
                .accid("123456")
                .build()));
    }

    @ApiOperation("封禁接口")
    @GetMapping("block")
    public ReturnDatas block() {
        return ReturnDatas.ok(nimUserService.blockNimUser(UserBlockRequestEntity.builder()
                .accid("123456")
                .build()));
    }

    @ApiOperation("解禁接口")
    @GetMapping("unblock")
    public ReturnDatas unblock() {
        return ReturnDatas.ok(nimUserService.unblockNimUser(UserUnBlockRequestEntity.builder()
                .accid("123456")
                .build()));
    }


}
