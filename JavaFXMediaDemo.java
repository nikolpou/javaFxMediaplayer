/*
 * JavaFX mediasoitin joka toistaa paikallisen videotiedoston
 */
package javafxmediademo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Nikolai
 */
public class JavaFXMediaDemo extends Application {


    @Override
    public void start(Stage primaryStage) {
        Media media = new Media((getClass().getResource("sample.mp4").toExternalForm()));
        MediaPlayer mediaplayer = new MediaPlayer(media);
        MediaView mediaview = new MediaView(mediaplayer);
        
        /*
        * Play nappi ja pause nappi
        */
        Button playButton = new Button (">");
        playButton.setOnAction(e -> {
            if (playButton.getText().equals(">")) {
                mediaplayer.play();
                playButton.setText("||");
            }
            else {
                mediaplayer.pause();
                playButton.setText(">");
            }
        });
        /*
        * Alkuun kelaus nappi
        */
        Button alkuunBt = new Button("<<");
        alkuunBt.setOnAction(e -> {
            mediaplayer.seek(Duration.ZERO);;            
        });
        /*
        * Äänenvoimakkuus slider
        */
        Slider volumeSlider = new Slider();
        volumeSlider.setPrefWidth(150);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        volumeSlider.setValue(50);
        mediaplayer.volumeProperty().bind(volumeSlider.valueProperty().divide(100));
        /*
        * Näytetään videon toistettu aika ja kokonaisaika
        */
        TextField textfield = new TextField();
        mediaplayer.currentTimeProperty().addListener(observable -> {
            textfield.setText(
            mediaplayer.getCurrentTime()
            + " / "
            + mediaplayer.getTotalDuration()
            );
        });
        
  
        
        HBox boksi = new HBox(10);
        boksi.setAlignment(Pos.CENTER);
        boksi.getChildren().addAll(playButton,alkuunBt,volumeSlider,textfield);
        BorderPane paneeli = new BorderPane();
        paneeli.setCenter(mediaview);
        paneeli.setBottom(boksi);
               
        Scene scene = new Scene(paneeli, 650, 500);
        
        primaryStage.setTitle("Media Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
