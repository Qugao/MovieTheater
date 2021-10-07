package com.company;

/**
 * An empty reservation holder, act as safety buffer.
 * */
public class EmptyReservation implements Reservation{

  public EmptyReservation() {
  }

  @Override public void assignSeat(String row, int seatNumber) {

  }

  @Override public int getPartySize() {
    return 0;
  }

  @Override public String getReservationNumber() {
    return "*";
  }

  @Override public String getAllSeats() {
    return null;
  }

  public String toString() {
    return "*";
  }
}
