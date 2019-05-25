package com.project.bootfx.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootFxApplication extends Application {

    private ConfigurableApplicationContext springContext;
    private AnchorPane anchorPane;
    private FXMLLoader fxmlLoader;

    public static Stage rootStage;

    private static final String ANCHOR_PANE = "/Logowanie.fxml";

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
        rootStage = primaryStage;
        fxmlLoader.setLocation(getClass().getResource(ANCHOR_PANE));
        anchorPane = fxmlLoader.load();
        anchorPane.getStyleClass().add("background");

        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add("test.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }
}
