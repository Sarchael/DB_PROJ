import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    private static final String ANCHOR_PANEFXML = "AnchorPane.fxml";

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(ANCHOR_PANEFXML));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Warsztat samochodowy - Martwe Dupy w Bagażniku");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
