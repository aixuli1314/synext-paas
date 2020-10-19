package co.synext.javafx.controller;

import cn.hutool.core.collection.CollectionUtil;
import co.synext.BuilderApplication;
import co.synext.javafx.model.TableInfoModel;
import co.synext.javafx.service.TableService;
import co.synext.javafx.util.AppUtil;
import co.synext.javafx.view.SetupView;
import co.synext.vo.TableVo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Random;

@Slf4j
@FXMLController
public class MainController {

    private static int initiative = 0;

    protected String[] ws = {
            "醉卧沙场君莫笑，古来征战几人回"
            , "醉卧美人漆，醒掌杀人权",
            "用舍由人，行藏在我"
            , "一门七进士，父子三探花",
            "匈奴未灭，何以为家",
            "我本楚狂人，凤歌笑孔丘",
            "痛饮狂歌空度日,飞扬跋扈为谁雄"
    };

    @FXML
    private TableColumn tableName;

    @FXML
    private TableColumn tableComment;

    @FXML
    private TableColumn engine;

    @FXML
    private TableColumn createTime;

    @FXML
    private TableView tableList;

    @FXML
    private Pagination page;

    @FXML
    private Label msg;

    @Resource
    private TableService tableService;

    @FXML
    protected void initialize() {
        tableName.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        tableComment.setCellValueFactory(new PropertyValueFactory<>("tableComment"));
        engine.setCellValueFactory(new PropertyValueFactory<>("engine"));
        createTime.setCellValueFactory(new PropertyValueFactory<>("createTime"));
        page.setPageFactory(param -> {
            msg.setText(ws[new Random().nextInt(7)]);
            Page<TableVo> tableVoPage = tableService.getPage(param + 1, 5, "");
            updatePagination(tableVoPage);
            if (initiative != 0) {
                tableList = new TableView();
                tableList.setOnMouseClicked(event -> mouseEvent(event));
                tableList.getColumns().add(tableName);
                tableList.getColumns().add(tableComment);
                tableList.getColumns().add(engine);
                tableList.getColumns().add(createTime);
            }
            initiative = 1;
            tableList.setTooltip(new Tooltip("双击生成"));
            tableList.setItems(getObservableList(tableVoPage));
            return tableList;
        });
    }

    @FXML
    private void mouseEvent(MouseEvent event) {
        if (event.getClickCount() == 2) {
            TableInfoModel bean = (TableInfoModel) tableList.getSelectionModel().getSelectedItem();
            if (AppUtil.get() == null) {
                AppUtil.put(bean);
            } else {
                AppUtil.get().tableNameProperty().set(bean.getTableName());
            }
            BuilderApplication.showView(SetupView.class, Modality.WINDOW_MODAL);
        }
    }

    private void updatePagination(Page<TableVo> tableVoPage) {
        int pageCount = 0;
        int pageCurrent = Long.valueOf(tableVoPage.getCurrent()).intValue();
        int pageSize = Long.valueOf(tableVoPage.getSize()).intValue();
        int total = Long.valueOf(tableVoPage.getTotal()).intValue();
        if (total != 0 && pageSize > 0) {
            int result = (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
            if (result > 0) {
                pageCount = result;
            }
        }
        page.setPageCount(pageCount);
        page.setCurrentPageIndex(pageCurrent - 1);
    }

    public ObservableList<TableInfoModel> getObservableList(Page<TableVo> tableVoPage) {
        ObservableList<TableInfoModel> observableList = FXCollections.observableList(new ArrayList());
        if (CollectionUtil.isNotEmpty(tableVoPage.getRecords())) {
            tableVoPage.getRecords().forEach(tableVo -> {
                TableInfoModel tableInfoModel = tableVo.convert2TableInfoModel();
                observableList.add(tableInfoModel);
            });
        }
        return observableList;
    }
}
