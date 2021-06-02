package Business_Layer;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    //private static final long serialVersionUID = -5947938895461248815L;
    private int orderID;
    private int clientID;
    private Date orderDate;
    private int price;

    public Order(int orderID, int clientID, Date orderDate, int price){
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
        this.price = price;
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

    public int getPrice() {
        return price;
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

    public String toString(){
        return orderID + " " + clientID + " " + orderDate + "\n";
    }

    public int getHour(){
        return orderDate.getHours();
    }
}
