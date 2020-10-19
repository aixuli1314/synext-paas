package co.synext.module.system.dto;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import co.synext.mybatis.entity.TOrgEnterprise;
import co.synext.common.base.convert.Converter;
import co.synext.common.base.dto.BaseDTO;
import co.synext.common.utils.WarpsUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * OrgEnterpriseQueryDTO对象
 *  企业信息表,企业实际是t_org部门,orgType=1.给部门创建一个账号,作为主管,用于登录.附件关联附件表t_attachment
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="OrgEnterpriseQueryDTO对象")
public class OrgEnterpriseQueryDTO extends BaseDTO<OrgEnterpriseQueryDTO, TOrgEnterprise>  implements Serializable {

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
    * 企业名称,和从t_org的name字段同步过来,和营业执照保持一致
    */
    @ApiModelProperty(value = "4-企业名称,和从t_org的name字段同步过来,和营业执照保持一致" )
    private String name;
    /**
    * 联系地址
    */
    @ApiModelProperty(value = "5-联系地址" )
    private String contactAddress;
    /**
    * 联系人姓名
    */
    @ApiModelProperty(value = "6-联系人姓名" )
    private String contactPersonName;
    /**
    * 联系电话
    */
    @ApiModelProperty(value = "7-联系电话" )
    private String contactPhone;
    /**
    * 联系邮箱
    */
    @ApiModelProperty(value = "8-联系邮箱" )
    private String contactEmail;
    /**
    * 营业执照类型,例如:有限责任
    */
    @ApiModelProperty(value = "9-营业执照类型,例如:有限责任" )
    @JsonProperty(value = "bType")
    private String bType;
    /**
    * 营业执照注册地
    */
    @ApiModelProperty(value = "10-营业执照注册地" )
    @JsonProperty(value = "bAddress")
    private String bAddress;
    /**
    * 注册法人
    */
    @ApiModelProperty(value = "11-注册法人" )
    @JsonProperty(value = "bLegalPerson")
    private String bLegalPerson;
    /**
    * 注册资本
    */
    @ApiModelProperty(value = "12-注册资本" )
    @JsonProperty(value = "bCapital")
    private String bCapital;
    /**
    * 成立日期
    */
    @ApiModelProperty(value = "13-成立日期" )
    @JsonProperty(value = "bCreateTime")
    private LocalDate bCreateTime;
    /**
    * 营业期限开始时间
    */
    @ApiModelProperty(value = "14-营业期限开始时间" )
    @JsonProperty(value = "bTermStart")
    private LocalDate bTermStart;
    /**
    * 营业期限结束时间
    */
    @ApiModelProperty(value = "15-营业期限结束时间" )
    @JsonProperty(value = "bTermEnd")
    private LocalDate bTermEnd;
    /**
    * 经营范围
    */
    @ApiModelProperty(value = "16-经营范围" )
    @JsonProperty(value = "bScope")
    private String bScope;
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

    public Page<TOrgEnterprise> page() {
        return new Page<>(current, size);
    }

    public static Converter<OrgEnterpriseQueryDTO, TOrgEnterprise> converter = new Converter<OrgEnterpriseQueryDTO, TOrgEnterprise>() {
        @Override
        public TOrgEnterprise doForward(OrgEnterpriseQueryDTO orgEnterpriseQueryDTO) {
            return WarpsUtils.copyTo(orgEnterpriseQueryDTO, TOrgEnterprise.class);
        }

        @Override
        public OrgEnterpriseQueryDTO doBackward(TOrgEnterprise orgEnterprise) {
            return WarpsUtils.copyTo(orgEnterprise, OrgEnterpriseQueryDTO.class);
        }
    };

    @Override
    public TOrgEnterprise convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public OrgEnterpriseQueryDTO convertFor(TOrgEnterprise orgEnterprise) {
        return converter.doBackward(orgEnterprise);
    }
}
