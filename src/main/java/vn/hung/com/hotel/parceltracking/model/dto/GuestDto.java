package vn.hung.com.hotel.parceltracking.model.dto;

import java.io.Serializable;

public class GuestDto implements Serializable {
    private Long id;
    private String name;
    private boolean checkedIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
