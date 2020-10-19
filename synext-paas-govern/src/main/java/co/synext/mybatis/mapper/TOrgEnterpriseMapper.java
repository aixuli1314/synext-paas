package co.synext.mybatis.mapper;

import co.synext.mybatis.entity.TOrgEnterprise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  企业信息表,企业实际是t_org部门,orgType=1.给部门创建一个账号,作为主管,用于登录.附件关联附件表t_attachment Mapper 接口
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
public interface TOrgEnterpriseMapper extends BaseMapper<TOrgEnterprise> {

}
