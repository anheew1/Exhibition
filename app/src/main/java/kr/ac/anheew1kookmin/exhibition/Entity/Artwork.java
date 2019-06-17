package kr.ac.anheew1kookmin.exhibition.Entity;

public class Artwork {
    private String id;
    private String name;
    private String photoID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isRental() {
        return isRental;
    }

    public void setRental(boolean rental) {
        isRental = rental;
    }

    public int getPeroid() {
        return Peroid;
    }

    public void setPeroid(int peroid) {
        Peroid = peroid;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    private String artType;
    private String description;
    private String size;
    private boolean isRental;
    private int Peroid;
    private int Price;

    public Artwork(){

    }
}
