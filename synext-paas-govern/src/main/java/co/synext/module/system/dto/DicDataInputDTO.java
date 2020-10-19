package co.synext.module.system.dto;

import java.io.Serializable;
import co.synext.mybatis.entity.TDicData;
import co.synext.common.base.convert.Converter;
import co.synext.common.base.dto.BaseDTO;
import co.synext.common.utils.WarpsUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * DicDataInputDTO对象
 * 公共的字典,通过pathkey区分不通的字典类型,例如 民族字典 pathkey 的值都是minzu, controller通过 /api/dicdata/{pathkey}/list 进行查看
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="InputDicDataDTO对象")
public class DicDataInputDTO extends BaseDTO<DicDataInputDTO, TDicData>  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 名称
    */
    @ApiModelProperty(value = "1-名称" )
    private String name;
    /**
    * 编码
    */
    @ApiModelProperty(value = "2-编码" )
    @NotNull
    private String code;
    /**
    * 路径代码,用于标识字典路径,例如 pid是root,此菜单的comcode是  ,root,mydataid,
    */
    @ApiModelProperty(value = "3-路径代码,用于标识字典路径,例如 pid是root,此菜单的comcode是  ,root,mydataid," )
    private String comcode;
    /**
    * 值
    */
    @ApiModelProperty(value = "4-值" )
    private String val;
    /**
    * 父级ID
    */
    @ApiModelProperty(value = "5-父级ID" )
    private String pid;
    /**
    * 排序
    */
    @ApiModelProperty(value = "6-排序" )
    private Integer sortno;
    /**
    * 描述
    */
    @ApiModelProperty(value = "7-描述" )
    private String remark;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "8-是否有效(0否,1是)" )
    private Integer active;
    /**
    * 类型
    */
    @ApiModelProperty(value = "9-类型" )
    private String pathkey;

    public static Converter<DicDataInputDTO, TDicData> converter = new Converter<DicDataInputDTO, TDicData>() {
        @Override
        public TDicData doForward(DicDataInputDTO dicDataInputDTO) {
            return WarpsUtils.copyTo(dicDataInputDTO, TDicData.class);
        }

        @Override
        public DicDataInputDTO doBackward(TDicData dicData) {
            return WarpsUtils.copyTo(dicData, DicDataInputDTO.class);
        }
    };

    @Override
    public TDicData convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public DicDataInputDTO convertFor(TDicData dicData) {
        return converter.doBackward(dicData);
    }
}
