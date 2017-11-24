import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

// https://stevebirtles.github.io/JavaFXPickNMix/

public class Main extends Application {

    public static Label displayFolder = new Label("No Folder Selected!");
    public static File fileFolder = new File("MusicTest");
    public static File folderLocation = new File("data/folderPath.txt");
    public static File[] listFilenames;
    public static int currentScene = 0;
    public static Stage initializeStage;
    public static Button nextLayout = new Button(">");
    public static DatabaseConnection db = new DatabaseConnection("src/MusicDB.db");
    private static double xOff = 0;
    private static double yOff = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BufferedReader readFile = new BufferedReader(new FileReader(Main.folderLocation));
        fileFolder = new File(readFile.readLine());
        listFilenames = fileFolder.listFiles();
        primaryStage.setScene(SceneOne.sceneOne());
        primaryStage.setTitle("PlayMP3"); // Name of application to display at top of window
        initializeStage = primaryStage;
        initializeStage.initStyle(StageStyle.UNDECORATED); // To be done later maybe in free time
        initializeStage.initStyle(StageStyle.TRANSPARENT);
        nextLayout.setOnAction((ActionEvent ae) -> Main.sceneChanger());
        nextLayout.getStyleClass().add("button_layout_2");
        initializeStage.setMinWidth(980);
        initializeStage.setMaxWidth(980);
        initializeStage.setMinHeight(480);
        initializeStage.setMaxHeight(480);
        initializeStage.setResizable(true);
        System.out.println(readFile.readLine());
        Main.displayFolder.setText(readFile.readLine());
        initializeStage.show(); //Shows the stage
        readFile.close();

    }

    public static void sceneChanger() {
        currentScene = currentScene + 1;

        if (currentScene > 3) {
            currentScene = 1;
        }

        if (currentScene == 1) {
            try {
                initializeStage.setScene(SceneOne.sceneOne());


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else  if (currentScene == 2) {
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
            currentScene = 1;
            try {
                initializeStage.setScene(SceneOne.sceneOne());


            } catch (Exception e) {
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
            WriteTo();
        }
    }

    public static void listFiles() {
        int x = 0;
        if (fileFolder.listFiles() == null) {
            System.out.println("The folder selected does not exist!");
        } else {
            do {
                System.out.println(x + " (Real Value: " + (x + 1) + ")");
                System.out.println(listFilenames[x]);
                new DisplayMP3().dSong(listFilenames[x], 330);
                System.out.println();
                x = x + 1;
            } while (x < fileFolder.listFiles().length);
        }
    }

    public static void WriteTo() {
        BufferedWriter writeFile = null;
        try {
            FileWriter fw = new FileWriter(folderLocation);
            String fF = String.valueOf(fileFolder);
            fw.write(fF);
            fw.close();
            System.out.println("DEBUG: " + fF);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static HBox topBar() {
        HBox topBar = new HBox(0);

        Button minButton = new Button("-");
        minButton.getStyleClass().add("button_layout_5");
        minButton.setMaxHeight(20);
        minButton.setOnAction((ActionEvent ae) -> initializeStage.setIconified(true));
        topBar.getChildren().add(minButton);

        Button exitButton = new Button("X");
        exitButton.getStyleClass().add("button_layout_4");
        exitButton.setMaxHeight(20);
        exitButton.setOnAction((ActionEvent ae) -> System.exit(100));
        topBar.getChildren().add(exitButton);

        topBar.getStyleClass().add("stage_background_3");
        topBar.setAlignment(Pos.CENTER_RIGHT);

        topBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOff = initializeStage.getX() - event.getScreenX();
                yOff = initializeStage.getY() - event.getScreenY();

            }
        });

        topBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                initializeStage.setX(event.getScreenX() + xOff);
                initializeStage.setY(event.getScreenY() + yOff);
            }
        });

        /*
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
         */
        topBar.setMinHeight(30);
        topBar.setMaxHeight(30);
        return topBar;
    }

}

