package co.synext.javafx.controller;

import com.baomidou.mybatisplus.generator.AutoGenerator;

import co.synext.config.BuilderConfig;
import co.synext.config.fileout.InputDTOFileOutConfig;
import co.synext.config.fileout.QueryDTOFileOutConfig;
import co.synext.config.fileout.UpdateDTOFileOutConfig;
import co.synext.config.fileout.VoFileOutConfig;
import co.synext.javafx.util.AppUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@Slf4j
@FXMLController
public class SetupController {

    @FXML
    private TextField tableName;

    @FXML
    private TextField packageName;

    @FXML
    private TextField moduleName;

    @FXML
    private TextField author;

    @FXML
    private ChoiceBox serviceClassName;

    @FXML
    private ChoiceBox controllerClassName;

    @FXML
    private ChoiceBox onlyGenMapper;

    @FXML
    private ChoiceBox genOverride;

    @Autowired
    private BuilderConfig builderConfig;

    @FXML
    protected void initialize() {
        log.info("初始化 ==> {}", AppUtil.get());
        tableName.textProperty().bindBidirectional(AppUtil.get().tableNameProperty());
        tableName.setDisable(true);
        packageName.setText("co.synext.module");
        moduleName.setText("demo");
        author.setText("xu.ran");
        serviceClassName.setItems(FXCollections.observableArrayList(
                "Service类名包含模块名", "Service类名不包含模块名")
        );

        serviceClassName.setValue("Service类名不包含模块名");
        controllerClassName.setItems(FXCollections.observableArrayList(
                "Controller类名包含模块名", "Controller类名不包含模块名")
        );

        controllerClassName.setValue("Controller类名不包含模块名");

        onlyGenMapper.setItems(FXCollections.observableArrayList(
                "是", "否")
        );
        onlyGenMapper.setValue("否");

        genOverride.setItems(FXCollections.observableArrayList(
                "是", "否")
        );
        genOverride.setValue("否");
    }

    @FXML
    protected void doGenerate() throws SQLException {
        AutoGenerator autoGenerator = builderConfig.createAutoGenerator();
        if (preCheckParam()) {
            autoGenerator.getStrategy().setInclude(tableName.textProperty().getValue());
            log.info("tableName ==> {}", tableName.textProperty().getValue());
            autoGenerator.getPackageInfo()
                    .setParent(packageName.textProperty().getValue())
                    .setModuleName(moduleName.textProperty().getValue());
            log.info("packageName ==> {}", packageName.textProperty().getValue());
            log.info("moduleName ==> {}", moduleName.textProperty().getValue());
            autoGenerator.getGlobalConfig().setAuthor(author.textProperty().getValue());
            log.info("author ==> {}", author.textProperty().getValue());

            String serviceClassNameValue = (String) serviceClassName.getValue();

            if (serviceClassNameValue.equals("Service类名包含模块名")) {
                autoGenerator.getCfg().getMap().put("serviceClassNameType", 1);
            } else {
                autoGenerator.getCfg().getMap().put("serviceClassNameType", 0);
            }

            String controllerClassNameValue = (String) controllerClassName.getValue();

            if (controllerClassNameValue.equals("Controller类名包含模块名")) {
                autoGenerator.getCfg().getMap().put("controllerClassNameType", 1);
            } else {
                autoGenerator.getCfg().getMap().put("controllerClassNameType", 0);
            }

            String onlyGeneratorMapper = (String) onlyGenMapper.getValue();

            if (onlyGeneratorMapper.equals("是")) {
                autoGenerator.getCfg().getMap().put("onlyGenMapper", 1);
            } else {
                autoGenerator.getCfg().getMap().put("onlyGenMapper", 0);
            }

            String genOverrideFile = (String) genOverride.getValue();

            if (genOverrideFile.equals("是")) {
                autoGenerator.getGlobalConfig().setFileOverride(true);
            } else {
                autoGenerator.getGlobalConfig().setFileOverride(false);
            }

            //自定义vo文件生成
            VoFileOutConfig voConfig = new VoFileOutConfig();
            voConfig.setModule(autoGenerator.getPackageInfo().getParent());
            voConfig.setDirPath(autoGenerator.getGlobalConfig().getOutputDir());
            autoGenerator.getCfg().getMap().put("VOPkg", voConfig.getPkg());
            autoGenerator.getCfg().getFileOutConfigList().add(voConfig);

            //自定义vo文件生成
            UpdateDTOFileOutConfig updateDtoFileOutConfig = new UpdateDTOFileOutConfig();
            updateDtoFileOutConfig.setModule(autoGenerator.getPackageInfo().getParent());
            updateDtoFileOutConfig.setDirPath(autoGenerator.getGlobalConfig().getOutputDir());
            autoGenerator.getCfg().getMap().put("DTOPkg", updateDtoFileOutConfig.getPkg());
            autoGenerator.getCfg().getFileOutConfigList().add(updateDtoFileOutConfig);

            //自定义vo文件生成
            InputDTOFileOutConfig inputDtoFileOutConfig = new InputDTOFileOutConfig();
            inputDtoFileOutConfig.setModule(autoGenerator.getPackageInfo().getParent());
            inputDtoFileOutConfig.setDirPath(autoGenerator.getGlobalConfig().getOutputDir());
            autoGenerator.getCfg().getMap().put("DTOPkg", inputDtoFileOutConfig.getPkg());
            autoGenerator.getCfg().getFileOutConfigList().add(inputDtoFileOutConfig);

            //自定义vo文件生成
            QueryDTOFileOutConfig queryDtoFileOutConfig = new QueryDTOFileOutConfig();
            queryDtoFileOutConfig.setModule(autoGenerator.getPackageInfo().getParent());
            queryDtoFileOutConfig.setDirPath(autoGenerator.getGlobalConfig().getOutputDir());
            autoGenerator.getCfg().getMap().put("DTOPkg", queryDtoFileOutConfig.getPkg());
            autoGenerator.getCfg().getFileOutConfigList().add(queryDtoFileOutConfig);

            //生成代码
            autoGenerator.execute();
            AppUtil.showSuccessAlert("代码生成成功！");
        }
    }

    private boolean preCheckParam() {
        boolean success = true;
        if (StringUtils.isEmpty(tableName.getText())) {
            AppUtil.showErrorAlert("表名不能为空！");
            tableName.setFocusTraversable(true);
            success = false;
        }
        if (StringUtils.isEmpty(packageName.getText())) {
            AppUtil.showErrorAlert("包名不能为空！");
            packageName.setFocusTraversable(true);
            success = false;
        }
        if (StringUtils.isEmpty(moduleName.getText())) {
            AppUtil.showErrorAlert("模块名不能为空！");
            moduleName.setFocusTraversable(true);
            success = false;
        }
        if (StringUtils.isEmpty(author.getText())) {
            AppUtil.showErrorAlert("作者不能为空！");
            author.setFocusTraversable(true);
            success = false;
        }
        return success;
    }
}
