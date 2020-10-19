package co.synext.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import co.synext.mybatis.entity.TDicData;
import co.synext.module.system.service.IDicDataService;
import co.synext.module.system.vo.DicDataVO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class DictUtils {

    private static IDicDataService dictService = SpringContextHolder.getBean(IDicDataService.class);

    public static Optional<DicDataVO> getOptionalByDictCode(String code) {
        DicDataVO dict = getByDictCode(code);
        if (dict != null)
            return Optional.of(dict);
        return Optional.empty();
    }

    public static DicDataVO getByDictCode(String code) {
        List<TDicData> sysDict = dictService.findByCodeAndParentId(code, Long.valueOf(0));
        if (CollectionUtil.isEmpty(sysDict)) return null;
        DicDataVO dict = WarpsUtils.copyTo(sysDict.get(0), DicDataVO.class);
        List<TDicData> childDictList = dictService.findByParentId(dict.getId());
        dict.setChildDict(childDictList);
        return dict;
    }

    public static DicDataVO getByCodeAndValue(String code, String value) {
        TDicData sysDict = dictService.findByCodeAndValue(code, value);
        if (sysDict == null) return null;
        return WarpsUtils.copyTo(sysDict, DicDataVO.class);
    }
    public static DicDataVO getByDictId(Serializable id) {
        TDicData sysDict = dictService.getById(id);
        if (sysDict==null) {
            return new DicDataVO();
        }
        DicDataVO dict = WarpsUtils.copyTo(sysDict, DicDataVO.class);
        return dict;
    }
}
