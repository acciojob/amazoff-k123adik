package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String, Order> orderDb = new HashMap<>();
    HashMap<String, DeliveryPartner> partnerDb = new HashMap<>();
    HashMap<String, List<String>> orderPartnerDb = new HashMap<>();
    HashSet<String> isOrderAssigned = new HashSet<>();

    public void addOrder(Order order){
        String key = order.getId();
        orderDb.put(key, order);
        isOrderAssigned.add(order.getId());
    }

    public void addPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        partnerDb.put(partnerId, deliveryPartner);
    }

    public void addOrderPartnerPair(String orderId, String partnerId){
        List<String> list = orderPartnerDb.getOrDefault(partnerId, new ArrayList<>());
        list .add(orderId);

        DeliveryPartner deliveryPartner = partnerDb.get(partnerId);
        int noOfOrders = deliveryPartner.getNumberOfOrders();
        deliveryPartner.setNumberOfOrders(noOfOrders + 1);

        orderPartnerDb.put(partnerId, list);
        isOrderAssigned.remove(orderId);
    }

    public Order getOrderById(String orderId){
        Order order = orderDb.get(orderId);
        return order;
    }

    public DeliveryPartner getPartnerById(String partnerId){
        DeliveryPartner deliveryPartner = partnerDb.get(partnerId);
        return deliveryPartner;
    }

    public int getOrderCountByPartnerId(String partnerId){
        int count = orderPartnerDb.get(partnerId).size();
        return count;
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> list = orderPartnerDb.getOrDefault(partnerId, new ArrayList<>());
        return list;
    }

    public List<String> getAllOrders(){
        List<String> list = new ArrayList<>();

        for(String orderId : orderDb.keySet()){
            list.add(orderId);
        }

        return list;
    }

    public int getCountOfUnassignedOrders(){
        return isOrderAssigned.size();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(Integer time, String partnerId){
        List<String> list = orderPartnerDb.getOrDefault(partnerId, new ArrayList<>());

        int count = 0;

        for(String orderId : list){
            Order order = orderDb.get(orderId);
            if(order.getDeliveryTime() > time){
                count++;
            }
        }
        return count;
    }

    public int getLastDeliveryTmeByPartnerId(String partnerId){
        List<String> list = orderPartnerDb.getOrDefault(partnerId, new ArrayList<>());

        int timeInt = 0;
        for(String orderId : list){
            Order order = orderDb.get(orderId);
            if(order.getDeliveryTime() > timeInt){
                timeInt = order.getDeliveryTime();
            }
        }
        return timeInt;
    }

    public void deletePartnerById(String partnerId){
        if(!orderPartnerDb.isEmpty()){
            isOrderAssigned.addAll(orderPartnerDb.get(partnerId));
        }
        orderPartnerDb.remove(partnerId);
        partnerDb.remove(partnerId);
    }

    public void deleteOrderById(String orderId){
        if(orderDb.containsKey(orderId)){
            isOrderAssigned.remove(orderId);
        }
        else{
            for(String partnerId : orderPartnerDb.keySet()){
                List<String> list = orderPartnerDb.get(partnerId);
                if(list.contains(orderId)){
                    list.remove(orderId);
                }
            }
        }
    }
}
