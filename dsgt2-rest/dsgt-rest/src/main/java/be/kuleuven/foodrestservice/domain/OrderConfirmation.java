package be.kuleuven.foodrestservice.domain;

import java.util.List;
import java.util.Objects;

public class OrderConfirmation {
    public OrderConfirmation(int id) {
        this.id = id;
    }

    public OrderConfirmation(int id, String address, List<OrderItem> orderItems) {
        this.id = id;
        this.address = address;
        OrderItems = orderItems;
    }

    protected  int id;
    protected String address;
    protected List<OrderItem> OrderItems;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderItem> getOrderItems() {
        return OrderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        OrderItems = orderItems;
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
        OrderConfirmation order = (OrderConfirmation) o;
        return id == order.id && Objects.equals(address, order.address) && Objects.equals(OrderItems, order.OrderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, OrderItems);
    }
}

