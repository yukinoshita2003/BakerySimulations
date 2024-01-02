package cakemaker.core.data;

import java.util.Date;

/**
 * A cake order consists of a person's name, an order number, the cake itself, and whether the cake is done or not
 */
public class CakeOrder implements Comparable<CakeOrder> {
    /**
     * The order number as an integer which is used to identify each order
     */
    private final int orderNumber;

    /**
     * The name of the customer
     */
    private String customer;

    /**
     * The date the order was placed
     */
    private Date dateOrdered;

    /**
     * The date the order is to be finished by
     */
    private Date dateFinish;

    /**
     * The cake that needs to be made for this order
     */
    private Cake cake;

    /**
     * Whether the order is finished or not (boolean)
     */
    private boolean orderFinished;

    /**
     * The structure of how the cake order is stored
     * @param orderNumber integer of the number given
     * @param customer String of the customer's name
     * @param dateOrdered Date of the day it was placed
     * @param dateFinish Date of the day it needs to be done by
     * @param cake The Cake that needs to be made
     */
    public CakeOrder(int orderNumber, String customer, Date dateOrdered, Date dateFinish, Cake cake){
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.dateOrdered = dateOrdered;
        this.dateFinish = dateFinish;
        this.cake = cake;
        this.orderFinished = false;
    }

    /**
     * Get the order number of the order
     * @return integer order number
     */
    public int getOrderNumber(){
        return this.orderNumber;
    }

    /**
     * Get the name of the customer
     * @return String customer name
     */
    public String getCustomer(){
        return this.customer;
    }

    /**
     * Set the customer for the order
     * @param customer String customer name
     */
    public void setCustomer(String customer){
        this.customer = customer;
    }

    /**
     * Get the date the order was placed on
     * @return Date the date ordered
     */
    public Date getDateOrdered(){
        return this.dateOrdered;
    }

    /**
     * Set the date the order was placed on
     * @param date the date it was ordered on
     */
    public void setDateOrdered(Date date){
        this.dateOrdered = date;
    }

    /**
     * Get the date the order needs to be finished by
     * @return Date the finish date
     */
    public Date getDateFinish(){
        return this.dateFinish;
    }

    /**
     * Set the date the order needs to be finished by
     * @param date the finish date of the order
     */
    public void setDateFinish(Date date){
        this.dateFinish = date;
    }

    /**
     * Get the cake that is required for this order
     * @return Cake the cake
     */
    public Cake getCake(){
        return this.cake;
    }

    /**
     * Set the cake that is required for this order
     * @param cake the cake for this order
     */
    public void setCake(Cake cake){
        this.cake = cake;
    }

    /**
     * Get the status of the order
     * @return boolean Whether the order is finished or not
     */
    public boolean getFinishedStatus(){
        return this.orderFinished;
    }

    /**
     * Set the status of the order
     * @param finished boolean Whether the order is finished or not
     */
    public void setOrderFinished(boolean finished){
        this.orderFinished = finished;
    }

    /**
     * Allows cake orders to be sorted using Collections.sort
     * @param other the object to be compared.
     * @return integer result of Integer.compare
     */
    @Override
    public int compareTo(CakeOrder other) {
        return Integer.compare(this.orderNumber, other.orderNumber);
    }

    /**
     * Prints the order in an organized manner
     * @return
     */
    @Override
    public String toString() {
        return "Order for order number " + orderNumber + "\n" +
                "\tCustomer name: " + customer + "\n" +
                "\tDate ordered: " + dateFinish + "\n" +
                "\tDate needed by: " + dateOrdered + "\n" +
                "\tType of cake: " + cake.cakeFlavour() + "\n" +
                "\tBase cake price: " + cake.cakePrice() + "\n" +
                "\tSpecial Requests: " + cake.getSpecialRequest() + "\n" +
                "\tExtra added price: $" + cake.getExtraPrice() + "\n" +
                "\tTotal price: $" + cake.totalPrice() + "\n" +
                "\tCake is finished: " + orderFinished + "\n";
    }
}
