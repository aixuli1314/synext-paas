package co.synext.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.sun.org.apache.bcel.internal.classfile.ConstantValue;

import java.util.Map;


public class CustomFileGenerateConfig extends InjectionConfig {

    @Override
    public void initMap() {

    }

    @Override
    public Map<String, Object> prepareObjectMap(Map<String, Object> objectMap) {
        objectMap.putAll(getMap());
        String entity = (String) objectMap.get("entity");
        TableInfo tableInfo = (TableInfo) objectMap.get("table");
        Integer serviceClassNameType = (Integer) objectMap.get("serviceClassNameType");
        Integer controllerClassNameType = (Integer) objectMap.get("controllerClassNameType");
        Integer onlyGenMapper = (Integer) objectMap.get("onlyGenMapper");
        if (tableInfo.getName().contains("_")) {
            String prefix = StrUtil.split(tableInfo.getName(), "_")[0];
            String nonPrefixEntityNameUpper = StrUtil.removePrefix(entity, StrUtil.upperFirst(prefix));
            String nonPrefixEntityNameLower = StrUtil.lowerFirst(nonPrefixEntityNameUpper);
            String modelName = getConfig().getPackageInfo().get(CustomConstVal.MODULE_NAME);

            if (serviceClassNameType == 1) {
                tableInfo.setServiceImplName(StrUtil.upperFirst(modelName) + nonPrefixEntityNameUpper + "ServiceImpl");
                tableInfo.setServiceName("I" + StrUtil.upperFirst(modelName) + nonPrefixEntityNameUpper + "Service");
            } else {
                tableInfo.setServiceImplName(nonPrefixEntityNameUpper + "ServiceImpl");
                tableInfo.setServiceName("I" + nonPrefixEntityNameUpper + "Service");
            }

            if (controllerClassNameType == 1) {
                tableInfo.setControllerName(StrUtil.upperFirst(modelName) + nonPrefixEntityNameUpper + "Controller");
            } else {
                tableInfo.setControllerName(nonPrefixEntityNameUpper + "Controller");
            }

            objectMap.put("nonPrefixEntityNameLower", nonPrefixEntityNameLower);
            objectMap.put("nonPrefixEntityNameUpper", nonPrefixEntityNameUpper);
        } else {
            objectMap.put("nonPrefixEntityNameLower", StrUtil.lowerFirst(entity));
            objectMap.put("nonPrefixEntityNameUpper", StrUtil.upperFirst(entity));
        }

        if (onlyGenMapper == 1) {

            getConfig().getPathInfo().remove(CustomConstVal.CONTROLLER_PATH);
            getConfig().getPathInfo().remove(CustomConstVal.SERVICE_PATH);
            getConfig().getPathInfo().remove(CustomConstVal.SERVICE_IMPL_PATH);

            getConfig().getPackageInfo().remove(CustomConstVal.SERVICE_IMPL);
            getConfig().getPackageInfo().remove(CustomConstVal.SERVICE);
            getConfig().getPackageInfo().remove(CustomConstVal.CONTROLLER);
        }

        return objectMap;
    }
}
