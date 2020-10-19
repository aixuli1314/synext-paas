package co.synext.module.system.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * TSendmessageTemplateVO对象
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-11
 */
@Data
@Accessors(chain = true)
@ApiModel(value="TSendmessageTemplateVO对象", description="消息模板表")
public class SendmessageTemplateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id主键
    */
    @ApiModelProperty(value = "1-id主键" )
    private String id;
    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "2-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 名称
    */
    @ApiModelProperty(value = "3-名称" )
    private String name;
    /**
    * 归属的部门,哪些部门有权限管理使用模板
    */
    @ApiModelProperty(value = "4-归属的部门,哪些部门有权限管理使用模板" )
    private String orgId;
    /**
    * 发送方式使用,号隔开.0站内信,1邮件,2短信,3微信
    */
    @ApiModelProperty(value = "5-发送方式使用,号隔开.0站内信,1邮件,2短信,3微信" )
    private String sendType;
    /**
    * 内容
    */
    @ApiModelProperty(value = "6-内容" )
    private String content;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "7-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "8-是否有效(0否,1是)" )
    private Integer active;

}
