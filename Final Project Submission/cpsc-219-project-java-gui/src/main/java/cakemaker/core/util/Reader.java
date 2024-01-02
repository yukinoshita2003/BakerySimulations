package cakemaker.core.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import cakemaker.core.data.*;


/**
 * Class to output cake order data to a file
 */
public class Reader {

    /**
     * Orders have a structure that it follows to ensure that data is written and read the same
     */
    private enum orderStructure {
        ORDER_NUMBER, CUSTOMER, DATE_ORDERED, DATE_FINISH, CAKE_ORDER, SPECIAL_REQUEST, EXTRA_PRICE, ORDER_FINISHED
    }

    /**
     * Loads orders from a given file into a set of data
     * @param filename The file to load from
     * @param data The data for information to be put into
     * @return The dataset that contains the information from the file
     */
    public static Data loadFile(File filename, Data data){

        File file = new File(String.valueOf(filename));

        if (file.exists() && file.isFile() && file.canRead()){
            try {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()){
                    // Split the line by commas to get each part of the order
                    String currentLine = scanner.nextLine();
                    String[] lineInformation = currentLine.split(",");

                    // Load the different parts of the cake order
                    int orderNumber = Integer.parseInt(lineInformation[orderStructure.ORDER_NUMBER.ordinal()]);
                    // Make sure that the order ID doesn't already exist in the order list
                    if (!data.getCakeOrderID().containsKey(orderNumber)) {
                        String customer = lineInformation[orderStructure.CUSTOMER.ordinal()];

                        // Parse the dates in the same pattern they should be written in
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        Date dateOrdered = dateFormat.parse(lineInformation[orderStructure.DATE_ORDERED.ordinal()]);
                        Date dateFinish = dateFormat.parse(lineInformation[orderStructure.DATE_FINISH.ordinal()]);

                        String cakeOrder = lineInformation[orderStructure.CAKE_ORDER.ordinal()];

                        String specialRequest = lineInformation[orderStructure.SPECIAL_REQUEST.ordinal()];
                        double extraPrice = Double.parseDouble(lineInformation[orderStructure.EXTRA_PRICE.ordinal()]);


                        boolean orderFinished = Boolean.parseBoolean(lineInformation[orderStructure.ORDER_FINISHED.ordinal()]);

                        // Attempt to store the data that was loaded
                        boolean success = data.storeCustomerCakeOrderInformation(orderNumber, customer, dateOrdered, dateFinish, cakeOrder, specialRequest, extraPrice);

                        // If it was successfully added, add the rest of the cake order
                        if (success) {
                            CakeOrder order = data.getCakeOrderID().get(orderNumber);
                            order.setOrderFinished(orderFinished);
                        } else {
                            return null;
                        }
                    }
                }
                scanner.close();
                return data;
            } catch (FileNotFoundException | ParseException e) {
                return null;
            }
        } else{
            return null;
        }
    }
}
