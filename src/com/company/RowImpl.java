package com.company;

import java.util.*;

public class RowImpl implements Row {
  private String identifier;
  private int size;
  private Reservation[] seats;

  private int rightSideLastIndex;
  private int leftSideLastIndex;

  /**
   * Construct a row.
   *
   * @param identifier the row identifier for this row.
   * @param size how many seats this row have.
   * */
  public RowImpl(String identifier, int size) {
    this.identifier = identifier;
    this.size = size;
    this.seats = new Reservation[size];
    rightSideLastIndex = size / 2;
    leftSideLastIndex = size / 2 - 4;

    Arrays.fill(seats, new EmptyReservation());
  }


  @Override
  public boolean reserve(Reservation reservation) {
    int partySize = reservation.getPartySize();

    // Start from right side, check if there's enough room for all the people
    if (rightSideLastIndex < seats.length && rightSideLastIndex + partySize <= seats.length || (rightSideLastIndex == seats.length - 1 && partySize == 1 && seats[seats.length - 2].getReservationNumber().equals("*"))) {

      // Assign seats.
      while (rightSideLastIndex < seats.length && partySize > 0) {
        seats[rightSideLastIndex] = reservation;
        // Put current seat to reservation's seating record.
        reservation.assignSeat(identifier, rightSideLastIndex + 1);
        rightSideLastIndex++;
        partySize--;
      }

      // Leave three empty seats to the right.
      int safetyBuffer = 3;

      while (rightSideLastIndex < seats.length && safetyBuffer > 0) {
        seats[rightSideLastIndex++] = new EmptyReservation();
        safetyBuffer--;
      }

      return true;

      // if right side don't have the room check the left side.
    } else if ((leftSideLastIndex >= 0 && leftSideLastIndex - partySize + 1 >= 0) || (leftSideLastIndex == 0 && partySize == 1 && seats[1].getReservationNumber().equals("*"))) {

      while (leftSideLastIndex >= 0 && partySize > 0) {
        seats[leftSideLastIndex] = reservation;
        reservation.assignSeat(identifier, leftSideLastIndex + 1);
        leftSideLastIndex--;
        partySize--;
      }

      int safetyBuffer = 3;

      while (leftSideLastIndex >= 0 && safetyBuffer > 0) {
        seats[leftSideLastIndex--] = new EmptyReservation();
        safetyBuffer--;
      }

      return true;

      // if failed above conditions but pointers didn't change at all, then means the party size is larger
      // than half of total seats. Then assign seats from the beginning.
    } else if (partySize < size && rightSideLastIndex == size / 2 && leftSideLastIndex == size / 2 - 4){
      // Assign seats from the beginning.
      rightSideLastIndex = 0;

      while (rightSideLastIndex < partySize) {
        seats[rightSideLastIndex++] = reservation;
        reservation.assignSeat(identifier, rightSideLastIndex + 1);
      }

      int safetyBuffer = 3;

      while (rightSideLastIndex < seats.length && safetyBuffer > 0) {
        seats[rightSideLastIndex++] = new EmptyReservation();
        safetyBuffer--;
      }

      // set left pointers.
      leftSideLastIndex = 0;

      return true;
    }

    // The party size exceed available seats of this row.
    return false;
  }


  public String toString() {
    return this.identifier + ": " + Arrays.toString(seats);
  }

}
