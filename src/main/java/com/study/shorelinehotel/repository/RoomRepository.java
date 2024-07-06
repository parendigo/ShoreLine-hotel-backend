package com.study.shorelinehotel.repository;

import com.study.shorelinehotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT DISTINCT r.roomType FROM Room r")
    List<String> findDistinctRoomTypes();

    @Query(" select r from Room r " +
            " where r.roomType like %:roomType% " +
            " and r.id not in (" +
            "   select br.room.id from Booking br" +
            "   where ((br.checkInDate <= :checkOutDate) and (br.checkOutDate >= :checkInDate))" +
            ")")
    List<Room> findAvailableRoomsByDatesAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType);
}
