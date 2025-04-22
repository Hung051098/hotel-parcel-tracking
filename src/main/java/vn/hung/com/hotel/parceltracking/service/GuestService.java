package vn.hung.com.hotel.parceltracking.service;

import vn.hung.com.hotel.parceltracking.model.entity.Guest;

public interface GuestService {
    Guest getCheckedInGuest(Long guestId);
}
