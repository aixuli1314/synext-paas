package co.synext.module.system.service.impl;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.base.service.BaseService;
import co.synext.module.system.dto.DicDataTypeDTO;
import co.synext.module.system.service.IDicDataTypeService;
import co.synext.mybatis.entity.TDicDataType;
import co.synext.mybatis.mapper.TDicDataTypeMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

@Service
public class DicDataTypeServiceImpl extends BaseService<TDicDataTypeMapper, TDicDataType> implements IDicDataTypeService {
    @Override
    public ReturnDatas page(DicDataTypeDTO dicDataTypeDTO) {
        TDicDataType tDicDataType = dicDataTypeDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(dicDataTypeDTO.page(), getLambdaQueryWrapper().setEntity(tDicDataType)));
    }

    @Override
    public ReturnDatas save(DicDataTypeDTO dicDataTypeDTO) {
        TDicDataType tDicDataType = dicDataTypeDTO.convertToEntity();
        baseMapper.insert(tDicDataType);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas update(DicDataTypeDTO dicDataTypeDTO) {
        TDicDataType tDicDataType = dicDataTypeDTO.convertToEntity();
        baseMapper.updateById(tDicDataType);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        TDicDataType tDicDataType = baseMapper.selectById(id);
        return ReturnDatas.ok(tDicDataType);
    }

    @Override
    public ReturnDatas batchDelete(Collection<String> ids) {
        int i = baseMapper.deleteBatchIds(ids);
        if(i == 0){
            return ReturnDatas.error(false);
        }else {
            return ReturnDatas.ok(true);
        }
    }

}
