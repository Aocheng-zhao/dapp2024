package be.kuleuven.foodrestservice.domain;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OrdersRepository {
    // map: id -> meal
    private static final Map<Integer, Order> orders = new HashMap<>();
    private static final AtomicInteger orderIdCounter = new AtomicInteger(1);
    @PostConstruct
    public void initData() {

        Order a = new Order(orderIdCounter.getAndIncrement());
        a.setAddress("KU Leuven");
        List<OrderItem> aItems = new ArrayList<>();
        aItems.add(new OrderItem("Steak",10.00));
        aItems.add(new OrderItem("Portobello",7.00));
        a.setOrderItems(aItems);

        orders.put(a.getId(), a);

        Order b = new Order(orderIdCounter.getAndIncrement());
        b.setAddress("KU Leuven");
        List<OrderItem> bItems = new ArrayList<>();
        bItems.add(new OrderItem("Portobello",7.00));
        b.setOrderItems(bItems);

        orders.put(b.getId(), b);

    }

    public Optional<Order> findOrder(int id) {
        Order order = orders.get(id);
        return Optional.ofNullable(order);
    }

    public Collection<Order> getAllMeal() {
        return orders.values();
    }



    public Optional<Order> addOrder(Order newOrder){
        Optional<Order> order = findOrder(newOrder.getId());
        if(order.isEmpty()){

            orders.put(newOrder.id,newOrder);
        }
        return order;
    }



}
