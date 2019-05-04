package com.project.bootfx.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootFxApplication extends Application {

    private ConfigurableApplicationContext springContext;
    private TabPane tabPane;
    private FXMLLoader fxmlLoader;

    private static final String TAB_PANE = "/TabPane.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(SpringBootFxApplication.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fxmlLoader.setLocation(getClass().getResource(TAB_PANE));
        tabPane = fxmlLoader.load();

        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(tabPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }
}
