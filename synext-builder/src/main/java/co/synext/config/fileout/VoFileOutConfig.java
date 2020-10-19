package co.synext.config.fileout;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import co.synext.config.BaseFileOutConfig;
import co.synext.config.CustomConstVal;


public class VoFileOutConfig extends BaseFileOutConfig {

    @Override
    public String getTemplatePath() {
        return CustomConstVal.TEMPLATE_VO + ".vm";
    }

    @Override
    public String outputFile(TableInfo tableInfo) {
        String fileName = getNonPrefixName(tableInfo.getName(), tableInfo.getEntityName()) + CustomConstVal.VO;
        return joinPath(dirPath, module + ".vo.") + fileName + CustomConstVal.JAVA_SUFFIX;
    }

    @Override
    public String getPkg() {
        return module + ".vo";
    }
}
