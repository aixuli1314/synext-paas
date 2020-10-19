package co.synext.vo;

import cn.hutool.core.date.DateUtil;
import co.synext.javafx.model.TableInfoModel;
import lombok.Data;

import java.util.Date;

@Data
public class TableVo {


    private String tableName;

    private String tableComment;

    private String engine;

    private Date createTime;

    public TableInfoModel convert2TableInfoModel() {
        TableInfoModel tableInfoModel = new TableInfoModel();
        tableInfoModel.tableNameProperty().set(this.getTableName());
        tableInfoModel.tableCommentProperty().set(this.getTableComment());
        tableInfoModel.engineProperty().set(this.getEngine());
        tableInfoModel.createTimeProperty().set(DateUtil.format(this.getCreateTime(), "yyyy/MM/dd HH:mm"));
        return tableInfoModel;
    }
}
