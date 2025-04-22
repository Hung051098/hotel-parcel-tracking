package vn.hung.com.hotel.parceltracking.service;

import vn.hung.com.hotel.parceltracking.model.dto.ParcelDto;
import vn.hung.com.hotel.parceltracking.model.response.Response;

public interface ParcelService {
    Response acceptParcel(ParcelDto parcelDto);

    Response getParcelsForGuest(Long guestId);
}
