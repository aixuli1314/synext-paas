package co.synext.config.fileout;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import co.synext.config.BaseFileOutConfig;
import co.synext.config.CustomConstVal;


public class UpdateDTOFileOutConfig extends BaseFileOutConfig {

    @Override
    public String getTemplatePath() {
        return CustomConstVal.TEMPLATE_UPDATE_DTO + ".vm" ;
    }

    @Override
    public String outputFile(TableInfo tableInfo) {
        String fileName = getNonPrefixName(tableInfo.getName(), tableInfo.getEntityName()) + "Update" + CustomConstVal.DTO + CustomConstVal.JAVA_SUFFIX;
        return joinPath(dirPath, module + ".dto." ) + fileName;
    }

    @Override
    public String getPkg() {
        return module + ".dto" ;
    }
}
