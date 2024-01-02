package cakemaker.core.data;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CakeOrderTest {

    /**
     * Test to see if it can get the correct order number
     * @throws ParseException
     */
    @Test
    void getOrderNumber() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);
        assertEquals(cakeorders.getOrderNumber(),0);

        assertEquals(cakeorders.getOrderNumber(),0);
    }

    /**
     * Test to see if it can get the correct customer
     * @throws ParseException
     */
    @Test
    void getCustomer() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);
        assertEquals(cakeorders.getCustomer(),"Hanz");
    }

    /**
     * Test to see if the program correctly sets a new customer name
     * @throws ParseException
     */
    @Test
    void setCustomer() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);
        cakeorders.setCustomer("Nathan");
        assertEquals(cakeorders.getCustomer(),"Nathan");
    }

    /**
     * Test to see if the program can get the correct order date
     * @throws ParseException
     */
    @Test
    void getDateOrdered() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);
        assertEquals(cakeorders.getDateOrdered(), dateOrdered);

    }

    /**
     * Test to see if the program can set a new order date
     * @throws ParseException
     */
    @Test
    void setDateOrdered() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateOrdered1 = dateFormat.parse("09/11/2023");

        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);

        cakeorders.setDateOrdered(dateOrdered1);
        assertEquals(cakeorders.getDateOrdered(),dateOrdered1);

    }

    /**
     * Test to see if the program can get the correct finish date
     * @throws ParseException
     */
    @Test
    void getDateFinish() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        Date dateNeeded1 = dateFormat.parse("10/11/2023");

        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);


        assertEquals(cakeorders.getDateFinish(),dateNeeded);
    }

    /**
     * Test to see if the program can set a new finish date
     * @throws ParseException
     */
    @Test
    void setDateFinish() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        Date dateNeeded1 = dateFormat.parse("10/11/2023");

        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);

        cakeorders.setDateFinish(dateNeeded1);
        assertEquals(cakeorders.getDateFinish(),dateNeeded1);
    }

    /**
     * Test to see if the program can get the correct cake type
     * @throws ParseException
     */
    @Test
    void getCake() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);

    assertEquals(cakeorders.getCake(), choco);
    }

    /**
     * Test to see if the program can set the correct cake type
     * @throws ParseException
     */
    @Test
    void setCake() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        RedVelvetCake red= new RedVelvetCake("none",0) ;

        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);

        cakeorders.setCake(red);
        assertEquals(cakeorders.getCake(),red);

    }

    /**
     * Test to see if the program can get the status of the order
     * @throws ParseException
     */
    @Test
    void getFinishedStatus() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);

    assertEquals(cakeorders.getFinishedStatus(),false);

    }

    /**
     * Test to see if the program can set the status of the order correctly
     * @throws ParseException
     */
    @Test
    void setOrderFinished() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);
    cakeorders.setOrderFinished(true);
    assertEquals(cakeorders.getFinishedStatus(),true);
    }

    /**
     * Test to see if the program can sort the list of orders properly
     * @throws ParseException
     */
    @Test
    void compareTo() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<CakeOrder> actual_list = new ArrayList<>();

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeOrder1 = new CakeOrder(456,"Hanz",dateOrdered,dateNeeded,choco);
        CakeOrder cakeOrder2 = new CakeOrder(123,"Hanz",dateOrdered,dateNeeded,choco);
        CakeOrder cakeOrder3 = new CakeOrder(1233,"Hanz",dateOrdered,dateNeeded,choco);

        actual_list.add(cakeOrder3);
        actual_list.add(cakeOrder2);
        actual_list.add(cakeOrder1);
        Collections.sort(actual_list);

        ArrayList<CakeOrder> expected_list = new ArrayList<>();
        expected_list.add(cakeOrder2);
        expected_list.add(cakeOrder1);
        expected_list.add(cakeOrder3);

        assertEquals(expected_list, actual_list);
    }

    /**
     * Test to see if the order is output properly
     * @throws ParseException
     */
    @Test
    void testToString() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);


        String toString= "Order for order number " + 0 + "\n" +
                "\tCustomer name: " + "Hanz" + "\n" +
                "\tDate ordered: " + dateNeeded + "\n" +
                "\tDate needed by: " + dateOrdered + "\n" +
                "\tType of cake: " + "Chocolate" + "\n" +
                "\tBase cake price: " + 15.0 + "\n" +
                "\tSpecial Requests: " + "none" + "\n" +
                "\tExtra added price: $" + 0.0 + "\n" +
                "\tTotal price: $" + 15.0 + "\n" +
                "\tCake is finished: " + false + "\n";

        assertEquals(toString,cakeorders.toString());
    }
}