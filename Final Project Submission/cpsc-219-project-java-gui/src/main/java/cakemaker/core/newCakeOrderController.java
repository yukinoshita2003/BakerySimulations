package cakemaker.core;

import cakemaker.core.data.CakeOrder;
import cakemaker.core.data.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * A window that allows the user to add a new cake order.
 */
public class newCakeOrderController {

    @FXML
    private TextField cakeDateFinished;

    @FXML
    private TextField cakeDateOrdered;

    @FXML
    private TextField cakeExtraPrice;


    @FXML
    private ChoiceBox<String> cakeOrder;

    @FXML
    private TextField cakeOrderCustomerName;

    @FXML
    private TextField cakeOrderNumber;

    @FXML
    private Label errorLabel;

    @FXML
    private Label specificErrorLabel;

    @FXML
    private TextArea specialRequest;

    /**
     * Data that contains the orders stored
     */
    private Data data;

    /**
     * Set the data that is being used
     * @param data data that contains the order information
     */
    public void setData(Data data){
        this.data = data;
    }

    /**
     * Verify the data input by the user and store it
     * @param event the event that triggers this function
     */
    @FXML
    void SubmitNewOrder(ActionEvent event) {

        errorLabel.setText("");

        boolean validName;
        String CustomerName = cakeOrderCustomerName.getText();
        // If the prompt is empty, get the user to input a name
        if(CustomerName.isEmpty()){
            specificErrorLabel.setText("Enter a name for the customer.");
            validName = false;
        }else {
            validName = true;
        }

        HashMap<Integer, CakeOrder> orderList = data.getCakeOrderID();

        boolean validOrderNumber = false;
        int orderNumber = 0;
        try {
            orderNumber = Integer.parseInt(cakeOrderNumber.getText());

            // If the number is valid, then add order will go onto the next process
            if (!orderList.containsKey(orderNumber) && orderNumber >= 0){
                validOrderNumber = true;

            }
            else{
                specificErrorLabel.setText("This number has been used before \n or is not valid; choose another integer.");
            }

        }  catch (NumberFormatException | NullPointerException e ){
            specificErrorLabel.setText("Enter a valid integer for the order number.");

        }


        // This section of code about getting dates from the user was partially taken from ChatGPT.
        // The professor said that it was okay to use this code.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        boolean isValid = false;
        String orderDate =  cakeDateOrdered.getText();
        Date dateOrdered = null;

        // Get the date the order was placed
        try{

            dateOrdered = dateFormat.parse(orderDate);
            Calendar calendar =Calendar.getInstance();
            calendar.setTime(dateOrdered);

            int year = calendar.get(Calendar.YEAR);
            if (year >= 2024 && year <= 2099) {
                isValid = true;

            } else {
                specificErrorLabel.setText("The year should be in between 2024 and 2099.");
            }

        }catch (ParseException | NullPointerException e){
            specificErrorLabel.setText("The order date entered is not valid. Please use dd/MM/yyyy.");

        }

        boolean isValid1 = false;
        Date dateFinish =null;
        // Get the date the order needs to be finished by
        try{
            String finishDate = cakeDateFinished.getText();
            dateFinish = dateFormat.parse(finishDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFinish);
            int year = calendar.get(Calendar.YEAR);

            if(dateFinish.before(dateOrdered)){
                specificErrorLabel.setText("Choose a date that a date after the date you ordered it.");

                if (year >=2024 && year <=2099){
                    isValid1 = true;
                } else {
                    specificErrorLabel.setText("The year should be in between 2023 and 2099");
                }
            }

        }catch (ParseException | NullPointerException e){
            specificErrorLabel.setText("The finish date entered is not valid. Please use dd/MM/yyyy.");
        }

        String cakeOrdered = cakeOrder.getValue();

        String special_requests= specialRequest.getText();

        boolean validExtraPrice = false;
        double extraPrice=0;

        // Make sure that the extra price given is a valid double
        try{
            extraPrice = Double.parseDouble(cakeExtraPrice.getText().trim());
            if (extraPrice < 0.0 || extraPrice > 99.99) {
                specificErrorLabel.setText("Insert a price between $0 - $99.99");
            } else {
                validExtraPrice = true;
            }

        }catch (NumberFormatException e){
            specificErrorLabel.setText("Failed to parse a double for the extra price!");
        }

        if (validName && validOrderNumber && isValid1 && isValid && validExtraPrice ){
            boolean success = data.storeCustomerCakeOrderInformation(orderNumber,CustomerName,dateOrdered,dateFinish,cakeOrdered,special_requests,extraPrice);
            if (success){
                ((Stage)cakeOrderCustomerName.getScene().getWindow()).close();
            } else {
                // If we got here, something went wrong with saving the order
                errorLabel.setText("Unable to save the cake order!");
            }
        } else {
            // If we got here, there is an issue with some part of the order input
            errorLabel.setText("There is something wrong with the entries for this order.");
        }
    }
}
