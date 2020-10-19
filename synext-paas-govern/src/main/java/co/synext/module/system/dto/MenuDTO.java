package co.synext.module.system.dto;

import co.synext.common.base.convert.Converter;
import co.synext.common.base.dto.BaseDTO;
import co.synext.common.utils.WarpsUtils;
import co.synext.mybatis.entity.TDicDataType;
import co.synext.mybatis.entity.TMenu;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;

@Data
@ToString
@TableName(value = "t_menu")
public class MenuDTO extends BaseDTO<MenuDTO, TMenu> {

    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

	private String id;

    @NotNull(message = "菜单名称不能为空！")
    private String name;

    @NotNull
    private Integer menuType;

    @NotNull
    private String comcode;
    
    @NotNull
    private String pid;
    private String remark;
    private String hrefurl;
    private String icon;
    private Integer sortno;

    private Integer active;
    private Integer hasChildren;
    
    private String permissionKey;

    public Page<TMenu> page() {
        return new Page<>(current, size);
    }

    public static Converter<MenuDTO, TMenu> converter = new Converter<MenuDTO, TMenu>() {
        @Override
        public TMenu doForward(MenuDTO menuDTO) {
            return WarpsUtils.copyTo(menuDTO,TMenu.class);
        }

        @Override
        public MenuDTO doBackward(TMenu tMenu) {
            return WarpsUtils.copyTo(tMenu,MenuDTO.class);
        }
    };

    @Override
    public TMenu convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public MenuDTO convertFor(TMenu tMenu) {
        return converter.doBackward(tMenu);
    }
}
