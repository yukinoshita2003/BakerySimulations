package cakemaker.core.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedVelvetCakeTest {

    /**
     * Test to see if the program can report the correct cake flavour
     */
    @Test
    void cakeFlavour() {
        RedVelvetCake cake1 = new RedVelvetCake("none",0);
        assertEquals(cake1.cakeFlavour(),"Red Velvet");

    }

    /**
     * Test to see if the program can report the correct cake price
     */
    @Test
    void cakePrice() {
        RedVelvetCake cake1 = new RedVelvetCake("none",0);
        assertEquals(cake1.cakePrice(),20);

    }

    /**
     * Test to see if the program can calculate the correct total price
     */
    @Test
    void totalPrice() {
        RedVelvetCake cake1 = new RedVelvetCake("none",0);
        assertEquals(cake1.totalPrice(),20);

    }

    /**
     * Test to see if the program can update the total price correctly
     */
    @Test
    void updateTotalPrice() {
        RedVelvetCake cake1 = new RedVelvetCake("none",0);
        cake1.updateTotalPrice(10);
        assertEquals(cake1.totalPrice(),30);

    }
}