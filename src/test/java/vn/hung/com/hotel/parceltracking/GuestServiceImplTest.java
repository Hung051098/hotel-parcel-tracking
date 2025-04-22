package vn.hung.com.hotel.parceltracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vn.hung.com.hotel.parceltracking.model.entity.Guest;
import vn.hung.com.hotel.parceltracking.repository.GuestRepository;
import vn.hung.com.hotel.parceltracking.service.impl.GuestServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class GuestServiceImplTest {

    @InjectMocks
    private GuestServiceImpl guestService;

    @Mock
    private GuestRepository guestRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCheckedInGuest_Success() {
        Long guestId = 1L;
        Guest guest = new Guest();
        guest.setId(guestId);
        guest.setCheckedIn(true);

        when(guestRepository.findByIdAndCheckedIn(guestId, true)).thenReturn(guest);

        Guest result = guestService.getCheckedInGuest(guestId);

        assertEquals(guestId, result.getId());
        assertEquals(true, result.isCheckedIn());
        verify(guestRepository, times(1)).findByIdAndCheckedIn(guestId, true);
    }

    @Test
    void testGetCheckedInGuest_NotFound() {
        Long guestId = 1L;

        when(guestRepository.findByIdAndCheckedIn(guestId, true)).thenReturn(null);

        Guest result = guestService.getCheckedInGuest(guestId);

        assertNull(result);
        verify(guestRepository, times(1)).findByIdAndCheckedIn(guestId, true);
    }
}
