package co.synext.mybatis.mapper;

import co.synext.module.system.vo.MenuVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import co.synext.mybatis.entity.TMenu;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-10
 */
public interface TMenuMapper extends BaseMapper<TMenu> {

    List<TMenu> selectByRoleIds(Set<String> roleIds);

}
