package com.example.junit5;

import com.company.Reservation;
import com.company.ReservationImpl;
import com.company.RowMap;
import com.company.RowMapImpl;
//import org.junit.jupiter.api.Test;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class RowMapImplTest {

  RowMap rowMap;
  Reservation validReservation1 = new ReservationImpl("R001", 2);
  Reservation validReservation2 = new ReservationImpl("R002", 3);
  Reservation validReservation3 = new ReservationImpl("R003", 1);
  Reservation validReservation4 = new ReservationImpl("R004", 4);

  @Test
  public void validConstruct() throws Exception {
    rowMap = new RowMapImpl(2, 2);
    assertEquals("A: [*, *]\n" + "B: [*, *]", rowMap.toString());
    assertEquals("NO RESERVATIONS.", rowMap.printAllReservations());
  }

  @Test
  public void setRowMapWithWrongValue() {
    Exception zeroPos = assertThrows(Exception.class, () -> {
      rowMap = new RowMapImpl(0, 10);
    });

    Exception posZero = assertThrows(Exception.class, () -> {
      rowMap = new RowMapImpl(10, 0);
    });

    Exception negPos = assertThrows(Exception.class, () -> {
      rowMap = new RowMapImpl(-10, 10);
    });

    Exception posNeg = assertThrows(Exception.class, () -> {
      rowMap = new RowMapImpl(10, -10);
    });

    Exception zeroZero = assertThrows(Exception.class, () -> {
      rowMap = new RowMapImpl(0, 0);
    });

    Exception negNeg = assertThrows(Exception.class, () -> {
      rowMap = new RowMapImpl(-10, -10);
    });
  }

  @Test
  public void takeValidReservation() throws Exception {
    rowMap = new RowMapImpl(10, 20);

    rowMap.takeReservation(validReservation1);
    assertEquals("R001: F11, F12", rowMap.printAllReservations());
  }

  @Test
  public void takeMultipleValidReservation() throws Exception {
    rowMap = new RowMapImpl(10, 20);

    rowMap.takeReservation(validReservation1);
    rowMap.takeReservation(validReservation2);
    rowMap.takeReservation(validReservation3);
    rowMap.takeReservation(validReservation4);

    assertEquals("R001: F11, F12\n" + "R002: F16, F17, F18\n" + "R003: F7\n" + "R004: G11, G12, G13, G14", rowMap.printAllReservations());
  }

  @Test
  public void takeMaximumReservation() throws Exception {
    rowMap = new RowMapImpl(2, 5);

    rowMap.takeReservation(validReservation2);
    rowMap.takeReservation(validReservation3);
    rowMap.takeReservation(validReservation4);

    assertEquals("R002: B3, B4, B5\n" + "R003: A3", rowMap.printAllReservations());
  }

  @Test public void takeReservationsWith1Person() throws Exception {
    rowMap = new RowMapImpl(2, 10);

    for (int i = 1; i <= 10; i++) {
      Reservation newReservation = new ReservationImpl("R00"+i, 1);
      rowMap.takeReservation(newReservation);
    }

    assertEquals("R001: B6\n" + "R002: B10\n" + "R003: B2\n" + "R004: A6\n" + "R005: A10\n" + "R006: A2", rowMap.printAllReservations());
    ;
  }


  @Test public void testPrintAllReservation() throws Exception {
    rowMap = new RowMapImpl(3, 11);

    int index = 1;

    for (int i = 1; i <= 3; i++) {
      Reservation newReservation = new ReservationImpl("R00"+ index++, 2);
      rowMap.takeReservation(newReservation);
    }

    for (int i = 1; i <= 1; i++) {
      Reservation newReservation = new ReservationImpl("R00"+ index++, 3);
      rowMap.takeReservation(newReservation);
    }

    for (int i = 1; i <= 1; i++) {
      Reservation newReservation = new ReservationImpl("R00"+ index++, 2);
      rowMap.takeReservation(newReservation);
    }

    for (int i = 1; i <= 3; i++) {
      Reservation newReservation = new ReservationImpl("R00"+ index++, 1);
      rowMap.takeReservation(newReservation);
    }
//    Seating map:
//        1     2     3  4  5  6     7     8     9 10    11
//    A: [*,    R008, *, *, *, R004, R004, R004, *, *,    *]
//    B: [R002, R002, *, *, *, R001, R001,    *, *, *, R006]
//    C: [R005, R005, *, *, *, R003, R003,    *, *, *, R007]

    assertEquals("R001: B6, B7\n" + "R002: B1, B2\n" + "R003: C6, C7\n" +
        "R004: A6, A7, A8\n" + "R005: C1, C2\n" + "R006: B11\n" + "R007: C11\n" + "R008: A2",
        rowMap.printAllReservations());
  }

  @Test
  public void takeInvalidReservation() throws Exception {
    rowMap = new RowMapImpl(10,10);

    Exception nullPos = assertThrows(Exception.class, () -> {
      rowMap.takeReservation(new ReservationImpl(null, 1));
    });

    Exception null1 = assertThrows(Exception.class, () -> {
      rowMap.takeReservation(null);
    });

    Exception emptyPos = assertThrows(Exception.class, () -> {
      rowMap.takeReservation(new ReservationImpl("", 1));
    });

    Exception normalZero = assertThrows(Exception.class, () -> {
      rowMap.takeReservation(new ReservationImpl("R001", 0));
    });

    Exception normalNeg = assertThrows(Exception.class, () -> {
      rowMap.takeReservation(new ReservationImpl("R001", -1));
    });
  }

  @Test
  public void takeOversizeParty() throws Exception {
    rowMap = new RowMapImpl(10,10);
    rowMap.takeReservation(new ReservationImpl("OVERSIZE", 11));
    assertEquals("NO RESERVATIONS.", rowMap.printAllReservations());
  }
}