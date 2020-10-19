package co.synext.module.system.service;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.module.system.dto.DicDataTypeDTO;
import co.synext.mybatis.entity.TDicDataType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;

public interface IDicDataTypeService extends IService<TDicDataType> {
    /**
     * 分页方法
     *
     * @param dicDataTypeDTO
     * @return
     */
    ReturnDatas page(DicDataTypeDTO dicDataTypeDTO);

    /**
     * 保存方法
     *
     * @param dicDataTypeDTO
     * @return
     */
    ReturnDatas save(DicDataTypeDTO dicDataTypeDTO);

    /**
     * 更新方法
     *
     * @param dicDataTypeDTO
     * @return
     */
    ReturnDatas update(DicDataTypeDTO dicDataTypeDTO);

    /**
     * 查询方法
     *
     * @param id
     * @return
     */
    ReturnDatas findById(Serializable id);

    /**
     * 删除多个方法
     * @param ids
     * @return
     */
    ReturnDatas batchDelete(Collection<String> ids);

}
