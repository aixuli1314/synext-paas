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
 * PlanTypeInputDTO对象
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
@ApiModel(value="InputPlanTypeDTO对象")
public class PlanTypeInputDTO extends BaseDTO<PlanTypeInputDTO, TPlanType>  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "1-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 计划类别.0课题申报
    */
    @ApiModelProperty(value = "2-计划类别.0课题申报" )
    private Integer planType;
    /**
    * 名称
    */
    @ApiModelProperty(value = "3-名称" )
    private String name;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "4-创建时间" )
    private String createTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "5-创建人" )
    private String createUserId;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "6-是否有效(0否,1是)" )
    private Integer active;

    public static Converter<PlanTypeInputDTO, TPlanType> converter = new Converter<PlanTypeInputDTO, TPlanType>() {
        @Override
        public TPlanType doForward(PlanTypeInputDTO planTypeInputDTO) {
            return WarpsUtils.copyTo(planTypeInputDTO, TPlanType.class);
        }

        @Override
        public PlanTypeInputDTO doBackward(TPlanType planType) {
            return WarpsUtils.copyTo(planType, PlanTypeInputDTO.class);
        }
    };

    @Override
    public TPlanType convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public PlanTypeInputDTO convertFor(TPlanType planType) {
        return converter.doBackward(planType);
    }
}
