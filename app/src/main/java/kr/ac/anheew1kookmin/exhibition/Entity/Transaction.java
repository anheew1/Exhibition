package kr.ac.anheew1kookmin.exhibition.Entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Transaction {
    private String id;
    private String buyer_id;
    private String seller_id;
    private Date purchase_date;
    private String artwork_id;
    private String peroidical_price;

    public Transaction(){

    }
    public  Transaction(String id,String buyer_id,String seller_id, Date purchase_date,
                        String artwork_id,String peroidical_price){
        this.id =id;
        this.buyer_id = buyer_id;
        this.seller_id = seller_id;
        this.artwork_id = artwork_id;
        this.purchase_date = purchase_date;
        this.peroidical_price = peroidical_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getArtwork_id() {
        return artwork_id;
    }

    public void setArtwork_id(String artwork_id) {
        this.artwork_id = artwork_id;
    }

    public String getPeroidical_price() {
        return peroidical_price;
    }

    public void setPeroidical_price(String peroidical_price) { this.peroidical_price = peroidical_price; }

}
