package vn.hung.com.hotel.parceltracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hung.com.hotel.parceltracking.model.entity.Parcel;

import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    List<Parcel> findByGuestId(Long guestId);
}
