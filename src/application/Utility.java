package application;

// imports 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Niraj Bohra
 * @version 5.0
 * @classDescription It is a utility function that loads the UI.
 *
 */
public class Utility {

	private static Stage primaryStage = new Stage();

	@SuppressWarnings("rawtypes")
	public Stage displayUX(Class classname, String fxmlFile, String cssFile) throws Exception {
		try {
			ClassLoader classLoader = classname.getClassLoader();
			Parent root = FXMLLoader.load(classLoader.getResource(fxmlFile));
			primaryStage.setTitle("Ozlympic");
			Scene scene = new Scene(root);
			if (!Utility.isNullorEmpty(cssFile)) {
				scene.getStylesheets().add(classLoader.getResource(cssFile).toExternalForm());
			}
			primaryStage.setScene(scene);
			primaryStage.show();
			return primaryStage;
		} catch (Exception exp) {
			throw new Exception(exp);
		}
	}

	/*
	 * To check if a string is null or Empty
	 */
	public static Boolean isNullorEmpty(String value) {
		if (value != null && !"".equals(value)) {
			return false;
		} else {
			return true;
		}
	}

}
