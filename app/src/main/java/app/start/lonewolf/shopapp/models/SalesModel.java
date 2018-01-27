package app.start.lonewolf.shopapp.models;

/**
 * Created by Infoview on 11/16/2017.
 */

public class SalesModel {
    private String item, quantity, salesId, price, date;

    public SalesModel(){

    }

    public SalesModel(String item, String quantity, String salesId, String price, String date) {
        this.item = item;
        this.quantity = quantity;
        this.salesId = salesId;
        this.price = price;
        this.date =date;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }
}
