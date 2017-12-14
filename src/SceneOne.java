import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.layout.*;

import javax.imageio.ImageIO;
import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

// https://stevebirtles.github.io/JavaFXPickNMix/

public class SceneOne extends Application {

    public static Scene loadScene_1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(sceneOne());
        primaryStage.setTitle("PlayMP3"); // Name of application to display at top of window
        primaryStage.setResizable(false); //Sets the stage to be non-resizable.
        Main.initializeStage = primaryStage;
        Main.initializeStage.show(); //Shows the stage

    }

    public static Scene sceneOne() throws Exception {
        Main.currentScene = 1;

        BorderPane loadBPane_1 = new BorderPane();

        loadBPane_1.getStylesheets().add("stylesheet.css"); // Opens CSS file


        loadBPane_1.setMinSize(980, 480);


        VBox leftPane = new VBox(20);
        Button leftButton1 = new Button("I am left.");
        leftButton1.setOnAction((ActionEvent ae) -> System.out.println("Oops! This button does nothing at the moment!"));
        leftPane.getChildren().add(leftButton1);
        Button leftButton2 = new Button("I am left again.");
        leftButton2.setOnAction((ActionEvent ae) -> System.out.println("Oops! This button does nothing at the moment!"));
        leftPane.getChildren().add(leftButton2);
        loadBPane_1.setLeft(leftPane);
        leftPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(leftPane, Pos.CENTER_LEFT);
        leftButton1.getStyleClass().add("button_layout_1");
        leftButton2.getStyleClass().add("button_layout_2");
        leftPane.getStyleClass().add("stage_background_2"); //Sets background colour
        leftPane.setPrefSize(213, 480);


        VBox rightPane = new VBox(-1);
        Button folderButton = new Button("Select Folder");
        folderButton.setOnAction((ActionEvent ae) -> Main.selectFolder(ae)); //Activates SelectFolder function
        rightPane.getChildren().add(folderButton);
        folderButton.setPrefWidth(413);
        Label lineSpacing_1 = new Label("If you can see this, something's gone wrong...");
        rightPane.getChildren().add(Main.displayFolder);
        Main.displayFolder.setPrefWidth(413);
        rightPane.getChildren().add(lineSpacing_1);
        Main.displayFolder.getStyleClass().add("label_layout_1");
        loadBPane_1.setRight(rightPane);
        rightPane.setAlignment(Pos.TOP_CENTER);
        BorderPane.setAlignment(rightPane, Pos.CENTER_RIGHT);
        folderButton.getStyleClass().add("button_layout_1");
        lineSpacing_1.getStyleClass().add("spacing_label");
        rightPane.getStyleClass().add("stage_background_2"); //Sets background colour
        rightPane.setPrefSize(313, 480);
        rightPane.getChildren().add(Main.nextLayout);

        VBox centerPane = new VBox(20);
        ScrollPane scroll = new ScrollPane(centerPane);
        loadBPane_1.setCenter(scroll);
        centerPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(scroll, Pos.CENTER);
        centerPane.getStyleClass().add("stage_background_1"); //Sets background colour
        Main.listFiles();

        HBox topBar = Main.topBar();
        loadBPane_1.setTop(topBar);
        loadScene_1 = new Scene(loadBPane_1);
        centerPane.setMaxHeight(480);
        scroll.setMaxHeight(480);
        scroll.getStyleClass().add("stage_background_2");
        int x = 0;
            if (Main.fileFolder.listFiles() == null) {
                System.out.println("The folder selected does not exist!");
            } else {
                do {
                    //String returnFilename = Main.fileType((String.valueOf(Main.listFilenames[x])));
                    //if(returnFilename.equals("mp3")) { //This line causes an instant crash.
                        centerPane.getChildren().add(new DisplayMP3().dSong(Main.listFilenames[x], 330));
                    //}
                        x = x + 1;
                } while (x < Main.fileFolder.listFiles().length);
            }
        return loadScene_1;
    }
}

