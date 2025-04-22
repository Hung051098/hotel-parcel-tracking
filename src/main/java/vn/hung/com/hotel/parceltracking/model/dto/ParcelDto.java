package vn.hung.com.hotel.parceltracking.model.dto;


import java.io.Serializable;

public class ParcelDto implements Serializable {
    private Long id;
    private String trackingNumber;
    private Long guestId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }
}