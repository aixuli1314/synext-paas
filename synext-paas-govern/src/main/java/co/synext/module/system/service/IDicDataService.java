package co.synext.module.system.service;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TDicData;
import co.synext.module.system.dto.DicDataInputDTO;
import co.synext.module.system.dto.DicDataUpdateDTO;
import co.synext.module.system.dto.DicDataQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-07
 */
public interface IDicDataService extends IService<TDicData> {

    /**
     * 分页方法
     *
     * @param dictQueryDTO
     * @return
     */
    ReturnDatas page(DicDataQueryDTO dictQueryDTO);

    /**
     * 保存方法
     *
     * @param dictInputDTO
     * @return
     */
    ReturnDatas save(DicDataInputDTO dictInputDTO);

    /**
     * 更新方法
     *
     * @param dictUpdateDTO
     * @return
     */

    ReturnDatas update(DicDataUpdateDTO dictUpdateDTO);
    /**
     * 获取字典树
     * @return
     */
    ReturnDatas getDicDataTree();

    /**
     * 查询方法
     *
     * @param id
     * @return
     */
    ReturnDatas findById(Serializable id);

    /**
     * 删除方法
     *
     * @param id
     * @return
     */
    ReturnDatas deleteById(Serializable id);

    /**
     * 根据parentId获取字典
     *
     * @param id
     * @return
     */
    List<TDicData> findByParentId(Serializable id);

    /**
     * 根据code 和 parentId 查询
     *
     * @param code
     * @param parentId
     * @return
     */
    List<TDicData> findByCodeAndParentId(String code, Serializable parentId);

    /**
     * 根据字典key和value获取字典详情
     *
     * @param dictCode
     * @param dictValue
     * @return
     */
    TDicData findByCodeAndValue(String dictCode, String dictValue);
}
