package co.synext.module.system.dto;

import co.synext.mybatis.entity.TUser;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import co.synext.mybatis.entity.TOrg;
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
 * OrgQueryDTO对象
 * 部门
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="OrgQueryDTO对象")
public class OrgQueryDTO extends BaseDTO<OrgQueryDTO, TOrg>  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 10;
    /**
     * 编号
     */
    @ApiModelProperty(value = "3-用户编号")
    private String id;
    /**
    * 名称
    */
    @ApiModelProperty(value = "4-名称" )
    private String name;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "5-创建时间")
    private String createTime;
    /**
    * 路径代码,用于标识部门路径,例如 pid是root,此部门的comcode是  ,root,myorgid,
    */
    @ApiModelProperty(value = "6-路径代码,用于标识部门路径,例如 pid是root,此部门的comcode是  ,root,myorgid," )
    private String comcode;
    /**
    * 上级部门ID
    */
    @ApiModelProperty(value = "7-上级部门ID" )
    private String pid;
    /**
    * 0科技局
    */
    @ApiModelProperty(value = "8-0科技局,1注册企业,9虚拟部门" )
    private Integer orgType;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "9-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 备注
    */
    @ApiModelProperty(value = "10-备注" )
    private String remark;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "11-是否有效(0否,1是)" )
    private Integer active;

    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj
     */
    @ApiModelProperty(value = "12-URL路径中的部门类型,例如 URL路径中的 kjj")
    private String orgTypePathKey;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "13-更新时间")
    private String updateTime;
    /**
     * 更新者ID
     */
    @ApiModelProperty(value = "14-更新者ID")
    private String updateUserId;
    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "15-创建者ID")
    private String createUserId;

    public Page<TOrg> page() {
        return new Page<>(current, size);
    }

    public static Converter<OrgQueryDTO, TOrg> converter = new Converter<OrgQueryDTO, TOrg>() {
        @Override
        public TOrg doForward(OrgQueryDTO orgQueryDTO) {
            return WarpsUtils.copyTo(orgQueryDTO, TOrg.class);
        }

        @Override
        public OrgQueryDTO doBackward(TOrg org) {
            return WarpsUtils.copyTo(org, OrgQueryDTO.class);
        }
    };

    @Override
    public TOrg convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public OrgQueryDTO convertFor(TOrg org) {
        return converter.doBackward(org);
    }
}
