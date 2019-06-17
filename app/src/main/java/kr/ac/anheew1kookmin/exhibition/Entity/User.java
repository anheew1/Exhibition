package kr.ac.anheew1kookmin.exhibition.Entity;

import java.util.ArrayList;

public class User {
    private String uid;
    private String email;
    private String name;
    private String profileImg;
    private ArrayList<String> artworkIDList;
    private ArrayList<String> placeIDList;
    private ArrayList<String> transactionIDList;

    public User(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public ArrayList<String> getArtworkIDList() {
        return artworkIDList;
    }

    public void setArtworkIDList(ArrayList<String> artworkIDList) {
        this.artworkIDList = artworkIDList;
    }

    public ArrayList<String> getPlaceIDList() {
        return placeIDList;
    }

    public void setPlaceIDList(ArrayList<String> placeIDList) {
        this.placeIDList = placeIDList;
    }

    public ArrayList<String> getTransactionIDList() {
        return transactionIDList;
    }

    public void setTransactionIDList(ArrayList<String> transactionIDList) {
        this.transactionIDList = transactionIDList;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public void addArtwork(Artwork artwork){
        artworkIDList.add(artwork.getId());
    }
    public void addPlace(Place place){
        placeIDList.add(place.getId());
    }
    public void addTransaction(Transaction trans){
        transactionIDList.add(trans.getId());
    }
}
