package cakemaker.core;

import cakemaker.core.data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A window that allows the user to modify an existing order.
 */
public class modifyCakeOrderController {
    @FXML
    private TextField orderNumber;

    @FXML
    private TextField cakeDateOrdered;

    @FXML
    private TextField cakeDateFinished;

    @FXML
    private CheckBox cakeFinishedStatus;

    @FXML
    private Button modifyButton;

    @FXML
    private TextField cakeExtraPrice;

    @FXML
    private ChoiceBox<String> cakeOrder;

    @FXML
    private TextField cakeOrderCustomerName;

    @FXML
    private Label errorLabel;

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
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Sets the new order details put in by the user
     * @param order the cake order for details to be saved to
     * @param customer string used to store the customer's name
     * @param dateOrdered date used to store when the cake was ordered
     * @param dateFinished date used to store when the cake needs to be finished
     * @param cakeFlavour string used to store what type of cake was ordered
     * @param specialRequest string used to store any special requests for the cake
     * @param extraPrice doubled used to store the additional price added based off of the special request
     * @param finishedStatus boolean whether the order is finished or not
     * @return boolean whether the details were set or not
     */
    private boolean setOrderDetails (CakeOrder order, String customer, Date dateOrdered, Date dateFinished, String cakeFlavour, String specialRequest, double extraPrice, boolean finishedStatus){
        // Do a check to make sure that the cake given is valid before adding anything
        Cake newCake;
        switch (cakeFlavour) {
            case "Vanilla" -> {
                newCake = new VanillaCake(specialRequest, extraPrice);
            }
            case "Chocolate" -> {
                newCake = new ChocolateCake(specialRequest, extraPrice);
            }
            case "Red Velvet" -> {
                newCake = new RedVelvetCake(specialRequest, extraPrice);
            }
            case "Cheesecake" -> {
                newCake = new CheesecakeCake(specialRequest, extraPrice);

            }
            default -> {
                // If we got here, this is an invalid type of cake
                errorLabel.setText("Invalid cake type received!");
                return false;
            }
        }
        order.setCustomer(customer);
        order.setDateOrdered(dateOrdered);
        order.setDateFinish(dateFinished);
        order.setCake(newCake);
        order.setOrderFinished(finishedStatus);
        return true;
    }

    /**
     * Loads order details from a given order number
     * @param event the event that triggers this function
     */
    @FXML
    void loadOrderDetails(ActionEvent event) {
            try{
                int orderID = Integer.parseInt(orderNumber.getText());
                // Make sure that the order number entered exists
                if (!data.getCakeOrderID().containsKey(orderID)) {
                    errorLabel.setText("This order number does not exist! Type in a valid order number.");

                    cakeOrderCustomerName.setEditable(false);
                    cakeDateOrdered.setEditable(false);
                    cakeDateFinished.setEditable(false);
                    cakeOrder.setDisable(true);
                    specialRequest.setEditable(false);
                    cakeExtraPrice.setEditable(false);
                    modifyButton.setDisable(true);
                    cakeFinishedStatus.setDisable(true);

                }else {
                    // Get the order and the cake associated with the order
                    CakeOrder order = data.getCakeOrderID().get(orderID);
                    Cake cake = order.getCake();

                    // Start filling in the textFields with the information given
                    cakeOrderCustomerName.setText(order.getCustomer());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateOrdered = order.getDateOrdered();
                    cakeDateOrdered.setText(dateFormat.format(dateOrdered));

                    Date dateFinished = order.getDateFinish();
                    cakeDateFinished.setText(dateFormat.format(dateFinished));

                    String cakeFlavour = cake.cakeFlavour();

                    switch (cakeFlavour) {
                        case "Vanilla" -> {
                            cakeOrder.setValue("Vanilla - $10");
                        }
                        case "Chocolate" -> {
                            cakeOrder.setValue("Chocolate - $15");
                        }
                        case "Red Velvet" -> {
                            cakeOrder.setValue("Red Velvet - $20");
                        }
                        case "Cheesecake" -> {
                            cakeOrder.setValue("Cheesecake - $25");

                        }
                        default -> {
                            // If we got here, this is an invalid type of cake
                            cakeOrder.setValue("Invalid - change!");
                        }
                    }
                    specialRequest.setText(cake.getSpecialRequest());
                    cakeExtraPrice.setText(String.valueOf(cake.getExtraPrice()));
                    cakeFinishedStatus.setSelected(order.getFinishedStatus());
                    errorLabel.setText("");

                    // Set the rest of the input fields to be editable/enabled
                    cakeOrderCustomerName.setEditable(true);
                    cakeDateOrdered.setEditable(true);
                    cakeDateFinished.setEditable(true);
                    cakeOrder.setDisable(false);
                    specialRequest.setEditable(true);
                    cakeExtraPrice.setEditable(true);
                    modifyButton.setDisable(false);
                    cakeFinishedStatus.setDisable(false);

                }
            } catch (NumberFormatException e){
                errorLabel.setText("This is an invalid input, enter a valid order number");
            }
    }

    /**
     * Verify the data input and store the new order details
     * @param event the event that triggers this function
     */
    @FXML
    void modifyOrder(ActionEvent event) {
        errorLabel.setText("");

        CakeOrder order = data.getCakeOrderID().get(Integer.parseInt(orderNumber.getText()));
        Cake cake = order.getCake();

        boolean validName;
        String CustomerName = cakeOrderCustomerName.getText().trim();
        // If the prompt is empty, get the user to input a name
        if(CustomerName.isEmpty()){
            errorLabel.setText("Enter a name for the customer.");
            validName = false;
        }else {
            validName = true;

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
                    errorLabel.setText("The year should be in between 2024 and 2099.");
                }

            }catch (ParseException | NullPointerException e){
                errorLabel.setText("The order date entered is not valid. Please use dd/MM/yyyy.");

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
                    errorLabel.setText("Choose a date that a date after the date you ordered it.");

                    if (year >=2024 && year <=2099){
                        isValid1 = true;
                    } else {
                        errorLabel.setText("The year should be in between 2023 and 2099");
                    }
                }

            }catch (ParseException | NullPointerException e){
                errorLabel.setText("The finish date entered is not valid. Please use dd/MM/yyyy.");
            }

            String cakeOrdered = cake.cakeFlavour();

            String special_requests= specialRequest.getText();

            boolean validExtraPrice = false;
            double extraPrice=0;

            // Make sure that the extra price given is a valid double
            try{
                extraPrice = Double.parseDouble(cakeExtraPrice.getText().trim());
                if (extraPrice < 0.0 || extraPrice > 99.99) {
                    errorLabel.setText("Insert a price between $0 - $99.99");
                } else {
                    validExtraPrice = true;
                }

            }catch (NumberFormatException e){
                errorLabel.setText("Failed to parse a double for the extra price!");
            }

            boolean cakeFinished = cakeFinishedStatus.isSelected();

            if (validName && isValid1 && isValid && validExtraPrice ){
                boolean success = setOrderDetails(order, CustomerName, dateOrdered, dateFinish, cakeOrdered, special_requests, extraPrice, cakeFinished);
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
}

