package vn.hung.com.hotel.parceltracking.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.hung.com.hotel.parceltracking.exception.ValidationException;
import vn.hung.com.hotel.parceltracking.model.dto.ParcelDto;
import vn.hung.com.hotel.parceltracking.model.entity.Guest;
import vn.hung.com.hotel.parceltracking.model.entity.Parcel;
import vn.hung.com.hotel.parceltracking.model.response.Response;
import vn.hung.com.hotel.parceltracking.repository.ParcelRepository;
import vn.hung.com.hotel.parceltracking.service.GuestService;
import vn.hung.com.hotel.parceltracking.service.ParcelService;

import java.util.Locale;

@Service
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private GuestService guestService;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    @Transactional
    public Response acceptParcel(ParcelDto parcelDto) {
        Parcel parcel = new Parcel();
        Guest guest = guestService.getCheckedInGuest(parcelDto.getGuestId());
        if (guest == null) {
            throw new ValidationException(applicationContext
                    .getMessage("guest.not-found", null, Locale.getDefault()));
        }
        BeanUtils.copyProperties(parcelDto, parcel);
        return Response.builder().data(parcelRepository.save(parcel)).build();
    }

    @Override
    public Response getParcelsForGuest(Long guestId) {
        return Response.builder().data(parcelRepository.findByGuestId(guestId)).build();
    }
}
