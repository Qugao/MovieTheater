package com.company;

import java.util.*;

public class ReservationImpl implements Reservation {
  private final String reservationNumber;
  private final int partySize;
  private final Map<String, List<Integer>> seatMap;

  /**
   * Construct a new reservation.
   *
   * @param reservationNumber the reservation identifier for this reservation.
   * @param partySize how many people are in this reservation.
   * */
  public ReservationImpl(String reservationNumber, int partySize) {
    this.reservationNumber = reservationNumber;
    this.partySize = partySize;
    seatMap = new HashMap<>();
  }

  @Override
  public void assignSeat(String row, int seatNumber) {
    if (!seatMap.containsKey(row)) {
      seatMap.put(row, new ArrayList<>());
    }

    seatMap.get(row).add(seatNumber);
  }

  @Override
  public int getPartySize() {
    return this.partySize;
  }

  @Override
  public String getReservationNumber() {
    return this.reservationNumber;
  }

  @Override
  public String getAllSeats() {
    StringBuilder sb = new StringBuilder();

    for (Map.Entry<String, List<Integer>> e : seatMap.entrySet()) {
      Collections.sort(e.getValue());

      for (int seatNumber : e.getValue()) {
        sb.append(e.getKey()).append(seatNumber).append(", ");
      }

      sb.delete(sb.length() - 2, sb.length());
      sb.append("\n");
    }

    if (sb.length() > 0) {
      sb.delete(sb.length() - 1, sb.length());
    }

    return sb.toString();
  }

  public String toString() {
    return reservationNumber;
  }
}
