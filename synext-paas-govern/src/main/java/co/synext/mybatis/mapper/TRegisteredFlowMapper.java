package co.synext.mybatis.mapper;

import co.synext.mybatis.entity.TRegisteredFlow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 注册流程,关联t_user和t_org,有专家注册和企业注册,企业注册.注册成功之后,短信通知 Mapper 接口
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
public interface TRegisteredFlowMapper extends BaseMapper<TRegisteredFlow> {

}
