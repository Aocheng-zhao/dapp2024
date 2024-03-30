package be.kuleuven.foodrestservice.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrderConfirmation {
    public OrderConfirmation(Order order) {
        this.date = new Date();
        this.address = order.getAddress();
        this.orderItems = order.getOrderItems();
        for(OrderItem item : order.orderItems){
            this.totalCost += item.getPrice();
        }
    }

    protected Date date;
    protected String address;
    protected List<OrderItem> orderItems;
    protected double totalCost;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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
        this.orderItems = orderItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderConfirmation that = (OrderConfirmation) o;
        return Double.compare(totalCost, that.totalCost) == 0 && Objects.equals(date, that.date) && Objects.equals(address, that.address) && Objects.equals(orderItems, that.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, address, orderItems, totalCost);
    }

    @Override
    public String toString() {
        return "OrderConfirmation{" +
                "date=" + date +
                ", address='" + address + '\'' +
                ", OrderItems=" + orderItems +
                ", totalCost=" + totalCost +
                '}';
    }
}

