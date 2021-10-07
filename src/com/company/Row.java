package com.company;

public interface Row {
  /**
   * Assign seats to this row for the reservation.
   *
   * @return Seats assigned success or not.
   * */
  boolean reserve(Reservation reservation);
}
