package g58399.ATLIR4.controller;

import g58399.ATLIR4.model.Game;
import g58399.ATLIR4.model.GameState;
import g58399.ATLIR4.model.Position;
import g58399.ATLIR4.view.javaFX.sceneGame.GamePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.Objects;

public class ControllerJavaFX {
    private Game model;
    private GamePane view;
    private String mode;

    public ControllerJavaFX(Game model, GamePane view, String mode) {
        Objects.requireNonNull(model, "model Null");
        Objects.requireNonNull(view, "view Null");
        this.model = model;
        this.view = view;
        this.mode = mode;
    }
    public void initialize(){
        view.initialize(this);
        model.addObserver(view);
    }
    public void play(){
        this.model.start();
        this.view.getPlayerBlackInfo().setStatusValue("Play");
        this.view.getPlayerWhiteInfo().setStatusValue("Wait");
        this.view.drawPieces(this.model.getBoard(), this.model);
        songPlay();
    }
    public void songPlay(){
        // Chemin du fichier MP3
        String musicFile = "src/main/resources/song/songGameEllie.mp3"; // Remplacez par le chemin de votre fichier MP3
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        // Réglage pour lire en boucle
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    public void updateMove(Position positionSelected, String clickName){
        updateCurrentPlayer();
        switch (mode) {
            case "human vs human" -> {
                if(clickName.equals("gauche")){
                    this.model.setMovePawn(positionSelected);
                }else {
                    this.model.addMur(positionSelected);
                }
            }
            case "human vs easy computer" -> {
                if(clickName.equals("gauche")){
                    this.model.setMovePawn(positionSelected);
                }else {
                    this.model.addMur(positionSelected);
                }
                    //this.model.setCurrentPlayer();
                    this.model.IASimpleAction();
                    //this.model.setCurrentPlayer();
            }
            case "human vs hard computer" -> {
                if(this.model.isValidMove(positionSelected)){
                    if(clickName.equals("gauche")){
                        this.model.setMovePawn(positionSelected);
                    }else {
                        this.model.addMur(positionSelected);
                    }
                    this.model.setCurrentPlayer();
                    this.model.IAHardAction();
                    this.model.setCurrentPlayer();
                }else{
                    this.view.displayError("Impossible Movement !");
                }
            }
            default -> System.out.println("Invalid mode.");
        }
    }
    public void updateCurrentPlayer() {
        if(this.model.noMovePlayer()){
            this.model.setCurrentPlayer();
        }
    }
    public void updateGameState(){
        if (this.model.isGameOver()) {
            this.model.setState(GameState.GameOver);
        }
    }
}
