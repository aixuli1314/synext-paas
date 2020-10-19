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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * PlanTypeQueryDTO对象
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
@ApiModel(value="PlanTypeQueryDTO对象")
public class PlanTypeQueryDTO extends BaseDTO<PlanTypeQueryDTO, TPlanType>  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

    private String id;
    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "3-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 计划类别.0课题申报
    */
    @ApiModelProperty(value = "4-计划类别.0课题申报" )
    private Integer planType;
    /**
    * 名称
    */
    @ApiModelProperty(value = "5-名称" )
    private String name;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "6-创建时间" )
    private String createTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "7-创建人" )
    private String createUserId;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "8-是否有效(0否,1是)" )
    private Integer active;

    public Page<TPlanType> page() {
        return new Page<>(current, size);
    }

    public static Converter<PlanTypeQueryDTO, TPlanType> converter = new Converter<PlanTypeQueryDTO, TPlanType>() {
        @Override
        public TPlanType doForward(PlanTypeQueryDTO planTypeQueryDTO) {
            return WarpsUtils.copyTo(planTypeQueryDTO, TPlanType.class);
        }

        @Override
        public PlanTypeQueryDTO doBackward(TPlanType planType) {
            return WarpsUtils.copyTo(planType, PlanTypeQueryDTO.class);
        }
    };

    @Override
    public TPlanType convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public PlanTypeQueryDTO convertFor(TPlanType planType) {
        return converter.doBackward(planType);
    }
}
