package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;

        String[] arr = deliveryTime.split(":");

        int hh = Integer.parseInt(arr[0]);
        int mm = Integer.parseInt(arr[1]);

        int time = (hh * 60) + mm;

        this.deliveryTime = time;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
