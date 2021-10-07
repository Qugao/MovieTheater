package com.company;

import java.util.ArrayList;
import java.util.List;

public class RowMapImpl implements RowMap {
  private final int maxRow;
  private final int maxCol;
  private final Row[] rowMap;
  private final List<Reservation> reservations;

  /**
   * Construct a 2 Dimension seating map.
   *
   * @param maxRow Maximum number of row(s) of seating map.
   * @param maxCol Maximum number of column(s) of seating map.
   * */
  public RowMapImpl(int maxRow, int maxCol) throws Exception {
    if (maxRow <= 0 || maxCol <= 0) {
      throw new Exception("Row and columns number can not less or equal to 0.");
    }

    rowMap = new Row[maxRow];
    char identifier = 'A';

    for (int i = 0; i < maxRow; i++) {
      rowMap[i] = new RowImpl(String.valueOf(identifier), maxCol);
      identifier++;
    }

    this.maxCol = maxCol;
    this.maxRow = maxRow;
    reservations = new ArrayList<>();
  }

  @Override
  public boolean takeReservation(Reservation reservation) throws Exception {
    if (reservation == null || reservation.getPartySize() <= 0 ||
        reservation.getReservationNumber() == null || reservation.getReservationNumber().isEmpty()) {
      throw new Exception("Reservation invalid.");
    }
    for (int i = maxRow / 2; i < maxRow; i++) {
      if (rowMap[i].reserve(reservation)) {
        reservations.add(reservation);
        return true;
      }
    }

    for (int i = maxRow / 2; i >= 0; i--) {
      if (rowMap[i].reserve(reservation)) {
        reservations.add(reservation);
        return true;
      }
    }

    return false;
  }

  @Override
  public String printAllReservations() {
    if (reservations.isEmpty()) {
      return "NO RESERVATIONS.";
    }
    StringBuilder sb = new StringBuilder();

    for (Reservation reservation : reservations) {
      sb.append(reservation.getReservationNumber()).append(": ");
      sb.append(reservation.getAllSeats());
      sb.append("\n");
    }

    sb.delete(sb.length() - 1, sb.length());

    return sb.toString();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Row row : rowMap) {
      sb.append(row.toString());
      sb.append("\n");
    }

    sb.delete(sb.length() - 1, sb.length());

    return sb.toString();
  }
}
