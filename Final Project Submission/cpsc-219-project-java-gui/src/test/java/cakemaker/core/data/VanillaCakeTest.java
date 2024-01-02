package cakemaker.core.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VanillaCakeTest {

    /**
     * Test to see if the program can report the correct cake flavour
     */
    @Test
    void cakeFlavour() {
        VanillaCake cake1 = new VanillaCake("none",0);
        assertEquals(cake1.cakeFlavour(),"Vanilla");

    }

    /**
     * Test to see if the program can report the correct cake price
     */
    @Test
    void cakePrice() {
        VanillaCake cake1 = new VanillaCake("none",0);
        assertEquals(cake1.cakePrice(),10);

    }

    /**
     * Test to see if the program can calculate the correct total price
     */
    @Test
    void totalPrice() {
        VanillaCake cake1 = new VanillaCake("none",0);
        assertEquals(cake1.totalPrice(),10);

    }

    /**
     * Test to see if the program can update the total price correctly
     */
    @Test
    void updateTotalPrice() {
        VanillaCake cake1 = new VanillaCake("none",0);
        cake1.updateTotalPrice(10);
        assertEquals(cake1.totalPrice(),20);

    }
}