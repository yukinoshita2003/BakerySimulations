package cakemaker.core;

import cakemaker.core.data.Data;
import cakemaker.core.util.Reader;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Cake Order Tracker
 * A simple program that allows orders for cakes to be input, stored, and modified.
 *
 * @author Nathan Caguiat, Hanz Dela Cruz
 * @version 1.42
 */
public class Main extends Application {

    private static final Data data = new Data();

    private static String[] file_args;


    @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cakemaker.fxml"));

        // Check if the user gave a file in the command line arguments before showing the scene
        if (file_args.length == 1) {
            File fileLoad = new File(file_args[0]);
            Data newData = Reader.loadFile(fileLoad, data);

            if (newData != null){
                CakeController.setData(newData);
                loadSuccess();
            } else {
                loadFailure();
            }
        }

            Scene scene = new Scene(fxmlLoader.load(), 926, 416);
            stage.setTitle("Cake Menu System");
            stage.setScene(scene);
            stage.show();
        }

    /**
     * Alert to tell the user that the file was loaded successfully
     */
    @FXML
    void loadSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("File Loader");
        alert.setHeaderText("Load success!");
        alert.setContentText("The file <" + file_args[0] + "> was able to be loaded!");
        alert.showAndWait();
    }

    /**
     * Alert to tell the user that the file was not loaded
     */
    @FXML
    void loadFailure() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File Loader");
        alert.setHeaderText("Load failure!");
        alert.setContentText("The file <" + file_args[0] + "> was not able to be loaded.");
        alert.showAndWait();
    }

        public static void main(String[] args) {
            file_args = args;
            launch();
        }
    }