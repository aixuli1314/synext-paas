package co.synext.module.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
 * OrgInputDTO对象
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
@ApiModel(value="InputOrgDTO对象")
public class OrgInputDTO extends BaseDTO<OrgInputDTO, TOrg>  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 名称
    */
    @ApiModelProperty(value = "1-名称" )
    private String name;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "2-创建时间")
    private String createTime;
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
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID")
    private String createUserId;

    private List<Long> roleIds;


    public static Converter<OrgInputDTO, TOrg> converter = new Converter<OrgInputDTO, TOrg>() {
        @Override
        public TOrg doForward(OrgInputDTO orgInputDTO) {
            return WarpsUtils.copyTo(orgInputDTO, TOrg.class);
        }

        @Override
        public OrgInputDTO doBackward(TOrg org) {
            return WarpsUtils.copyTo(org, OrgInputDTO.class);
        }
    };

    @Override
    public TOrg convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public OrgInputDTO convertFor(TOrg org) {
        return converter.doBackward(org);
    }
}
