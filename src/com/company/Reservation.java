package com.company;

public interface Reservation {

  /**
   * Add seating detail to this reservation.
   *
   * @param row which row is assigned
   * @param seatNumber the seat number(s) of this reservation is assigned.
   * */
  void assignSeat(String row, int seatNumber);

  /**
   * Get party size of this reservation.
   *
   * @return party size of this reservation.
   * */
  int getPartySize();

  /**
   * Get reservation number of this reservation.
   *
   * @return reservation number of this reservation.
   * */
  String getReservationNumber();

  /**
   * Get seating detail of this reservation.
   *
   * @return String contains all the info of this reservation's seatings.
   * */
  String getAllSeats();
}
