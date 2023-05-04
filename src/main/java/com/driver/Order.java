package com.driver;

public class Order {

    private String id;
    private int deliveryTime;



    public Order(String id, String deliveryTime) {

        this.id = id;
////         char[] arr = new char[2];
////         arr[0] = deliveryTime.charAt(0);
//         String s1 = String.valueOf(deliveryTime.charAt(0) + deliveryTime.charAt(1));
//         String s2 = String.valueOf(deliveryTime.charAt(3) + deliveryTime.charAt(4));
//         int hh = Integer.valueOf(s1);
//         int mm = Integer.valueOf(s2);
//
//         int time = (hh*60)+mm;
        String arr[] = deliveryTime.split(":");
        int hh = Integer.parseInt(arr[0]);
        int mm = Integer.parseInt(arr[1]);
        int time = (hh*60)+mm;

        this.deliveryTime = time;


        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}


}