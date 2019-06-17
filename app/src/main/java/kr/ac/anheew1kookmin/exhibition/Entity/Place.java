package kr.ac.anheew1kookmin.exhibition.Entity;

public class Place {
    private String id;
    private String name;
    private String photoId;
    private String provider_id;
    private String artType;
    private String description;
    private String size;
    public Place(){

    }
    public  Place(String id,String name,String photoId,String provider_id,
                  String artType,String description, String size){
        this.id=id;
        this.name=name;
        this.photoId=photoId;
        this.provider_id=provider_id;
        this.artType=artType;
        this.description=description;
        this.size=size;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
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
        return photoId;
    }

    public void setPhotoID(String photoID) {
        this.photoId = photoID;
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
}
