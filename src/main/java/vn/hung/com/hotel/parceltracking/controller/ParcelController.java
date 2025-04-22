package vn.hung.com.hotel.parceltracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hung.com.hotel.parceltracking.model.dto.ParcelDto;
import vn.hung.com.hotel.parceltracking.model.response.Response;
import vn.hung.com.hotel.parceltracking.service.ParcelService;

@RestController
@RequestMapping("/api/parcels")
public class ParcelController {
    @Autowired
    private ParcelService parcelService;

    @PostMapping
    public ResponseEntity<Response> acceptParcel(@RequestBody ParcelDto parcelDto) {
        Response savedParcel = parcelService.acceptParcel(parcelDto);
        return ResponseEntity.ok(savedParcel);
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<Response> getParcelsForGuest(@PathVariable Long guestId) {
        Response parcels = parcelService.getParcelsForGuest(guestId);
        return ResponseEntity.ok(parcels);
    }
}
