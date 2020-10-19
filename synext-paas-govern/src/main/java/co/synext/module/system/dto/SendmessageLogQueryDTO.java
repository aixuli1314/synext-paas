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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * SendmessageLogQueryDTO对象
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
@ApiModel(value="SendmessageLogQueryDTO对象")
public class SendmessageLogQueryDTO extends BaseDTO<SendmessageLogQueryDTO, TSendmessageLog>  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

    /**
     * id主键
     */
    private String id;
    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "3-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 模板Id
    */
    @ApiModelProperty(value = "4-模板Id" )
    private String templateId;
    /**
    * 发送消息的业务模块,例如 单位注册模块
    */
    @ApiModelProperty(value = "5-发送消息的业务模块,例如 单位注册模块" )
    private String businessModule;
    /**
    * 发送模板消息的业务记录Id,比如t_user_20200808 这样的记录ID
    */
    @ApiModelProperty(value = "6-发送模板消息的业务记录Id,比如t_user_20200808 这样的记录ID" )
    private String businessId;
    /**
    * 发送方式,一个类型一条记录,0站内信,1邮件,2短信,3微信
    */
    @ApiModelProperty(value = "7-发送方式,一个类型一条记录,0站内信,1邮件,2短信,3微信" )
    private Integer sendType;
    /**
    * 模板Id
    */
    @ApiModelProperty(value = "8-模板Id" )
    private String sendTime;
    /**
    * 发送成功时间
    */
    @ApiModelProperty(value = "9-发送成功时间" )
    private String successTime;
    /**
    * 发送错误时间
    */
    @ApiModelProperty(value = "10-发送错误时间" )
    private String errorTime;
    /**
    * 读取时间
    */
    @ApiModelProperty(value = "11-读取时间" )
    private String readTime;
    /**
    * 发送人
    */
    @ApiModelProperty(value = "12-发送人" )
    private String fromUserId;
    /**
    * 接收人Id
    */
    @ApiModelProperty(value = "13-接收人Id" )
    private String toUserId;
    /**
    * 内容
    */
    @ApiModelProperty(value = "14-内容" )
    private String content;
    /**
    * 如果发送失败,记录错误原因
    */
    @ApiModelProperty(value = "15-如果发送失败,记录错误原因" )
    private String errorMessage;
    /**
    * 消息状态.0已创建,1发送成功,2.发送失败,3已读取
    */
    @ApiModelProperty(value = "16-消息状态.0已创建,1发送成功,2.发送失败,3已读取" )
    private Integer status;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "17-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "18-是否有效(0否,1是)" )
    private Integer active;

    public Page<TSendmessageLog> page() {
        return new Page<>(current, size);
    }

    public static Converter<SendmessageLogQueryDTO, TSendmessageLog> converter = new Converter<SendmessageLogQueryDTO, TSendmessageLog>() {
        @Override
        public TSendmessageLog doForward(SendmessageLogQueryDTO sendmessageLogQueryDTO) {
            return WarpsUtils.copyTo(sendmessageLogQueryDTO, TSendmessageLog.class);
        }

        @Override
        public SendmessageLogQueryDTO doBackward(TSendmessageLog sendmessageLog) {
            return WarpsUtils.copyTo(sendmessageLog, SendmessageLogQueryDTO.class);
        }
    };

    @Override
    public TSendmessageLog convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public SendmessageLogQueryDTO convertFor(TSendmessageLog sendmessageLog) {
        return converter.doBackward(sendmessageLog);
    }
}
