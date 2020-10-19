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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * DicDataQueryDTO对象
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
@ApiModel(value="DicDataQueryDTO对象")
public class DicDataQueryDTO extends BaseDTO<DicDataQueryDTO, TDicData>  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

    /**
     * 名称
     */
    private String id;
    /**
    * 名称
    */
    @ApiModelProperty(value = "3-名称" )
    private String name;
    /**
    * 编码
    */
    @ApiModelProperty(value = "4-编码" )
    private String code;
    /**
    * 路径代码,用于标识字典路径,例如 pid是root,此菜单的comcode是  ,root,mydataid,
    */
    @ApiModelProperty(value = "5-路径代码,用于标识字典路径,例如 pid是root,此菜单的comcode是  ,root,mydataid," )
    private String comcode;
    /**
    * 值
    */
    @ApiModelProperty(value = "6-值" )
    private String val;
    /**
    * 父级ID
    */
    @ApiModelProperty(value = "7-父级ID" )
    private String pid;
    /**
    * 排序
    */
    @ApiModelProperty(value = "8-排序" )
    private Integer sortno;
    /**
    * 描述
    */
    @ApiModelProperty(value = "9-描述" )
    private String remark;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "10-是否有效(0否,1是)" )
    private Integer active;
    /**
    * 类型
    */
    @ApiModelProperty(value = "11-类型" )
    private String pathkey;

    public Page<TDicData> page() {
        return new Page<>(current, size);
    }

    public static Converter<DicDataQueryDTO, TDicData> converter = new Converter<DicDataQueryDTO, TDicData>() {
        @Override
        public TDicData doForward(DicDataQueryDTO dicDataQueryDTO) {
            return WarpsUtils.copyTo(dicDataQueryDTO, TDicData.class);
        }

        @Override
        public DicDataQueryDTO doBackward(TDicData dicData) {
            return WarpsUtils.copyTo(dicData, DicDataQueryDTO.class);
        }
    };

    @Override
    public TDicData convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public DicDataQueryDTO convertFor(TDicData dicData) {
        return converter.doBackward(dicData);
    }
}
