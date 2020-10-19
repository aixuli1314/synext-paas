package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户社交账号绑定
 * </p>
 *
 * @author xu.ran
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysUserConnection对象", description="用户社交账号绑定")
public class SysUserConnection implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("userId")
    private String userId;

    @TableField("providerId")
    private String providerId;

    @TableField("providerUserId")
    private String providerUserId;

    @TableField("providerUnionId")
    private String providerUnionId;

    @TableField("providerSessionKey")
    private String providerSessionKey;

    private Integer rank;

    @TableField("displayName")
    private String displayName;

    @TableField("profileUrl")
    private String profileUrl;

    @TableField("imageUrl")
    private String imageUrl;

    @TableField("accessToken")
    private String accessToken;

    private String secret;

    @TableField("refreshToken")
    private String refreshToken;

    @TableField("expireTime")
    private Long expireTime;


}
