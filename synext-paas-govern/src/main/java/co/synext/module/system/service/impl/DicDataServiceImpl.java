package co.synext.module.system.service.impl;

import cn.hutool.core.util.StrUtil;
import co.synext.module.system.dto.UserDTO;
import co.synext.module.system.vo.DicDataVO;
import co.synext.module.system.vo.OrgVO;
import co.synext.mybatis.entity.TOrg;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.enums.Enums;
import co.synext.mybatis.entity.TDicData;
import co.synext.mybatis.mapper.TDicDataMapper;
import co.synext.module.system.service.IDicDataService;
import co.synext.module.system.dto.DicDataInputDTO;
import co.synext.module.system.dto.DicDataUpdateDTO;
import co.synext.module.system.dto.DicDataQueryDTO;
import co.synext.common.base.service.BaseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-07
 */
@Service
public class DicDataServiceImpl extends BaseService<TDicDataMapper, TDicData> implements IDicDataService {

    @Override
    public ReturnDatas page(DicDataQueryDTO dictQueryDTO) {
        LambdaQueryWrapper<TDicData> lambdaQueryWrapper = getLambdaQueryWrapper();

        if(StrUtil.isNotEmpty(dictQueryDTO.getId()))
            lambdaQueryWrapper.eq(TDicData::getId,dictQueryDTO.getId());
/*        if (dictQueryDTO.getPid() == null)
            lambdaQueryWrapper.eq(TDicData::getPid, 0);*/
        if(StrUtil.isNotEmpty(dictQueryDTO.getPathkey()))
            lambdaQueryWrapper.eq(TDicData::getPathkey,dictQueryDTO.getPathkey());

        if (StrUtil.isNotEmpty(dictQueryDTO.getName()))
            lambdaQueryWrapper.like(TDicData::getName, "%" + dictQueryDTO.getName() + "%");

        return ReturnDatas.ok(baseMapper.selectPage(dictQueryDTO.page(), lambdaQueryWrapper
                .eq(TDicData::getActive, Enums.ActiveStateEnum.启用.getCode())
                .orderByAsc(TDicData::getSortno)
        ));
    }


    @Override
    public ReturnDatas save(DicDataInputDTO dictInputDTO) {
        TDicData dict = dictInputDTO.convertToEntity();
        baseMapper.insert(dict);
        return ReturnDatas.ok();
    }

    @Override
    public ReturnDatas update(DicDataUpdateDTO dictUpdateDTO) {
        TDicData dict = dictUpdateDTO.convertToEntity();
        baseMapper.updateById(dict);
        return ReturnDatas.ok();
    }

    @Override
    public ReturnDatas getDicDataTree() {
        // 原始的数据
        List<TDicData> allDicData = getAllDicData();

        ArrayList<TDicData> dicDataArrayList = new ArrayList<>();
        allDicData.forEach(dicData -> {
            dicDataArrayList.add(dicData);
        });
        // 最后的结果
        ArrayList<DicDataVO> dicDataTree = new ArrayList<>();
        //先找到所有的一级权限
        for(TDicData dicData : dicDataArrayList) {
            if(dicData.getPid().equals("0")){
                DicDataVO dicDataVO = copy2(dicData, DicDataVO.class);
                dicDataTree.add(dicDataVO);
            }
        }
        for(DicDataVO dicDataVO : dicDataTree) {
            List<TDicData> child = getChild(dicDataVO.getId(), dicDataArrayList);
            dicDataVO.setChildDict(child);
        }
        return ReturnDatas.ok(dicDataTree);
    }
    //递归获取子组织
    public List<TDicData> getChild(String id,List<TDicData> AllDicData) {
        //子数据
        List<TDicData> childList = new ArrayList<>();
        for (TDicData dicData : AllDicData) {
            if(!dicData.getPid().equals("0")){
                if(dicData.getPid().equals(id)) {
                    childList.add(dicData);
                }
            }
        }
        //递归终止的条件,没有子菜单时
        if(childList.size() == 0){
            return null;
        }
        //如果子菜单还有子菜单,把子菜单的子菜单再循环一遍
        for(TDicData dicData:childList) {
            if(!dicData.getPid().equals("0")){
                DicDataVO dicDataVO = copy2(dicData, DicDataVO.class);
                dicDataVO.setChildDict(getChild(dicDataVO.getId(),AllDicData));
            }
        }
        return childList;
    }
    //获取所有字典数据
    public List<TDicData> getAllDicData(){
        QueryWrapper queryWrapper = Wrappers.query();
        List<TDicData> dicDatas = baseMapper.selectList(queryWrapper);
/*        ArrayList<DicDataVO> dicDataList = new ArrayList<>();
        for (TDicData dicData : dicDatas) {
            DicDataVO dicDataVO = copy2(dicData, DicDataVO.class);
            dicDataList.add(dicDataVO);
        }*/
        return dicDatas;
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        return ReturnDatas.ok(baseMapper.selectById(id));
    }

    @Override
    public ReturnDatas deleteById(Serializable id) {
        baseMapper.deleteById(id);
        return ReturnDatas.ok();
    }

    @Override
    public List<TDicData> findByParentId(Serializable id) {
        return baseMapper.selectList(getLambdaQueryWrapper()
                .eq(TDicData::getPid, id)
                .eq(TDicData::getActive, Enums.ActiveStateEnum.启用.getCode())
                .orderByAsc(TDicData::getSortno)
        );
    }

    @Override
    public List<TDicData> findByCodeAndParentId(String code, Serializable parentId) {
        return baseMapper.selectList(getLambdaQueryWrapper()
                .eq(TDicData::getPid, parentId)
                .eq(TDicData::getCode, code)
                .eq(TDicData::getActive, Enums.ActiveStateEnum.启用.getCode())
                .orderByAsc(TDicData::getSortno));
    }

    @Override
    public TDicData findByCodeAndValue(String dictCode, String dictValue) {
        return baseMapper.selectOne(getLambdaQueryWrapper()
                .eq(TDicData::getCode, dictCode)
                .eq(TDicData::getVal, dictValue)
                .eq(TDicData::getActive, Enums.ActiveStateEnum.启用.getCode())
        );
    }

}
