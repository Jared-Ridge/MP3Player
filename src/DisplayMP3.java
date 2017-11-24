import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

public class DisplayMP3 {
    public static HBox dSong(File songName, int songLength ){
        HBox songDisplay = new HBox();
        HBox songSort = new HBox();
        Button playSong = new Button("▶");
        songSort.getChildren().add(playSong);
        int songSeconds = songLength % 60;
        int songMinutes = (songLength - songSeconds)/60;
        Label displaySongLength = new Label("  |  Song Length: " + songMinutes + ":" + songSeconds);
        Label displaySongName = new Label(String.valueOf("  " + songName.getName()));
        songDisplay.getChildren().add(songSort);
        songDisplay.getChildren().add(displaySongName);
        songDisplay.getChildren().add(displaySongLength);
        System.out.println(String.valueOf(displaySongLength));
        System.out.println(displaySongName);
        songDisplay.setAlignment(Pos.CENTER_LEFT);
        songSort.setAlignment(Pos.CENTER_LEFT);
        songDisplay.getStyleClass().add("song_layout_1");
        return songDisplay;
    }
}
