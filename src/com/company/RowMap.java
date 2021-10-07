package com.company;

public interface RowMap {

  /**
   * Assign seats for reservation.
   *
   * @param reservation reservation needed take care of
   * @return Reservation is successfully assigned or not.
   * */
  boolean takeReservation(Reservation reservation) throws Exception;

  /**
   * Give a String that contains all the reservation's seating details for this row map.
   *
   * @return String that contains all the reservation's seating details for this row map.
   * */
  String printAllReservations();
}
