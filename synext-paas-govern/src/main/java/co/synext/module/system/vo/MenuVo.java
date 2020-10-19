package co.synext.module.system.vo;

import co.synext.mybatis.entity.TMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo extends TMenu {

    String parentName;

    List<MenuVo> children;

}
