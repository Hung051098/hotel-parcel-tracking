package vn.hung.com.hotel.parceltracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.hung.com.hotel.parceltracking.model.entity.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM guest WHERE id = ?1 AND checked_in = ?2")
    Guest findByIdAndCheckedIn(Long id, boolean checkedIn);
}
