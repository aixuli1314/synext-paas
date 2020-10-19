package co.synext.javafx.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableInfoModel {

    private StringProperty tableName;

    private StringProperty tableComment;

    private StringProperty engine;

    private StringProperty createTime;

    public String getTableName() {
        return tableName.get();
    }

    public StringProperty tableNameProperty() {
        return tableName;
    }

    public String getTableComment() {
        return tableComment.get();
    }

    public StringProperty tableCommentProperty() {
        return tableComment;
    }

    public String getCreateTime() {
        return createTime.get();
    }

    public StringProperty createTimeProperty() {
        return createTime;
    }

    public String getEngine() {
        return engine.get();
    }

    public StringProperty engineProperty() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine.set(engine);
    }

    public TableInfoModel() {
        this.tableName = new SimpleStringProperty();
        this.tableComment = new SimpleStringProperty();
        this.engine = new SimpleStringProperty();
        this.createTime = new SimpleStringProperty();
    }
}