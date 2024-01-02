package cakemaker.core.data;

/**
 * A Red Velvet cake is a Cake with a flavour of "Red Velvet" and a base price of $20
 */
public class RedVelvetCake extends Cake {

    /**
     * The base price of this cake ($20)
     */
    private final double cakePrice = 20.00;

    /**
     * Each cake has a total price that is the cake price and the extra price given combined
     */
    private double totalPrice;

    /**
     * Each cake stores the special request and extra price given from the inherited class
     * @param specialRequest String special request of the cake
     * @param extraPrice double price given with the special request
     */
    public RedVelvetCake(String specialRequest, double extraPrice){
        super(specialRequest, extraPrice);
        this.totalPrice = calculateTotalPrice(extraPrice);
    }

    /**
     * Get the flavour of the cake
     * @return String flavour of cake
     */
    public String cakeFlavour(){
        return "Red Velvet";
    }

    /**
     * Get the base price of the cake
     * @return double price of the cake
     */
    public double cakePrice(){
        return cakePrice;
    }

    /**
     * Get the total price of the cake
     * @return double total price of the cake
     */
    public double totalPrice(){
        return this.totalPrice;
    }

    /**
     * Update the total price of the cake, called when a new extra price is given
     * @param extraPrice The price given from the special request
     */
    public void updateTotalPrice(double extraPrice){
        this.totalPrice = cakePrice + extraPrice;
    }

    /**
     * Calculate the total price of the cake
     * @param extraPrice double extra price of the cake
     * @return double calculated total price of the cake
     */
    private double calculateTotalPrice(double extraPrice){
        return cakePrice + extraPrice;
    }
}
