package cakemaker.core.data;

/**
 * A cake contains the flavour, the price, any special request, the price of the special request, and the total cost
 */
public abstract class Cake {
// Abstract because a cake needs a flavour and cannot exist on its own

    /**
     * Each cake has a special request that the customer adds to their order
     */
    private String specialRequest;
    /**
     * Each cake has an extra price that comes from the special request of the order
     */
    private final double extraPrice;

    /**
     * Each cake has a cake flavour
     * @return The cake flavour as a String
     */
    public abstract String cakeFlavour();

    /**
     * Each cake has a base price
     * @return double Price of the cake
     */
    public abstract double cakePrice();

    /**
     * Each cake has a total price of the cake price and extra price added together
     * @return double Total price
     */
    public abstract double totalPrice();

    /**
     * Updates the total price of the cake
     * @param extraPrice The price given from the special request
     */
    public abstract void updateTotalPrice(double extraPrice);

    /**
     * Get the special request of the order
     * @return String of the special request
     */
    public String getSpecialRequest(){
        return this.specialRequest;
    }

    /**
     * Get the extra price of the order
     * @return double Extra price
     */
    public double getExtraPrice(){
        return this.extraPrice;
    }

    /**
     * Set the special request of the order
     * @param specialRequest the String to be input as the request
     */
    public void setSpecialRequest(String specialRequest){
        this.specialRequest = specialRequest;
    }

    /**
     * This class stores the special request and the extra price input by the user
     * @param specialRequest the String to be input as the request
     * @param extraPrice double Extra price
     */
    protected Cake(String specialRequest, double extraPrice){
        this.specialRequest = specialRequest;
        this.extraPrice = extraPrice;
    }
}
