package vn.hung.com.hotel.parceltracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import vn.hung.com.hotel.parceltracking.exception.ValidationException;
import vn.hung.com.hotel.parceltracking.model.dto.ParcelDto;
import vn.hung.com.hotel.parceltracking.model.entity.Guest;
import vn.hung.com.hotel.parceltracking.model.entity.Parcel;
import vn.hung.com.hotel.parceltracking.model.response.Response;
import vn.hung.com.hotel.parceltracking.service.GuestService;
import vn.hung.com.hotel.parceltracking.service.impl.ParcelServiceImpl;
import vn.hung.com.hotel.parceltracking.repository.ParcelRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ParcelServiceImplTest {

    @InjectMocks
    private ParcelServiceImpl parcelService;

    @Mock
    private ParcelRepository parcelRepository;

    @Mock
    private GuestService guestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAcceptParcel_Success() {
        ParcelDto parcelDto = new ParcelDto();
        parcelDto.setTrackingNumber("12345");
        parcelDto.setGuestId(1L);

        Guest guest = new Guest();
        guest.setId(1L);

        Parcel parcel = new Parcel();
        BeanUtils.copyProperties(parcelDto, parcel);

        when(guestService.getCheckedInGuest(1L)).thenReturn(guest);
        when(parcelRepository.save(any(Parcel.class))).thenReturn(parcel);

        Response response = parcelService.acceptParcel(parcelDto);

        Parcel savedParcel = (Parcel) response.getData();
        assertEquals("12345", savedParcel.getTrackingNumber());
        verify(guestService, times(1)).getCheckedInGuest(1L);
        verify(parcelRepository, times(1)).save(any(Parcel.class));
    }

    @Test
    void testAcceptParcel_GuestNotFound() {
        ParcelDto parcelDto = new ParcelDto();
        parcelDto.setGuestId(1L);

        when(guestService.getCheckedInGuest(1L)).thenReturn(null);

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            parcelService.acceptParcel(parcelDto);
        });

        assertEquals("Guest not found or not checked in", exception.getMessage());
        verify(guestService, times(1)).getCheckedInGuest(1L);
        verify(parcelRepository, never()).save(any(Parcel.class));
    }

    @Test
    void testGetParcelsForGuest() {
        Long guestId = 1L;
        Parcel parcel1 = new Parcel();
        parcel1.setId(1L);
        parcel1.setTrackingNumber("12345");

        Parcel parcel2 = new Parcel();
        parcel2.setId(2L);
        parcel2.setTrackingNumber("67890");

        List<Parcel> parcels = Arrays.asList(parcel1, parcel2);

        when(parcelRepository.findByGuestId(guestId)).thenReturn(parcels);

        Response response = parcelService.getParcelsForGuest(guestId);

        List<Parcel> result = (List<Parcel>) response.getData();
        assertEquals(2, result.size());
        assertEquals("12345", result.get(0).getTrackingNumber());
        assertEquals("67890", result.get(1).getTrackingNumber());
        verify(parcelRepository, times(1)).findByGuestId(guestId);
    }
}
