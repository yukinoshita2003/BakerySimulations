package cakemaker.core.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChocolateCakeTest {

    /**
     * Test to see if the program can report the correct cake flavour
     */
    @Test
    void cakeFlavour() {
        ChocolateCake cake1 = new ChocolateCake("none",0);
        assertEquals(cake1.cakeFlavour(),"Chocolate");
    }

    /**
     * Test to see if the program can report the correct cake price
     */
    @Test
    void cakePrice() {
        ChocolateCake cake1 = new ChocolateCake("none",0);
        assertEquals(cake1.cakePrice(),15);

    }

    /**
     * Test to see if the program can calculate the correct total price
     */
    @Test
    void totalPrice() {
        ChocolateCake cake1 = new ChocolateCake("none",11);
        assertEquals(cake1.totalPrice(),26);

    }

    /**
     * Test to see if the program can update the total price correctly
     */
    @Test
    void updateTotalPrice() {
        ChocolateCake cake1 = new ChocolateCake("none",0);
        cake1.updateTotalPrice(1);
        assertEquals(cake1.totalPrice(),16);

    }
}