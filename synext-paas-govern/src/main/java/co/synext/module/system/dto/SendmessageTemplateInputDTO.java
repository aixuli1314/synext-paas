package co.synext.module.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import co.synext.mybatis.entity.TSendmessageTemplate;
import co.synext.common.base.convert.Converter;
import co.synext.common.base.dto.BaseDTO;
import co.synext.common.utils.WarpsUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * SendmessageTemplateInputDTO对象
 * 消息模板表
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-11
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="InputSendmessageTemplateDTO对象")
public class SendmessageTemplateInputDTO extends BaseDTO<SendmessageTemplateInputDTO, TSendmessageTemplate>  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "1-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 名称
    */
    @ApiModelProperty(value = "2-名称" )
    private String name;
    /**
    * 归属的部门,哪些部门有权限管理使用模板
    */
    @ApiModelProperty(value = "3-归属的部门,哪些部门有权限管理使用模板" )
    private String orgId;
    /**
    * 发送方式使用,号隔开.0站内信,1邮件,2短信,3微信
    */
    @ApiModelProperty(value = "4-发送方式使用,号隔开.0站内信,1邮件,2短信,3微信" )
    private String sendType;
    /**
    * 内容
    */
    @ApiModelProperty(value = "5-内容" )
    private String content;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "6-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "7-是否有效(0否,1是)" )
    private Integer active;

    public static Converter<SendmessageTemplateInputDTO, TSendmessageTemplate> converter = new Converter<SendmessageTemplateInputDTO, TSendmessageTemplate>() {
        @Override
        public TSendmessageTemplate doForward(SendmessageTemplateInputDTO sendmessageTemplateInputDTO) {
            return WarpsUtils.copyTo(sendmessageTemplateInputDTO, TSendmessageTemplate.class);
        }

        @Override
        public SendmessageTemplateInputDTO doBackward(TSendmessageTemplate sendmessageTemplate) {
            return WarpsUtils.copyTo(sendmessageTemplate, SendmessageTemplateInputDTO.class);
        }
    };

    @Override
    public TSendmessageTemplate convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public SendmessageTemplateInputDTO convertFor(TSendmessageTemplate sendmessageTemplate) {
        return converter.doBackward(sendmessageTemplate);
    }
}
