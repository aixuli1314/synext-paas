package co.synext.module.system.dto;

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

/**
 * <p>
 * TOrgUpdateDTO对象
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
@ApiModel(value="OrgUpdateDTO对象")
public class OrgUpdateDTO extends BaseDTO<OrgUpdateDTO, TOrg>  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 编号
    */
    @ApiModelProperty(value = "1-编号" )
    private String id;
    /**
    * 名称
    */
    @ApiModelProperty(value = "2-名称" )
    private String name;
    /**
    * 路径代码,用于标识部门路径,例如 pid是root,此部门的comcode是  ,root,myorgid,
    */
    @ApiModelProperty(value = "3-路径代码,用于标识部门路径,例如 pid是root,此部门的comcode是  ,root,myorgid," )
    private String comcode;
    /**
    * 上级部门ID
    */
    @ApiModelProperty(value = "4-上级部门ID" )
    private String pid;
    /**
    * 0科技局
    */
    @ApiModelProperty(value = "5-0科技局,1注册企业,9虚拟部门" )
    private Integer orgType;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "6-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 备注
    */
    @ApiModelProperty(value = "7-备注" )
    private String remark;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "8-是否有效(0否,1是)" )
    private Integer active;
    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj
     */
    @ApiModelProperty(value = "9-URL路径中的部门类型,例如 URL路径中的 kjj")
    private String orgTypePathKey;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "10-更新时间")
    private String updateTime;
    /**
     * 更新者ID
     */
    @ApiModelProperty(value = "11-更新者ID")
    private String updateUserId;

    private List<Long> roleIds;
    public static Converter<OrgUpdateDTO, TOrg> converter = new Converter<OrgUpdateDTO, TOrg>() {
        @Override
        public TOrg doForward(OrgUpdateDTO orgUpdateDTO) {
            return WarpsUtils.copyTo(orgUpdateDTO, TOrg.class);
        }

        @Override
        public OrgUpdateDTO doBackward(TOrg org) {
            return WarpsUtils.copyTo(org, OrgUpdateDTO.class);
        }
    };

    @Override
    public TOrg convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public OrgUpdateDTO convertFor(TOrg org) {
        return converter.doBackward(org);
    }
}
