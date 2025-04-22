package vn.hung.com.hotel.parceltracking.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hung.com.hotel.parceltracking.model.entity.Guest;
import vn.hung.com.hotel.parceltracking.repository.GuestRepository;
import vn.hung.com.hotel.parceltracking.service.GuestService;

@Service
public class GuestServiceImpl implements GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public Guest getCheckedInGuest(Long guestId) {
        return guestRepository.findByIdAndCheckedIn(guestId, true);
    }

    @PostConstruct
    private void iniGuest() {
        // Insert two guests into the Guest entity
        Guest guest1 = new Guest();
        guest1.setName("HUNG");
        guest1.setCheckedIn(false);
        guestRepository.save(guest1);

        Guest guest2 = new Guest();
        guest2.setName("MIA");
        guest2.setCheckedIn(true);
        guestRepository.save(guest2);

        System.out.println("Inserted two guests into the Guest entity.");
    }
}
