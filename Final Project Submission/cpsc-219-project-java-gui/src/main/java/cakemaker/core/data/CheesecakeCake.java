package cakemaker.core.data;

/**
 * A Cheesecake cake is a Cake with a flavour of "Cheesecake" and a base price of $25
 */
public class CheesecakeCake extends Cake {

    /**
     * The base price of this cake ($25)
     */
    private final double cakePrice = 25.00;

    /**
     * Each cake has a total price that is the cake price and the extra price given combined
     */
    private double totalPrice;

    /**
     * Each cake stores the special request and extra price given from the inherited class
     * @param specialRequest String special request of the cake
     * @param extraPrice double price given with the special request
     */
    public CheesecakeCake(String specialRequest, double extraPrice){
        super(specialRequest, extraPrice);
        this.totalPrice = calculateTotalPrice(extraPrice);
    }

    /**
     * Get the flavour of the cake
     * @return String flavour of cake
     */
    public String cakeFlavour(){
        return "Cheesecake";
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
     * @param extraPrice double The price given from the special request
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
