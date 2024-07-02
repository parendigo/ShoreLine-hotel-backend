package com.study.shorelinehotel.service;

import com.study.shorelinehotel.model.BookedRoom;

import java.util.List;
import java.util.Optional;

public interface IBookingService {
    List<BookedRoom> getAllBookings();

    List<BookedRoom> getBookingsByUserEmail(String email);

    String saveBooking(Long roomId, BookedRoom bookingRequest);

    BookedRoom findByBookingConfirmationCode(String confirmationCode);

    void cancelBooking(Long bookingId);
}
