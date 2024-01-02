package cakemaker.core.data;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;



class DataTest {

    /**
     * Check to see if the program can get the correct order number
     * @throws ParseException
     */
    @Test
    void getCakeOrderID() throws ParseException {

        // storeCustomerCakeOrderInformation is utilized to check if it contains the same items.
        Data data = new Data();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        Cake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);

        data.storeCustomerCakeOrderInformation(0,"Hanz",dateOrdered,dateNeeded,"Chocolate - $15","none",0);
        data.storeCustomerCakeOrderInformation(1,"Hanz",dateOrdered,dateNeeded,"Chocolate - $15","none",0);



        assertEquals(data.getCakeOrderID().size(),2);
        assertTrue(data.getCakeOrderID().containsKey(0));
        assertTrue(data.getCakeOrderID().containsKey(1));


    }


    /**
     * Check to see if the program can get the correct list of cake orders
     * @throws ParseException
     */
    @Test
    void getListOfCakeOrders() throws ParseException {
        // storeCustomerCakeOrderInformation is utilized to check if it contains the same items.
        Data data = new Data();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOrdered = dateFormat.parse("08/11/2023");
        Date dateNeeded = dateFormat.parse("09/11/2023");
        ChocolateCake choco= new ChocolateCake("none",0) ;
        CakeOrder cakeorders = new CakeOrder(0,"Hanz",dateOrdered,dateNeeded,choco);

        HashMap<Integer, CakeOrder> cakeOrderID1 = new HashMap<>();
        ArrayList<CakeOrder> list = new ArrayList<>();
        list.add(cakeorders);
        cakeOrderID1.put(0,cakeorders);

        data.storeCustomerCakeOrderInformation(0,"Hanz",dateOrdered,dateNeeded,"Chocolate - $15","none",0);

        assertEquals(data.getListOfCakeOrders().size(),1);

    }

    /**
     * Check to see if the program can store cake orders correctly
     * @throws ParseException
     */
    @Test
    void storeCustomerCakeOrderInformation() throws ParseException {

        Data data = new Data();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String needDate= "11/11/1111";
        String finishDate = "11/11/1112";
        Date dateFinish = dateFormat.parse(needDate);
        Date dateOrdered =  dateFormat.parse(finishDate);
        int orderNumber = 111;
        String customer= "Hanz";
        String cakeOrder ="Chocolate - $15";
        int cakePrice = 15;
        String specialRequest ="none";
        double extraPrice = 0;
        double totalPrice = 15;
        boolean isCakeFinished = false;


        data.storeCustomerCakeOrderInformation(orderNumber, customer, dateOrdered, dateFinish, cakeOrder, specialRequest, extraPrice);
        CakeOrder cake = data.getListOfCakeOrders().get(0);
        int size = data.getListOfCakeOrders().size();
        assertEquals(1,size);
        assertEquals(cake.getOrderNumber(),orderNumber);
        assertEquals(cake.getCustomer(),customer);
        assertEquals(cake.getDateOrdered(),dateOrdered);
        assertEquals(cake.getDateFinish(),dateFinish);
        assertEquals(cake.getCake().cakeFlavour(),"Chocolate");
        assertEquals(cake.getCake().cakePrice(),cakePrice);
        assertEquals(cake.getCake().getSpecialRequest(),specialRequest);
        assertEquals(cake.getCake().getExtraPrice(),extraPrice);
        assertEquals(cake.getCake().totalPrice(),totalPrice);
        assertEquals(cake.getFinishedStatus(),isCakeFinished);

        CakeOrder cake1 = data.getCakeOrderID().get(orderNumber);
        int size1 = data.getCakeOrderID().size();

        boolean exist = data.getCakeOrderID().containsKey(orderNumber);
        assertEquals(1,size1);
        assertTrue(exist);
        String customer1r = cake1.getCustomer();
        Date dateOrdered1 = cake1.getDateOrdered();
        Date dateFinished1 = cake1.getDateFinish();
        String cakeOrdered1 = cake1.getCake().cakeFlavour();
        int cakePrice1 = (int) cake1.getCake().cakePrice();
        String sR = cake1.getCake().getSpecialRequest();
        double extraPrice1 = cake1.getCake().getExtraPrice();
        double totalPrice1 = cake1.getCake().totalPrice();
        boolean status = cake1.getFinishedStatus();

        assertEquals(customer,customer1r);
        assertEquals(dateOrdered,dateOrdered1);
        assertEquals(dateFinish,dateFinished1);
        assertEquals("Chocolate",cakeOrdered1);
        assertEquals(cakePrice,cakePrice1);
        assertEquals(specialRequest,sR);
        assertEquals(extraPrice,extraPrice1);
        assertEquals(totalPrice,totalPrice1);
        assertEquals(isCakeFinished,status);
    }

    /**
     * Check to see if the program calculate the item that occurs the most amount of times in a list
     * @throws ParseException
     */
    @Test
    void calculateMaximumOccurrence() throws ParseException {

        Data data = new Data();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String needDate= "11/11/1111";
        String finishDate = "11/11/1112";
        Date dateFinish = dateFormat.parse(needDate);
        Date dateOrdered =  dateFormat.parse(finishDate);
        int orderNumber = 111 ;
        int orderNumber2 = 112 ;
        int orderNumber3 = 113 ;
        int orderNumber4 = 114 ;
        String customer= "Hanz";
        String cakeOrder ="Chocolate - $15" ;
        String cakeOrder1 = "Vanilla - $10";
        String cakeOrder2= "Chocolate - $15";
        String cakeOrder3= "Chocolate - $15";
        String specialRequest ="none"  ;
        int extraPrice = 0;


        data.storeCustomerCakeOrderInformation(orderNumber,customer,dateOrdered,dateFinish,cakeOrder,specialRequest,extraPrice);
        data.storeCustomerCakeOrderInformation(orderNumber2,customer,dateOrdered,dateFinish,cakeOrder1,specialRequest,extraPrice);
        data.storeCustomerCakeOrderInformation(orderNumber3,customer,dateOrdered,dateFinish,cakeOrder2,specialRequest,extraPrice);
        data.storeCustomerCakeOrderInformation(orderNumber4,customer,dateOrdered,dateFinish,cakeOrder3,specialRequest,extraPrice);


        CakeOrder cake1 = data.getListOfCakeOrders().get(0);
        CakeOrder cake2 = data.getListOfCakeOrders().get(1);
        CakeOrder cake3 = data.getListOfCakeOrders().get(2);
        CakeOrder cake4 = data.getListOfCakeOrders().get(3);

        String cakes1 = cake1.getCake().cakeFlavour();
        String cakes2 = cake2.getCake().cakeFlavour();
        String cakes3 = cake3.getCake().cakeFlavour();
        String cakes4 = cake4.getCake().cakeFlavour();

        ArrayList<String> list = new ArrayList<>();
        list.add(cakes1);
        list.add(cakes2);
        list.add(cakes3);
        list.add(cakes4);


        Object[] result = data.calculateMaximumOccurrence(list);
        int mostFrequentValue = (int) result[1];
        Object mostFrequentKey = result[0];
        ArrayList<Object> expectedKey = new ArrayList<>();
        expectedKey.add("Chocolate");

        assertEquals(expectedKey, mostFrequentKey, "Item does not match");
        assertEquals(3, mostFrequentValue, "Value does not match");
    }
}