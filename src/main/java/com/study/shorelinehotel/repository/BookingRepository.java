package com.study.shorelinehotel.repository;

import com.study.shorelinehotel.model.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookedRoom, Long> {

    List<BookedRoom> findByRoomId(Long roomId);

    Optional<BookedRoom> findByBookingConfirmation(String confirmation);

    List<BookedRoom> findAllByGuestEmail(String email);
}
