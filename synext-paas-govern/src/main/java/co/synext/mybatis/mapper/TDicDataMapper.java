package co.synext.mybatis.mapper;

import co.synext.mybatis.entity.TDicData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 公共的字典,通过pathkey区分不通的字典类型,例如 民族字典 pathkey 的值都是minzu, controller通过 /api/dicdata/{pathkey}/list 进行查看 Mapper 接口
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
public interface TDicDataMapper extends BaseMapper<TDicData> {

}
