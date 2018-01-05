package plantenbahnen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    private FXMLLoader fxmlLoader;
    
    @Override
    public void start(Stage stage) throws Exception {

        URL location = getClass().getResource("FXMLDocument.fxml");
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        Parent root = (Parent) fxmlLoader.load(location.openStream());
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() {
        ((Controller) fxmlLoader.getController()).onCloseEvent();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
