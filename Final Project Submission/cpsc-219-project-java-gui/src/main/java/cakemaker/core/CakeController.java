package cakemaker.core;

import cakemaker.core.data.Cake;
import cakemaker.core.data.CakeOrder;
import cakemaker.core.data.Data;
import cakemaker.core.util.Reader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import cakemaker.core.util.*;

import java.io.File;
import java.util.ArrayList;


public class CakeController {

    private static final String emptyListMessage = "There are no orders in the list currently.";

    @FXML
    private TextArea displayScreen;

    @FXML
    private Label errorStatus;

    @FXML
    private TextField specificOrderNumber;

    private static Data data = new Data();


    @FXML
    public void initialize() {
        displayScreen.setEditable(false);
        displayScreen.setText("");
        errorStatus.setText("");
        ViewAllOrders(null);
    }

    /**
     * Function to display the total revenue and average price of each order in the GUI
     * @param event the event that triggers this function
     */
    @FXML
    void DisplayRevenueAndAveragePrice(ActionEvent event) {
        displayScreen.setEditable(false);
        displayScreen.setText("");
        errorStatus.setTextFill(Color.RED);
        errorStatus.setText("");

        String display_data = "";

        // Make sure the order list is not empty
        if (data.getListOfCakeOrders().isEmpty()) {
            display_data = emptyListMessage;
        } else {
            double sumOfPrice = 0;
            int numberOfOrders = 0;

            // Go through each order stored, and get the total price for each order
            for (CakeOrder order : data.getListOfCakeOrders()) {
                Cake cake = order.getCake();
                double totalPrice = cake.totalPrice();
                sumOfPrice += totalPrice;
                numberOfOrders++;
            }

            double averagePrice = sumOfPrice / numberOfOrders;
            display_data = "The store's total revenue is: $" + sumOfPrice + "\n"+
                    "The average price of all the cake orders is: $" + averagePrice ;
        }

        displayScreen.setText(display_data);

    }

