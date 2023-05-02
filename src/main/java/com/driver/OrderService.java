package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();

    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    public void addPartner(String partnerId){
        orderRepository.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId){
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId){
        Order order = orderRepository.getOrderById(orderId);
        return order;
    }

    public DeliveryPartner getPartnerById(String partnerId){
        DeliveryPartner deliveryPartner = orderRepository.getPartnerById(partnerId);
        return deliveryPartner;
    }

    public int getOrderCountByPartnerId(String partnerId){
        int count = orderRepository.getOrderCountByPartnerId(partnerId);
        return count;
    }

    public  List<String> getOrdersByPartnerId(String partnerId){
        List<String> list = orderRepository.getOrdersByPartnerId(partnerId);
        return list;
    }

    public List<String> getAllOrders(){
        List<String> list = orderRepository.getAllOrders();
        return list;
    }

    public int getCountOfUnassignedOrders(){
        int count = orderRepository.getCountOfUnassignedOrders();
        return count;
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){

        String[] arr = time.split(":");
        int hh = Integer.parseInt(arr[0]);
        int mm = Integer.parseInt(arr[1]);

        int timeInt = (hh * 60) + mm;

        int count = orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(timeInt, partnerId);

        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){

        int timeInt = orderRepository.getLastDeliveryTmeByPartnerId(partnerId);
        int hh = timeInt / 60;
        int mm = timeInt % 60;

        String HH = String.valueOf(hh);
        if(HH.length() == 1){
            HH = '0' + HH;
        }

        String MM = String.valueOf(mm);
        if(MM.length() == 1){
            MM = '0' + MM;
        }

        String time = HH + ":" + MM;

        return time;
    }

    public void deletePartnerById(String partnerId){
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }


}
