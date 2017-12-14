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
import javafx.stage.FileChooser;
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
    public static int xFiles;
    public static String fileText = ".mp3";

    @Override
    public void start(Stage primaryStage) throws Exception {
        BufferedReader readFile = new BufferedReader(new FileReader(Main.folderLocation));
        fileFolder = new File(readFile.readLine());
        listFilenames = fileFolder.listFiles();
        primaryStage.setScene(SceneTwo.sceneTwo());
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
        initializeStage.show(); //Shows the stage
        System.out.println(readFile.readLine());
        Main.displayFolder.setText(readFile.readLine());
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
        } else if (currentScene == 2) {
            try {
                initializeStage.setScene(SceneTwo.sceneTwo());

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (currentScene == 3) {
            try {

                initializeStage.setScene(SceneThree.sceneThree());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (currentScene > 3 || currentScene < 1) {
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
        xFiles = 0;
        if (fileFolder.listFiles() == null) {
            System.out.println("The folder selected does not exist!");
        } else {
            do {
                System.out.println(xFiles + " (Real Value: " + (xFiles + 1) + ")");
                System.out.println(listFilenames[xFiles]);
                new DisplayMP3().dSong(listFilenames[xFiles], 330);
                System.out.println();
                xFiles = xFiles + 1;
            } while (xFiles < fileFolder.listFiles().length);
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

        Button maxButton = new Button("◻");
        maxButton.getStyleClass().add("button_layout_5");
        maxButton.setMaxHeight(20);
        maxButton.setOnAction((ActionEvent ae) -> initializeStage.setMaximized(true));
        topBar.getChildren().add(maxButton);

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
        topBar.setMinHeight(30);
        topBar.setMaxHeight(30);
        return topBar;
    }
    public static void fileMP3() {
        if(fileFolder.isDirectory() == false){
            System.out.println("Something's gone wrong... Main.fileMP3");
            return;
            }
            //https://www.mkyong.com/java/how-to-find-files-with-certain-extension-only/
        }
    }
}