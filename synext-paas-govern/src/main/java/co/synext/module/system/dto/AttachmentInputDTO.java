package co.synext.module.system.dto;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import co.synext.mybatis.entity.TAttachment;
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
 * AttachmentInputDTO对象
 * 专家专业 信息表
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-11
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="InputAttachmentDTO对象")
public class AttachmentInputDTO extends BaseDTO<AttachmentInputDTO, TAttachment>  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "1-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 业务ID,用于业务关联查询
    */
    @ApiModelProperty(value = "2-业务ID,用于业务关联查询" )
    private String businessId;
    /**
    * 业务模块名称
    */
    @ApiModelProperty(value = "3-业务模块名称" )
    private String businessModule;
    /**
    * 附件名称
    */
    @ApiModelProperty(value = "4-附件名称" )
    private String fileName;
    /**
    * 路径
    */
    @ApiModelProperty(value = "5-路径" )
    private String fileURL;
    /**
    * 文件后缀
    */
    @ApiModelProperty(value = "6-文件后缀" )
    private String suffix;
    /**
    * 文件大小,单位K
    */
    @ApiModelProperty(value = "7-文件大小,单位K" )
    private Integer fileSize;
    /**
    * 专业开始时间
    */
    @ApiModelProperty(value = "8-专业开始时间" )
    private LocalDateTime createTime;
    /**
    * 最后下载时间
    */
    @ApiModelProperty(value = "9-最后下载时间" )
    private LocalDateTime lastDownTime;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "10-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "11-是否有效(0否,1是)" )
    private Integer active;

    public static Converter<AttachmentInputDTO, TAttachment> converter = new Converter<AttachmentInputDTO, TAttachment>() {
        @Override
        public TAttachment doForward(AttachmentInputDTO attachmentInputDTO) {
            return WarpsUtils.copyTo(attachmentInputDTO, TAttachment.class);
        }

        @Override
        public AttachmentInputDTO doBackward(TAttachment attachment) {
            return WarpsUtils.copyTo(attachment, AttachmentInputDTO.class);
        }
    };

    @Override
    public TAttachment convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public AttachmentInputDTO convertFor(TAttachment attachment) {
        return converter.doBackward(attachment);
    }
}
