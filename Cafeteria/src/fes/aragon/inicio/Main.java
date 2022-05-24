package fes.aragon.inicio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {

			Pane root = FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/AdministradorAceptar.fxml"));
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image("/fes/aragon/recursos/cafeIcono.png"));
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setRoot(String fxml) {

	}

	public static void main(String[] args) {
		launch(args);
	}
}
