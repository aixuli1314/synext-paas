package co.synext.mybatis.mapper;

import co.synext.mybatis.entity.TRole;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
public interface TRoleMapper extends BaseMapper<TRole> {
	@Select("SELECT sr.* \n" +
            "FROM t_role sr \n" +
            "    join t_user_role sur \n" +
            "        on sr.id = sur.role_id \n" +
            "    join t_user su \n" +
            "        on sur.user_id = su.id\n" +
            "where su.id = #{userId}")
    @ResultMap("BaseResultMap")
    List<TRole> selectByUserId(@Param("userId") String userId);
}
