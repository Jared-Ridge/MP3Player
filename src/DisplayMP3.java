import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

public class DisplayMP3 {
    public static HBox dSong(File songName, int songLength ){
        HBox songDisplay = new HBox();
        VBox songSort = new VBox();
        Button playSong = new Button("▶");
        songDisplay.getChildren().add(playSong);
        int songSeconds = songLength % 60;
        int songMinutes = (songLength - songSeconds)/60;
        Label spacer = new Label("     ");
        Label displaySongLength = new Label(songMinutes + ":" + songSeconds);
        displaySongLength.getStyleClass().add("text_general_1");
        Label displaySongName = new Label(String.valueOf(songName.getName()));
        displaySongName.getStyleClass().add("text_general_1");
        songDisplay.getStyleClass().add("song_layout_1");

        songDisplay.getChildren().add(songSort);
        songDisplay.getChildren().add(spacer);
        songSort.getChildren().add(displaySongName);
        songSort.getChildren().add(displaySongLength);

        System.out.println(String.valueOf(displaySongLength));
        System.out.println(displaySongName);
        songDisplay.setAlignment(Pos.CENTER_LEFT);
        songSort.setAlignment(Pos.CENTER_LEFT);
        songDisplay.getStyleClass().add("song_layout_1");
        songDisplay.setPrefWidth(480);
        return songDisplay;
    }
}
