package com.study.shorelinehotel.service;

import com.study.shorelinehotel.model.Booking;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IBookingService {
    List<Booking> getAllBookings();

    List<Booking> getBookingsByUserEmail(String email);

    String saveBooking(Long roomId, Booking bookingRequest);

    Booking findByBookingConfirmationCode(String confirmationCode);

    @Transactional
    void cancelBooking(Long bookingId);
}
