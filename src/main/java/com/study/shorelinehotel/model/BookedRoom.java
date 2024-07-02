package com.study.shorelinehotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "check_in")
    private LocalDate checkInDate;
    @Column(name = "check_out")
    private LocalDate checkOutDate;
    @Column(name = "guest_full_name")
    private String guestFullName;
    @Column(name = "guest_email")
    private String guestEmail;
    @Column(name = "adults")
    private int numberOfAdults;
    @Column(name = "children")
    private int numberOfChildren;
    @Column(name = "total_guests")
    private int totalNumberOfGuests;
    @Column(name = "confirmation_code")
    private String bookingConfirmation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    public BookedRoom(String bookingConfirmation) {
        this.bookingConfirmation = bookingConfirmation;
    }

    public void calculateTotalNumberOfGuests() {
        this.totalNumberOfGuests = numberOfAdults + numberOfChildren;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
        calculateTotalNumberOfGuests();
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
        calculateTotalNumberOfGuests();
    }

}
