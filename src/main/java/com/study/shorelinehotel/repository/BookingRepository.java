package com.study.shorelinehotel.repository;

import com.study.shorelinehotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoomId(Long roomId);

    Optional<Booking> findByBookingConfirmation(String confirmation);

    List<Booking> findAllByGuestEmail(String email);
}
