import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// https://stevebirtles.github.io/JavaFXPickNMix/

public class SceneTwo extends Application {

    public static VBox rightPane = new VBox(-1);
    public static Scene loadScene_2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(sceneTwo());
        primaryStage.setTitle("PlayMP3"); // Name of application to display at top of window
        primaryStage.setResizable(false); //Sets the stage to be non-resizable.
        Main.initializeStage = primaryStage;
        Main.initializeStage.show(); //Shows the stage

    }

    public static Scene sceneTwo() throws Exception {
        Main.currentScene = 2;

        BorderPane loadBPane_2 = new BorderPane();

        loadBPane_2.getStylesheets().add("stylesheet.css"); // Opens CSS file

        BufferedReader readFile = new BufferedReader(new FileReader(Main.folderLocation));
        System.out.println(readFile.readLine());
        Main.displayFolder.setText(readFile.readLine());
        loadBPane_2.setPrefWidth(920);

        VBox leftPane = new VBox(20);
        Button leftButton1 = new Button("SECOND STAGE IS WORKING");
        leftButton1.setOnAction((ActionEvent ae) -> System.out.println("Oops! This button does nothing at the moment!"));
        leftPane.getChildren().add(leftButton1);
        Button leftButton2 = new Button("I am left again.");
        leftButton2.setOnAction((ActionEvent ae) -> System.out.println("Oops! This button does nothing at the moment!"));
        leftPane.getChildren().add(leftButton2);
        loadBPane_2.setLeft(leftPane);
        leftPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(leftPane, Pos.CENTER_LEFT);
        leftButton1.getStyleClass().add("button_layout_1");
        leftButton2.getStyleClass().add("button_layout_2");
        leftPane.getStyleClass().add("stage_background_2"); //Sets background colour
        leftPane.setPrefSize(213, 480);


        Button folderButton = new Button("Select Folder");
        folderButton.setOnAction((ActionEvent ae) -> Main.selectFolder(ae)); //Activates SelectFolder function
        rightPane.getChildren().add(folderButton);
        folderButton.setPrefWidth(413);
        Label lineSpacing_1 = new Label("If you can see this, something's gone wrong...");
        rightPane.getChildren().add(Main.displayFolder);
        Main.displayFolder.setPrefWidth(413);
        rightPane.getChildren().add(lineSpacing_1);
        Main.displayFolder.getStyleClass().add("label_layout_1");
        loadBPane_2.setRight(rightPane);
        rightPane.setAlignment(Pos.TOP_CENTER);
        BorderPane.setAlignment(rightPane, Pos.CENTER_RIGHT);
        folderButton.getStyleClass().add("button_layout_1");
        lineSpacing_1.getStyleClass().add("spacing_label");
        rightPane.getStyleClass().add("stage_background_2"); //Sets background colour
        rightPane.setPrefSize(313, 480);
        Button nextLayout = new Button(">");
        nextLayout.setOnAction((ActionEvent ae) -> Main.sceneChanger());
        rightPane.getChildren().add(nextLayout);

        VBox centerPane = new VBox(20);
        Button centerButton1 = new Button("I am centre.");
        centerButton1.setOnAction((ActionEvent ae) -> System.out.println("Oops! This button does nothing at the moment!"));
        centerPane.getChildren().add(centerButton1);
        Button centerButton2 = new Button("I am centre again.");
        centerButton2.setOnAction((ActionEvent ae) -> System.out.println("Oops! This button does nothing at the moment!"));
        centerPane.getChildren().add(centerButton2);
        loadBPane_2.setCenter(centerPane);
        centerPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(centerPane, Pos.CENTER);
        centerButton1.getStyleClass().add("button_layout_3");
        centerButton2.getStyleClass().add("button_layout_3");
        centerPane.getStyleClass().add("stage_background_1"); //Sets background colour
        Main.listFiles();


        loadScene_2 = new Scene(loadBPane_2);

        return loadScene_2;
    }
}

