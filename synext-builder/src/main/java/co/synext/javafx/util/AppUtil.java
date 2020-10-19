package co.synext.javafx.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

import co.synext.javafx.model.TableInfoModel;

/**
 * Description:
 *
 * @author xu.ran
 * @create 2017-12-27
 **/
public class AppUtil {

    private static final ThreadLocal<TableInfoModel> THREAD_LOCAL = new ThreadLocal<>();


    public static void put(TableInfoModel tableInfoModel) {
        THREAD_LOCAL.set(tableInfoModel);
    }

    public static TableInfoModel get() {
        return THREAD_LOCAL.get();
    }

    public static void checkRepeatRun() throws Exception {
        try {
            ServerSocketChannel.open().socket().bind(new InetSocketAddress(65478));
        } catch (Exception e) {
            throw new Exception("请不要重复运行程序！");
        }
    }

    public static void showErrorAlert(Throwable throwable) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, throwable.getMessage());
            alert.showAndWait().ifPresent(r -> Platform.exit());

        });
    }

    public static void showSuccessAlert(String msg) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
            alert.showAndWait();

        });
    }

    public static void showErrorAlert(String msg) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, msg);
            alert.showAndWait();

        });
    }


    public static ImageView getDocumentImageView() {
        return new ImageView(new Image(AppUtil.class.getClass().getResource("/icons/document_16x16.png").toExternalForm()));
    }
}
