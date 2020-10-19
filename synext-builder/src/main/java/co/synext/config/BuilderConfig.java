package co.synext.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

@Component
@Configuration
public class BuilderConfig {

    @Resource
    private DataSource dataSource;

    @Bean
    public PackageConfig packageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("co.synext.demo");
        packageConfig.setXml("mapper");
        return packageConfig;
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        String outPutDir = System.getProperty("user.dir") + "/synext-paas-govern/src/main/java";
        globalConfig.setOutputDir(outPutDir);
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        return globalConfig;
    }

    @Bean
    public DataSourceConfig dataSourceConfig() {
        DataSourceConfig dataSourceConfig = new CustomDataSourceConfig(dataSource);
        dataSourceConfig.setDriverName("mysql");
        return dataSourceConfig;
    }

    @Bean
    public StrategyConfig strategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperServiceImplClass("co.synext.common.base.service.BaseService");
        return strategy;
    }

    @Bean
    public InjectionConfig fileGenerateConfig() {
        InjectionConfig injectionConfig = new CustomFileGenerateConfig();
        injectionConfig.setFileOutConfigList(new ArrayList<>());
        injectionConfig.setMap(new HashMap<>());
        return injectionConfig;
    }


    public AutoGenerator createAutoGenerator() {
        return new CustomAutoGenerator()
                .setPackageInfo(packageConfig())
                .setGlobalConfig(globalConfig())
                .setDataSource(dataSourceConfig())
                .setStrategy(strategyConfig())
                .setCfg(fileGenerateConfig());
    }

    public static class CustomAutoGenerator extends AutoGenerator {
        @Override
        protected ConfigBuilder pretreatmentConfigBuilder(ConfigBuilder config) {
            ConfigBuilder configBuilder = super.pretreatmentConfigBuilder(config);
            PackageConfig packageConfig = this.getPackageInfo();

            String parent = "co" + StringPool.DOT + "synext" + StringPool.DOT + "mybatis";

            configBuilder.getPackageInfo().put(ConstVal.XML, (parent + StringPool.DOT + packageConfig.getXml()));
            configBuilder.getPackageInfo().put(ConstVal.MAPPER, (parent + StringPool.DOT + packageConfig.getMapper()));
            configBuilder.getPackageInfo().put(ConstVal.ENTITY, (parent + StringPool.DOT + packageConfig.getEntity()));

            configBuilder.getPathInfo().put(ConstVal.XML_PATH, joinPath(this.getGlobalConfig().getOutputDir(), configBuilder.getPackageInfo().get(ConstVal.XML)));
            configBuilder.getPathInfo().put(ConstVal.MAPPER_PATH, joinPath(this.getGlobalConfig().getOutputDir(), configBuilder.getPackageInfo().get(ConstVal.MAPPER)));
            configBuilder.getPathInfo().put(ConstVal.ENTITY_PATH, joinPath(this.getGlobalConfig().getOutputDir(), configBuilder.getPackageInfo().get(ConstVal.ENTITY)));

            return configBuilder;
        }

        private String joinPath(String parentDir, String packageName) {
            if (StringUtils.isBlank(parentDir)) {
                parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
            }
            if (!StringUtils.endsWith(parentDir, File.separator)) {
                parentDir += File.separator;
            }
            packageName = packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
            return parentDir + packageName;
        }
    }

}
