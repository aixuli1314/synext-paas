package co.synext.module.system.dto;

import co.synext.common.base.convert.Converter;
import co.synext.common.base.dto.BaseDTO;
import co.synext.common.utils.WarpsUtils;
import co.synext.mybatis.entity.TDicDataType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName(value = "t_dic_data_type")
public class DicDataTypeDTO extends BaseDTO<DicDataTypeDTO, TDicDataType> {

    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

    private String id;

    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj
     */
    @TableField("orgTypePathKey")
    private String orgTypePathKey;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String pathkey;

    /**
     * 是否有效(0否,1是)
     */
    private Integer active;

    public Page<TDicDataType> page() {
        return new Page<>(current, size);
    }

    public static Converter<DicDataTypeDTO, TDicDataType> converter = new Converter<DicDataTypeDTO, TDicDataType>() {

        @Override
        public TDicDataType doForward(DicDataTypeDTO dicDataTypeDTO) {
            return WarpsUtils.copyTo(dicDataTypeDTO, TDicDataType.class);
        }

        @Override
        public DicDataTypeDTO doBackward(TDicDataType dicDataType) {
            return WarpsUtils.copyTo(dicDataType, DicDataTypeDTO.class);
        }

    };


    @Override
    public TDicDataType convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public DicDataTypeDTO convertFor(TDicDataType dicDataType) {
        return converter.doBackward(dicDataType);
    }
}
