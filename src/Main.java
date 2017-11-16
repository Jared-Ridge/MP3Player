import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
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

public class Main extends Application {

    public static VBox rightPane = new VBox(-1);
    public static Label displayFolder = new Label("No Folder Selected!");
    public static File fileFolder = new File("MusicTest");
    public static File folderLocation = new File("data/folderPath.txt");
    public static File[] listFilenames = fileFolder.listFiles();
    public static int currentScene = 0;
    public static Scene loadScene_1;
    public static Stage initializeStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(SceneOne.sceneOne());
        primaryStage.setTitle("PlayMP3"); // Name of application to display at top of window
        primaryStage.setResizable(false); //Sets the stage to be non-resizable.
        initializeStage = primaryStage;
        initializeStage.show(); //Shows the stage

    }

    public static void sceneChanger() {
        currentScene = currentScene + 1;
        if (currentScene > 3) {
            currentScene = 1;

        }
        if (currentScene == 1) {
            try {
                initializeStage.setScene(SceneOne.sceneOne());
            }catch(Exception e){
                e.printStackTrace();
            }

        }else  if (currentScene == 2) {
            try {
                initializeStage.setScene(SceneTwo.sceneTwo());
            }catch(Exception e){
                e.printStackTrace();
            }

        }else  if (currentScene == 3) {
            try {
                initializeStage.setScene(SceneThree.sceneThree());
            }catch(Exception e){
                e.printStackTrace();
            }

        } if (currentScene > 3 || currentScene < 1) {
            Alert invalidScene = new Alert(Alert.AlertType.ERROR);
            invalidScene.setTitle("Invalid Scene Number");
            invalidScene.setContentText("The Scene ID is " + currentScene + ".");
            invalidScene.setHeaderText("The Scene ID is invalid! Returning to default...");
            try {
                initializeStage.setScene(SceneOne.sceneOne());
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }


    public static void selectFolder(ActionEvent ae) {
        DirectoryChooser folderSelect = new DirectoryChooser();
        folderSelect.setTitle("Select Folder");
        File selectedFolder = folderSelect.showDialog(null);
        if (folderSelect == null) {
            if (displayFolder == null) {
                displayFolder.setText("No Folder Selected!");
            }
        } else {
            displayFolder.setText(selectedFolder.getPath()); // Sets the Label - What the user sees
            fileFolder = (selectedFolder); // Sets the directory - What the Computer sees
            System.out.println("Debug: " + displayFolder + " / " + fileFolder);
            listFilenames = fileFolder.listFiles();
            listFiles();
        }
    }

    public static void listFiles() {
        int x = 0;
        do{
            System.out.println(x);
            System.out.println(listFilenames[x]);
            x = x + 1;
        }while(x < fileFolder.listFiles().length);
    }
}