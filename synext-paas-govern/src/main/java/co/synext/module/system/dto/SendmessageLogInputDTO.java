package co.synext.module.system.dto;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import co.synext.mybatis.entity.TSendmessageLog;
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
 * SendmessageLogInputDTO对象
 * 消息发送记录表
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="InputSendmessageLogDTO对象")
public class SendmessageLogInputDTO extends BaseDTO<SendmessageLogInputDTO, TSendmessageLog>  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "1-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 模板Id
    */
    @ApiModelProperty(value = "2-模板Id" )
    private String templateId;
    /**
    * 发送消息的业务模块,例如 单位注册模块
    */
    @ApiModelProperty(value = "3-发送消息的业务模块,例如 单位注册模块" )
    private String businessModule;
    /**
    * 发送模板消息的业务记录Id,比如t_user_20200808 这样的记录ID
    */
    @ApiModelProperty(value = "4-发送模板消息的业务记录Id,比如t_user_20200808 这样的记录ID" )
    private String businessId;
    /**
    * 发送方式,一个类型一条记录,0站内信,1邮件,2短信,3微信
    */
    @ApiModelProperty(value = "5-发送方式,一个类型一条记录,0站内信,1邮件,2短信,3微信" )
    private Integer sendType;
    /**
    * 模板Id
    */
    @ApiModelProperty(value = "6-发送时间" )
    private String sendTime;
    /**
    * 发送成功时间
    */
    @ApiModelProperty(value = "7-发送成功时间" )
    private String successTime;
    /**
    * 发送错误时间
    */
    @ApiModelProperty(value = "8-发送错误时间" )
    private String errorTime;
    /**
    * 读取时间
    */
    @ApiModelProperty(value = "9-读取时间" )
    private String readTime;
    /**
    * 发送人
    */
    @ApiModelProperty(value = "10-发送人" )
    private String fromUserId;
    /**
    * 接收人Id
    */
    @ApiModelProperty(value = "11-接收人Id" )
    private String toUserId;
    /**
    * 内容
    */
    @ApiModelProperty(value = "12-内容" )
    private String content;
    /**
    * 如果发送失败,记录错误原因
    */
    @ApiModelProperty(value = "13-如果发送失败,记录错误原因" )
    private String errorMessage;
    /**
    * 消息状态.0已创建,1发送成功,2.发送失败,3已读取
    */
    @ApiModelProperty(value = "14-消息状态.0已创建,1发送成功,2.发送失败,3已读取" )
    private Integer status;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "15-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "16-是否有效(0否,1是)" )
    private Integer active;

    public static Converter<SendmessageLogInputDTO, TSendmessageLog> converter = new Converter<SendmessageLogInputDTO, TSendmessageLog>() {
        @Override
        public TSendmessageLog doForward(SendmessageLogInputDTO sendmessageLogInputDTO) {
            return WarpsUtils.copyTo(sendmessageLogInputDTO, TSendmessageLog.class);
        }

        @Override
        public SendmessageLogInputDTO doBackward(TSendmessageLog sendmessageLog) {
            return WarpsUtils.copyTo(sendmessageLog, SendmessageLogInputDTO.class);
        }
    };

    @Override
    public TSendmessageLog convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public SendmessageLogInputDTO convertFor(TSendmessageLog sendmessageLog) {
        return converter.doBackward(sendmessageLog);
    }
}