    /**
     * Loads the window that allows users to modify an order
     * @param event the event that triggers this function
     */
    @FXML
    void ModifyOrder(ActionEvent event) {
        displayScreen.setText("");
        errorStatus.setTextFill(Color.RED);
        errorStatus.setText("");

        String label_data = "";

        // Print out the existing orders for the user to select from
        label_data = "Existing order numbers: ";

        for (CakeOrder order : data.getListOfCakeOrders()) {
            label_data += (order.getOrderNumber() + "\t");
        }

        displayScreen.setText(label_data);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("modifyCakeOrder.fxml"));
            Stage stage =new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 393, 611);
            stage.setTitle("Cake Menu System");
            stage.setScene(scene);
            ((modifyCakeOrderController)fxmlLoader.getController()).setData(data);
            stage.show();

        }catch (Exception e){
            errorStatus.setText("Window for modifying orders is not launching.");
        }


    }

    /**
     * Displays the cake flavour that has been ordered the most in the GUI
     * @param event the event that triggers this function
     */
    @FXML
    void MostPopularCake(ActionEvent event) {
        displayScreen.setEditable(false);
        displayScreen.setText("");
        errorStatus.setTextFill(Color.RED);
        errorStatus.setText("");

        // Make sure the order list is not empty
        if (data.getListOfCakeOrders().isEmpty()) {
            displayScreen.setText(emptyListMessage);
        } else {
            // Create a comparison list
            ArrayList<String> comparisonList = new ArrayList<>();
            ArrayList<CakeOrder> cakeOrders = data.getListOfCakeOrders();
            // For every cake order stored, grab the type of cake and add it to the comparison list
            for (CakeOrder order : cakeOrders){
                Cake cake = order.getCake();
                comparisonList.add(cake.cakeFlavour());
            }
            Object[] mostPopularCake = data.calculateMaximumOccurrence(comparisonList);
            int cake = 0;
            int numberOfOrders = 1;
            displayScreen.setText(mostPopularCake[cake] + " is the most popular cake with " + mostPopularCake[numberOfOrders] + " orders.");
        }
    }

    /**
     * Displays every order stored in the GUI
     * @param event the event that triggers this function
     */
    @FXML
    void ViewAllOrders(ActionEvent event) {
        displayScreen.setText("");
        errorStatus.setTextFill(Color.RED);
        errorStatus.setText("");
        displayScreen.setEditable(false);
        String label_data = "";

        // Make sure the order list is not empty
        if (data.getListOfCakeOrders().isEmpty()) {
            label_data = emptyListMessage;
            displayScreen.setText(label_data);

        } else {
            for (CakeOrder order: data.getListOfCakeOrders()) {
                // Fetch the current order using the order number in the ArrayList
                label_data += order.toString();
            }
            displayScreen.setText(label_data);

        }
    }

    /**
     * Displays the specific order from the order number given in the GUI
     * @param event the event that triggers this function
     */
    @FXML
    void ViewSpecificOrder(ActionEvent event) {

        displayScreen.setEditable(false);
        displayScreen.setText("");
        errorStatus.setTextFill(Color.RED);
        errorStatus.setText("");

        String label_data = "";

        // Make sure the order list is not empty
        if (data.getListOfCakeOrders().isEmpty()) {
            label_data = emptyListMessage;
            displayScreen.setText(label_data);

        } else {
            // Print out the existing orders for the user to select from
            label_data = "Existing order numbers: ";

            for (CakeOrder order : data.getListOfCakeOrders()) {
                label_data += (order.getOrderNumber() + "\t");
            }

            displayScreen.setText(label_data);

            try {

                int orderNumber = Integer.parseInt(specificOrderNumber.getText());
                // Make sure that the order number entered exists
                if (!data.getCakeOrderID().containsKey(orderNumber)) {
                    label_data = ("This order number does not exist! Type in a valid order number.");

                } else {
                    CakeOrder order = data.getCakeOrderID().get(orderNumber);
                    // Print out each stored part of the order
                    label_data = order.toString();
                }
                displayScreen.setText(label_data);


            } catch (NumberFormatException e) {
                errorStatus.setText("This is an invalid input, enter a valid order number");
            }
        }
    }

    /**
     * Displays the amount of finished and unfinished orders in the GUI
     * @param event the event that triggers this function
     */
    @FXML
    void displayFinished(ActionEvent event) {
        displayScreen.setText("");
        errorStatus.setTextFill(Color.RED);
        errorStatus.setText("");

        int totalOrders = data.getListOfCakeOrders().size();

        // Make sure the order list is not empty
        if (totalOrders == 0) {
            System.out.println(emptyListMessage);
        } else {
            int finishedOrders = 0;
            int unfinishedOrders = 0;
            // Check to see what state the order is, then increment the appropriate counter.
            for (CakeOrder order: data.getListOfCakeOrders()) {
                boolean isFinished = order.getFinishedStatus();

                if (isFinished) {
                    finishedOrders++;
                } else {
                    unfinishedOrders++;
                }
            }
            displayScreen.setText("Out of " + totalOrders + " orders:\n" + finishedOrders + " are finished, and " + unfinishedOrders + " are unfinished.");
        }

    }

    /**
     * Loads the window that allows users to add an order
     * @param event the event that triggers this function
     */
    @FXML
    void openOrderScreen(ActionEvent event) {
        displayScreen.setText("");
        errorStatus.setTextFill(Color.RED);
        errorStatus.setText("");

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newCakeOrder.fxml"));
            Stage stage =new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 393, 611);
            stage.setTitle("Cake Menu System");
            stage.setScene(scene);
            ((newCakeOrderController)fxmlLoader.getController()).setData(data);
            stage.show();

        }catch (Exception e){
            errorStatus.setText("Window for add person is not launching.");

        }

    }

    /**
     * Displays the date that has the most cake orders to be finished by
     * @param event the event that triggers this function
     */
    @FXML
    void withMostOrders(ActionEvent event) {
        displayScreen.setText("");
        errorStatus.setTextFill(Color.RED);
        errorStatus.setText("");

        // Make sure the order list is not empty
        if (data.getListOfCakeOrders().isEmpty()) {
            displayScreen.setText(emptyListMessage);
        } else {

            // Create a comparison list
            ArrayList<String> comparisonList = new ArrayList<>();
            ArrayList<CakeOrder> cakeOrders = data.getListOfCakeOrders();
            // For every cake order stored, grab the date it needs to be done by and add it to the list
            for (CakeOrder order : cakeOrders) {
                comparisonList.add(String.valueOf(order.getDateFinish()));
            }

            Object[] busiestDate = data.calculateMaximumOccurrence(comparisonList);
            int date = 0;
            int numberOfOrders = 1;
            displayScreen.setText(busiestDate[date] + " is the date with the most orders needed to be completed (" + busiestDate[numberOfOrders] + " orders).");
        }

    }

    /**
     * Closes the program
     * @param event the event that triggers this function
     */
    @FXML
    void quitApplication(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Allows the user to save orders to an external file
     * @param event the event that triggers this function
     */
    @FXML
    void saveOrders(ActionEvent event) {
        displayScreen.setEditable(false);
        displayScreen.setText("");
        errorStatus.setText("");

        // Get the file to be saved to
        FileChooser fc = new FileChooser();
        fc.setTitle("Save Orders");
        fc.setInitialDirectory(new File("."));
        fc.setInitialFileName("data.csv");
        File file = fc.showSaveDialog(new Stage());

        // Attempt to save the file
        boolean success = Writer.writeToFile(new File(String.valueOf(file)), data);

        if (success){
            errorStatus.setTextFill(Color.BLACK);
            errorStatus.setText(file.getName()+ " was saved successfully!");
        } else {
            errorStatus.setTextFill(Color.RED);
            errorStatus.setText(file.getName()+ " failed to save.");
        }

    }

    /**
     * Allows the user to load orders from an external file
     * @param event the event that triggers this function
     */
    @FXML
    void loadOrders(ActionEvent event) {
        displayScreen.setEditable(false);
        displayScreen.setText("");
        errorStatus.setText("");

        // Get the file to be loaded from
        FileChooser fc = new FileChooser();
        fc.setTitle("Open a Cake Order Database ");
        fc.setInitialDirectory(new File("."));
        fc.setInitialFileName("data.csv");
        File file = fc.showOpenDialog(new Stage());
        Data newData = Reader.loadFile(file, data);

        // Make sure that the data return isn't null (which means it failed)
        if (newData != null) {
            setData(newData);
            ViewAllOrders(null);
            errorStatus.setTextFill(Color.BLACK);
            errorStatus.setText("The file was loaded successfully!");
        } else {
            errorStatus.setTextFill(Color.RED);
            errorStatus.setText("The file failed to load.");
        }
    }

    /**
     * Displays a window that gives information about this program
     * @param event the event that triggers this function
     */
    @FXML
    void aboutApplication(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Cake Tracker");
        alert.setContentText("Author: Nathan Caguiat, Hanz Dela Cruz\nEmail: nathan.caguiat@ucalgary.ca, hanz.delacruz@ucalgary.ca\nThis is an order tracker for cake orders.");
        alert.showAndWait();
    }

    /**
     * Set data loaded into the data set that is used by the program itself
     * @param data data of the orders that have been loaded
     */
    protected static void setData(Data data){
        CakeController.data = data;
    }

}
