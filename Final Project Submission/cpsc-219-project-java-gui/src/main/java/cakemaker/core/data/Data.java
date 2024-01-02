package cakemaker.core.data;

import java.util.*;

/**
 * Class where cake orders are processed and stored in
 */
public class Data {

    /**
     * An ArrayList for storing the information about the cake order
     */
    private final ArrayList<CakeOrder> listOfCakeOrders;

    /**
     * A hashmap for easy lookup of specific orders
     */
    private final HashMap<Integer, CakeOrder> cakeOrderID ;

    /**
     * Get the HashMap that contains both order numbers and the order itself
     * @return the HashMap of orders
     */
    public HashMap<Integer,CakeOrder> getCakeOrderID(){
        return this.cakeOrderID;

    }

    /**
     * Get the ArrayList of orders
     * @return ArrayList of orders
     */
    public ArrayList<CakeOrder> getListOfCakeOrders(){
        // Make sure that orders are always sorted before returning the list
        Collections.sort(listOfCakeOrders);
        return listOfCakeOrders;

    }

    /**
     * Data stores the list of cake orders in an array list and a list of cake order IDs in a HashMap for easier searching
     */
    public Data(){
        this.listOfCakeOrders = new ArrayList<>();
        this.cakeOrderID = new HashMap<>();

    }


    /**
     * Creates an array to store cake order information in to add to the cake order list HashMap.
     * @param orderNumber integer number used to identify the cake order
     * @param customer string used to store the customer's name
     * @param dateOrdered date used to store when the cake was ordered
     * @param dateFinish date used to store when the cake needs to be finished
     * @param cakeOrder string used to store what type of cake was ordered
     * @param specialRequest string used to store any special requests for the cake
     * @param extraPrice doubled used to store the additional price added based off of the special request
     */
    public boolean storeCustomerCakeOrderInformation(int orderNumber, String customer, Date dateOrdered, Date dateFinish, String cakeOrder, String specialRequest, double extraPrice) {
        // Add a different type of cake depending on the order given
        switch (cakeOrder) {
            case "Vanilla - $10", "Vanilla" -> {
                Cake cake = new VanillaCake(specialRequest, extraPrice);
                CakeOrder order = new CakeOrder(orderNumber, customer, dateOrdered, dateFinish, cake);

                listOfCakeOrders.add(order);
                cakeOrderID.put(orderNumber, order);

                // Verify that the order was properly added
                return listOfCakeOrders.contains(order);
            }
            case "Chocolate - $15", "Chocolate" -> {
                Cake cake = new ChocolateCake(specialRequest, extraPrice);
                CakeOrder order = new CakeOrder(orderNumber, customer, dateOrdered, dateFinish, cake);

                listOfCakeOrders.add(order);
                cakeOrderID.put(orderNumber, order);

                // Verify that the order was properly added
                return listOfCakeOrders.contains(order);
            }
            case "Red Velvet - $20", "Red Velvet" -> {
                Cake cake = new RedVelvetCake(specialRequest, extraPrice);
                CakeOrder order = new CakeOrder(orderNumber, customer, dateOrdered, dateFinish, cake);

                listOfCakeOrders.add(order);
                cakeOrderID.put(orderNumber, order);

                // Verify that the order was properly added
                return listOfCakeOrders.contains(order);
            }
            case "Cheesecake - $25", "Cheesecake" -> {
                Cake cake = new CheesecakeCake(specialRequest, extraPrice);
                CakeOrder order = new CakeOrder(orderNumber, customer, dateOrdered, dateFinish, cake);

                listOfCakeOrders.add(order);
                cakeOrderID.put(orderNumber, order);

                // Verify that the order was properly added
                return listOfCakeOrders.contains(order);

            } default -> {
                // If we got here, this is an invalid type of cake
                return false;
            }
        }
    }

    /**
     * Looks through a specific index of all the stored orders to find what occurs the most frequently.
     * @param  comparisonList a list of all the items to be compared with
     * @return Object array that contains the most frequently occurring item, and how many times it shows up.
     */
    public Object[] calculateMaximumOccurrence(ArrayList<String> comparisonList) {
        // Create a new HashMap to store how many times an item appears in the list
        HashMap<Object, Integer> occurrenceList = new HashMap<>();

        for (String item : comparisonList) {
            // If this is the first time the item has appeared, add it to the occurrence list with a value of 1
            int occurrenceValue;
            if (!occurrenceList.containsKey(item)) {
                occurrenceValue = 1;
                // Otherwise, increment the occurrence counter.
            } else {
                occurrenceValue = occurrenceList.get(item);
                occurrenceValue++;
            }
            occurrenceList.put(item, occurrenceValue);
        }

        int mostFrequentValue = Collections.max(occurrenceList.values());

        // Create a list to store the keys with the highest occurrence in
        ArrayList<Object> mostFrequentKey = new ArrayList<>();

        // Loop through the occurrence list to get the keys that have the highest occurrence counter
        for (Object key : occurrenceList.keySet()) {
            int currentValue = occurrenceList.get(key);
            if (currentValue == mostFrequentValue){
                mostFrequentKey.add(key);
            }
        }
        // Add to an array to return the most frequently occurring key and value
        return new Object[]{mostFrequentKey, mostFrequentValue};
    }

}

