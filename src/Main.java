import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import java.io.*;

// https://stevebirtles.github.io/JavaFXPickNMix/


public class Main extends Application {

    private BorderPane root = new BorderPane();
    private VBox rightPane = new VBox(-1);
    private Label displayFolder = new Label("No Folder Selected!");
    private File fileFolder = new File("MusicTest");
    private File folderLocation = new File("data/folderPath.txt");

    @Override
    public void start(Stage primaryStage) throws Exception {

        BufferedReader readFile = new BufferedReader(new FileReader(folderLocation));
        System.out.println(readFile.readLine());
        displayFolder.setText(readFile.readLine());

        primaryStage.setTitle("PlayMP3"); // Name of application to display at top of window
        primaryStage.setScene(new Scene(root, 1053, 480)); //Sets dimensions of stage
        primaryStage.setResizable(false); //Sets the stage to be non-resizable.
        primaryStage.show(); //Shows the stage
        root.getStylesheets().add("stylesheet.css"); // Opens CSS file

        VBox leftPane = new VBox(20);
        Button leftButton1 = new Button("I am left.");
        leftPane.getChildren().add(leftButton1);
        Button leftButton2 = new Button("I am left again.");
        leftPane.getChildren().add(leftButton2);
        root.setLeft(leftPane);
        leftPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(leftPane, Pos.CENTER_LEFT);
        leftButton1.getStyleClass().add("button_layout_1");
        leftButton2.getStyleClass().add("button_layout_2");
        leftPane.getStyleClass().add("stage_background_2"); //Sets background colour
        leftPane.setPrefSize(213, 480);


        Button folderButton = new Button("Select Folder");
        folderButton.setOnAction((ActionEvent ae) -> selectFolder(ae)); //Activates SelectFolder function
        rightPane.getChildren().add(folderButton);
        folderButton.setPrefWidth(413);
        Label lineSpacing_1 = new Label("If you can see this, something's gone wrong...");
        rightPane.getChildren().add(displayFolder);
        displayFolder.setPrefWidth(413);
        rightPane.getChildren().add(lineSpacing_1);
        displayFolder.getStyleClass().add("label_layout_1");
        root.setRight(rightPane);
        rightPane.setAlignment(Pos.TOP_CENTER);
        BorderPane.setAlignment(rightPane, Pos.CENTER_RIGHT);
        folderButton.getStyleClass().add("button_layout_1");
        lineSpacing_1.getStyleClass().add("spacing_label");
        rightPane.getStyleClass().add("stage_background_2"); //Sets background colour
        rightPane.setPrefSize(313, 480);


        VBox centerPane = new VBox(20);
        Button centerButton1 = new Button("I am centre.");
        centerPane.getChildren().add(centerButton1);
        Button centerButton2 = new Button("I am centre again.");
        centerPane.getChildren().add(centerButton2);
        root.setCenter(centerPane);
        centerPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(centerPane, Pos.CENTER);
        centerButton1.getStyleClass().add("button_layout_3");
        centerButton2.getStyleClass().add("button_layout_3");
        centerPane.getStyleClass().add("stage_background_1"); //Sets background colour
        listFiles();

    }


    private void selectFolder(ActionEvent ae) {
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
            listFiles();
        }
    }

    private void listFiles() {
        int x = 0;
        fileFolder.listFiles();
        for(x = 0; x < fileFolder.listFiles().length - 1; x++); {
            System.out.println();
        }
    }
}