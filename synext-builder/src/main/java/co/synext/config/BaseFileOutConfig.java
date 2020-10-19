package co.synext.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import lombok.Setter;

import java.io.File;

public abstract class BaseFileOutConfig extends FileOutConfig {

    @Setter
    protected String module;
    @Setter
    protected String dirPath;

    public abstract String getPkg();

    public String getNonPrefixName(String tableName, String entityName) {

        if (tableName.contains("_" )) {
            String prefix = StrUtil.split(tableName, "_" )[0];
            return StrUtil.removePrefix(entityName, StrUtil.upperFirst(prefix));
        } else {
            return entityName;
        }
    }

    protected String joinPath(String parentDir, String packageName) {
        if (StringUtils.isBlank(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\." , StringPool.BACK_SLASH + File.separator);
        return parentDir + packageName;
    }

}
