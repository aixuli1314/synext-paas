package co.synext.config.nim.entity;

import co.synext.common.utils.EnumUtils;
import co.synext.config.nim.NimRespCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * NimBaseResp
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-09
 */
@ToString
public class NimBaseRespEntity implements Serializable {

    @Getter
    @Setter
    private Integer code;

    public final String getMessage() {
        NimRespCodeEnum respCodeEnum = EnumUtils.getEnumByCode(this.code, NimRespCodeEnum.class);
        if (respCodeEnum == null) return "状态码：" + code + " 请查看官方文档！";
        return respCodeEnum.getMessage();
    }
}
