package cakemaker.core.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cakemaker.core.data.*;


/**
 * Class to output cake order data to a file
 */
public class Writer {

    /**
     * Takes the data of orders and outputs them to a .csv file
     * @param filename the name of the file to output to
     * @param data the data that contains all the orders to be saved
     * @return boolean true or false on whether the file was successfully saved or not
     */
    public static boolean writeToFile(File filename, Data data){

        File file = new File(String.valueOf(filename));
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }
        if (file.exists() && file.isFile() && file.canWrite()) {
            try {
                FileWriter file_writer = new FileWriter(file);
                BufferedWriter buffered_writer = new BufferedWriter(file_writer);

                // Write each part of the cake order, separated by a comma
                for ( CakeOrder order: data.getListOfCakeOrders()) {
                    buffered_writer.write(order.getOrderNumber()+ ",");
                    buffered_writer.write(order.getCustomer()+ ",");

                    // Make sure that each date is formatted correctly when writing them
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String writeDateOrdered = dateFormat.format(order.getDateOrdered());
                    buffered_writer.write(writeDateOrdered+ ",");

                    String writeDateFinish = dateFormat.format(order.getDateFinish());
                    buffered_writer.write(writeDateFinish+ ",");

                    buffered_writer.write(order.getCake().cakeFlavour()+ ",");
                    buffered_writer.write(order.getCake().getSpecialRequest()+ ",");
                    buffered_writer.write(order.getCake().getExtraPrice()+ ",");
                    buffered_writer.write(order.getFinishedStatus()  + "\n");
                }
                buffered_writer.flush();

                return true;

            } catch (IOException e) {
                return false;
            }
        } else {
            return false;
        }
    }

}
