package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.exception.ValidationCodeException;
import co.synext.config.captch.ValidateCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xu.ran
 * @date 2020/4/27 18:42
 * @description: TODO
 */
@Api(tags = "1-验证码接口")
@RestController
public class VerifyController {

    @Autowired
    private ValidateCodeService imageValidateCodeService;

    @Autowired
    private ValidateCodeService smsValidateCodeService;

    @ApiOperation(value = "获取图片验证码")
    @PostMapping("/verify/code")
    public ReturnDatas createCaptcha(){
        return imageValidateCodeService.create();
    }

    @ApiOperation(value = "获取短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", dataType = "String", paramType = "query", value = "设备编号"),
            @ApiImplicitParam(name = "mobile", dataType = "String", paramType = "query", value = "手机号码")
    })
    @PostMapping("/verify/sms/code")
    public ReturnDatas sendSmsCode() {
        return smsValidateCodeService.create();
    }

    @ApiOperation(value = "短信验证码校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", dataType = "String", paramType = "query", value = "设备编号", required = true),
            @ApiImplicitParam(name = "mobile", dataType = "String", paramType = "query", value = "手机号码", required = true)
    })
    @PostMapping("/verify/sms/code/check")
    public ReturnDatas checkSmsCode(
            @RequestParam("deviceId") String deviceId,
            @RequestParam("mobile") String mobile,
            @RequestParam("code") String code) {
        try {
            smsValidateCodeService.verifySmsCode(deviceId, mobile, code);
            return ReturnDatas.ok(Boolean.TRUE);
        } catch (ValidationCodeException e) {
            return ReturnDatas.error(Boolean.FALSE);
        }
    }

}
