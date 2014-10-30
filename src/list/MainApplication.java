package list;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApplication extends Application {

	private static final String APPLICATION_NAME = "LIST";	
	private Stage primaryStage;
	private StackPane mainLayout;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle(APPLICATION_NAME);
	
		initializeMainLayout();
	}
		
	private void initializeMainLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApplication.class.getResource("view/asdf.fxml"));
            mainLayout = (StackPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(mainLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
		} 
	}

	public static void main(String[] args) {
		launch(args);
	}

}
