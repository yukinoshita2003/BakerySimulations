package cakemaker.core.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CheesecakeCakeTest {


    /**
     * Test to see if the program can report the correct cake flavour
     */
    @Test
    void cakeFlavour() {
        CheesecakeCake cake1 = new CheesecakeCake("none",0);
        assertEquals(cake1.cakeFlavour(),"Cheesecake");
    }

    /**
     * Test to see if the program can report the correct cake price
     */
    @Test
    void cakePrice() {
        CheesecakeCake cake1 = new CheesecakeCake("none",0);
        assertEquals(cake1.cakePrice(),25);
    }

    /**
     * Test to see if the program can calculate the correct total price
     */
    @Test
    void totalPrice() {
        CheesecakeCake cake1 = new CheesecakeCake("none",0);
        assertEquals(cake1.totalPrice(),25);
    }

    /**
     * Test to see if the program can update the total price correctly
     */
    @Test
    void updateTotalPrice() {
        CheesecakeCake cake1 = new CheesecakeCake("none",0);
        cake1.updateTotalPrice(1);
        assertEquals(cake1.totalPrice(),26);
    }
}