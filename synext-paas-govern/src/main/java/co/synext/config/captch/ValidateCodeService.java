/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package co.synext.config.captch;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.exception.ValidationCodeException;

/**
 * @author xu.ran
 * @date 2020/4/27 14:10
 * @description: 验证码
 */
public interface ValidateCodeService {


    /**
     * 创建验证码
     */
    ReturnDatas create();


    /**
     * 图形验证码校验
     *
     * @param deviceId
     * @param inputCodeValue
     */
    void verifyCaptcha(String deviceId, String inputCodeValue);

    /**
     * 短信验证码校验
     *
     * @param deviceId
     * @param mobile
     * @param inputCodeValue
     * @throws ValidationCodeException
     */
    void verifySmsCode(String deviceId, String mobile, String inputCodeValue) throws ValidationCodeException;

}
