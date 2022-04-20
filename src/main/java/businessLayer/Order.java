package businessLayer;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    private int orderID;
    private String clientID;
    private Date data;
    private double costTotal;

    public Order(int orderID, String clientID, Date data, double costTotal) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.data = data;
        this.costTotal = costTotal;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(double costTotal) {
        this.costTotal = costTotal;
    }
}
