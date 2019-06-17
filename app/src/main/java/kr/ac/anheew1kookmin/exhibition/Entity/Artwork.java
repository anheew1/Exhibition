package kr.ac.anheew1kookmin.exhibition.Entity;

public class Artwork {
    private String id;
    private String name;
    private String photoID;
    private String artist_id;
    private String artType;
    private String description;
    private String size;
    private boolean isRental;
    private int peroid;
    private int price;

    public Artwork(){

    }

    public Artwork(String id, String name, String photoID, String artist_id, String artType, String description, String size, int peroid, int price) {
        this.id = id;
        this.name = name;
        this.photoID = photoID;
        this.artist_id = artist_id;
        this.artType = artType;
        this.description = description;
        this.size = size;
        this.peroid = peroid;
        this.price = price;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

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

    public int getPeroid() {
        return peroid;
    }

    public void setPeroid(int peroid) {
        this.peroid = peroid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
