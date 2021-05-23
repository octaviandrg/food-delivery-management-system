package Business_Layer;

import java.util.Date;
import java.util.Objects;

public class Order {
    private int orderID;
    private int clientID;
    private Date orderDate;

    public Order(int orderID, int clientID, Date orderDate){
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
    }

    @Override
    public int hashCode(){
        return Objects.hash(orderID, clientID, orderDate);
    }

    @Override
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof Order))
            return false;
        Order order = (Order)o;
        return orderID == order.orderID &&
                clientID == order.clientID &&
                Objects.equals(orderDate, order.orderDate);
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
