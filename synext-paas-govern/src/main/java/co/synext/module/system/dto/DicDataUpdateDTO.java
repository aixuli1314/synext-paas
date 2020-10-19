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

/**
 * <p>
 * TDicDataUpdateDTO对象
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
@ApiModel(value="DicDataUpdateDTO对象")
public class DicDataUpdateDTO extends BaseDTO<DicDataUpdateDTO, TDicData>  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
    * 名称
    */
    @ApiModelProperty(value = "2-名称" )
    private String name;
    /**
    * 编码
    */
    @ApiModelProperty(value = "3-编码" )
    private String code;
    /**
    * 路径代码,用于标识字典路径,例如 pid是root,此菜单的comcode是  ,root,mydataid,
    */
    @ApiModelProperty(value = "4-路径代码,用于标识字典路径,例如 pid是root,此菜单的comcode是  ,root,mydataid," )
    private String comcode;
    /**
    * 值
    */
    @ApiModelProperty(value = "5-值" )
    private String val;
    /**
    * 父级ID
    */
    @ApiModelProperty(value = "6-父级ID" )
    private String pid;
    /**
    * 排序
    */
    @ApiModelProperty(value = "7-排序" )
    private Integer sortno;
    /**
    * 描述
    */
    @ApiModelProperty(value = "8-描述" )
    private String remark;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "9-是否有效(0否,1是)" )
    private Integer active;
    /**
    * 类型
    */
    @ApiModelProperty(value = "10-类型" )
    private String pathkey;

    public static Converter<DicDataUpdateDTO, TDicData> converter = new Converter<DicDataUpdateDTO, TDicData>() {
        @Override
        public TDicData doForward(DicDataUpdateDTO dicDataUpdateDTO) {
            return WarpsUtils.copyTo(dicDataUpdateDTO, TDicData.class);
        }

        @Override
        public DicDataUpdateDTO doBackward(TDicData dicData) {
            return WarpsUtils.copyTo(dicData, DicDataUpdateDTO.class);
        }
    };

    @Override
    public TDicData convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public DicDataUpdateDTO convertFor(TDicData dicData) {
        return converter.doBackward(dicData);
    }
}
