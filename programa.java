import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class BackgroundMusicWithThreads extends Application {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java BackgroundMusicWithThreads <ruta-del-archivo-de-musica>");
            return;
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Thread musicThread = new Thread(() -> playBackgroundMusic());
        musicThread.start();

        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("¡Hola, " + nombre + "!");

        try {
            musicThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void playBackgroundMusic() {
        String musicFile = getParameters().getRaw().get(0);
        Media media = new Media("file:///" + musicFile);
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}