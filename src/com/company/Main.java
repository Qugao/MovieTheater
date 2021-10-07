package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runners.AllTests;


public class Main {

    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public static void main(String[] args) throws Exception {
//        JUnitCore.main("com.example.junit5.RowMapImplTest");

        RowMap rowMap = new RowMapImpl(10, 20);
        List<Reservation> failedReservation = new ArrayList<>();
        List<String> errorLog = new ArrayList<>();
        try {
            Scanner scn = new Scanner(new File(args[0]));
            while (scn.hasNextLine()) {
                String[] inputs = scn.nextLine().split(" ");

                if (!isNumeric(inputs[1])) {
                    errorLog.add("Invalid input for reservation " + inputs[0]);
                    continue;
                }

                Reservation reservation = new ReservationImpl(inputs[0], Integer.parseInt(inputs[1]));

                if (!rowMap.takeReservation(reservation)) {
                    failedReservation.add(reservation);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write(rowMap.printAllReservations());

            if (!failedReservation.isEmpty()) {
                myWriter.write("\nFailed reservation: ");
                myWriter.write(failedReservation.toString());
            }

            if (!errorLog.isEmpty()) {
                myWriter.write("\nError: ");
                myWriter.write(errorLog.toString());
            }

            System.out.println("Output saved as output.txt");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}


