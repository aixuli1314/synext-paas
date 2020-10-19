package co.synext.module.system.dto;

import co.synext.mybatis.entity.TUserExpertAward;
import co.synext.mybatis.entity.TUserExpertProfession;
import co.synext.mybatis.entity.TUserExpertQualification;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import co.synext.mybatis.entity.TUserExpert;
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
 * UserExpertQueryDTO对象
 * 专家信息表,是t_user表的扩展表,userType=3
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-08
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UserExpertQueryDTO对象")
public class UserExpertQueryDTO extends BaseDTO<UserExpertQueryDTO, TUserExpert>  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "0-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "1-分页数量")
    public Integer size = 20;

    /**
     * id主键,和t_user的ID保持一致
     */
    @ApiModelProperty(value = "1-id主键,和t_user的ID保持一致" )
    private String id;
    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "3-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 专家名称,和从t_user的name字段同步过来
    */
    @ApiModelProperty(value = "4-专家名称,和从t_user的name字段同步过来" )
    private String name;

    private String mobile;
    /**
    * 单位名称
    */
    @ApiModelProperty(value = "5-单位名称" )
    private String unitName;
    /**
    * 注册状态,0资料保存未提交,1资料已提交,2内部审核中,3驳回,4申请成功
    */
    @ApiModelProperty(value = "6-注册状态,0资料保存未提交,1资料已提交,2内部审核中,3驳回,4申请成功" )
    private Integer status;
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
    /**
     * 专家资格
     */

    public Page<TUserExpert> page() {
        return new Page<>(current, size);
    }

    public static Converter<UserExpertQueryDTO, TUserExpert> converter = new Converter<UserExpertQueryDTO, TUserExpert>() {
        @Override
        public TUserExpert doForward(UserExpertQueryDTO userExpertQueryDTO) {
            return WarpsUtils.copyTo(userExpertQueryDTO, TUserExpert.class);
        }

        @Override
        public UserExpertQueryDTO doBackward(TUserExpert userExpert) {
            return WarpsUtils.copyTo(userExpert, UserExpertQueryDTO.class);
        }
    };

    @Override
    public TUserExpert convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public UserExpertQueryDTO convertFor(TUserExpert userExpert) {
        return converter.doBackward(userExpert);
    }
}
