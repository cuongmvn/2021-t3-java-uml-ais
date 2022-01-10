package fr.epita.titanic.tests;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import fr.epita.titanic.datamodel.Passenger;
import fr.epita.titanic.services.PassengerCSVReader;
import fr.epita.titanic.services.PassengerCSVReader2;
import fr.epita.titanic.services.PassengerEncoder;

public class TestPassengerEncoding {

    public static void main(String[] args) throws IOException {

        givenTestFile_thenWeShouldHaveAValidListOfPassengersAndEncodeThem();



    }

    private static void givenTestFile_thenWeShouldHaveAValidListOfPassengersAndEncodeThem() throws IOException {
        File file = new File("./titanic-training/test.csv");
        PassengerCSVReader2 reader = new PassengerCSVReader2();
        List<Passenger> passengers = reader.readPassengers(file);

        PassengerEncoder passengerEncoder = new PassengerEncoder();
        LinkedHashMap<String, Double> sexEncodedValues = new LinkedHashMap<>();
        sexEncodedValues.put("male", 0d);
        sexEncodedValues.put("female",1d);

        passengerEncoder.setSexEncodedValues(sexEncodedValues);
        for (Passenger passenger :passengers){
            double[] encoded = passengerEncoder.encode(passenger);
            System.out.print("[");
            for (double dbl : encoded){
                System.out.print(dbl +" ");
            }
            System.out.println("]");
        }

        System.out.println(passengerEncoder.getSexEncodedValues());
    }

}
