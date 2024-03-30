package be.kuleuven.foodrestservice.domain;

import java.util.*;
public class Order {
    public Order(int id) {
        this.id = id;
    }

    public Order(int id, String address, List<OrderItem> orderItems) {
        this.id = id;
        this.address = address;
        this.orderItems = orderItems;
    }

    protected  int id;
    protected String address;
    protected List<OrderItem> orderItems;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(address, order.address) && Objects.equals(orderItems, order.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, orderItems);
    }
}

