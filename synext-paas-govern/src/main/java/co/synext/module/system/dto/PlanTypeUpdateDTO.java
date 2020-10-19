package co.synext.module.system.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import co.synext.mybatis.entity.TPlanType;
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
 * TPlanTypeUpdateDTO对象
 * 计划类别,课题申报的是planType=0
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PlanTypeUpdateDTO对象")
public class PlanTypeUpdateDTO extends BaseDTO<PlanTypeUpdateDTO, TPlanType>  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "2-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 计划类别.0课题申报
    */
    @ApiModelProperty(value = "3-计划类别.0课题申报" )
    private Integer planType;
    /**
    * 名称
    */
    @ApiModelProperty(value = "4-名称" )
    private String name;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "5-创建时间" )
    private String createTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "6-创建人" )
    private String createUserId;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "7-是否有效(0否,1是)" )
    private Integer active;

    public static Converter<PlanTypeUpdateDTO, TPlanType> converter = new Converter<PlanTypeUpdateDTO, TPlanType>() {
        @Override
        public TPlanType doForward(PlanTypeUpdateDTO planTypeUpdateDTO) {
            return WarpsUtils.copyTo(planTypeUpdateDTO, TPlanType.class);
        }

        @Override
        public PlanTypeUpdateDTO doBackward(TPlanType planType) {
            return WarpsUtils.copyTo(planType, PlanTypeUpdateDTO.class);
        }
    };

    @Override
    public TPlanType convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public PlanTypeUpdateDTO convertFor(TPlanType planType) {
        return converter.doBackward(planType);
    }
}
