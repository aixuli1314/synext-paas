package co.synext;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import co.synext.javafx.util.AppUtil;
import co.synext.javafx.view.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.SplashScreen;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 代码生成
 */
@MapperScan(value = "co.synext.dao")
@SpringBootApplication
public class BuilderApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        try {
            AppUtil.checkRepeatRun();
            launch(BuilderApplication.class, MainView.class, new NonSplashScreen(), args);
        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.showErrorAlert(e);
        }
    }

    static class NonSplashScreen extends SplashScreen {

        @Override
        public Parent getParent() {
            ImageView imageView = new ImageView("http://www.synext.co/static/www/jlwh/images/synextlogo1.png");
            imageView.setFitHeight(1000);
            imageView.setFitWidth(800);
            imageView.setPreserveRatio(true);
            ProgressBar splashProgressBar = new ProgressBar();
            splashProgressBar.setPrefWidth(imageView.getFitWidth());
            VBox vbox = new VBox();
            vbox.getChildren().addAll(new Node[]{imageView, splashProgressBar});
            return vbox;
        }

        @Override
        public boolean visible() {
            return true;
        }
    }

    @Override
    public void beforeInitialView(Stage stage, ConfigurableApplicationContext ctx) {
        super.beforeInitialView(stage, ctx);
        stage.setTitle("Hello world!");
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
