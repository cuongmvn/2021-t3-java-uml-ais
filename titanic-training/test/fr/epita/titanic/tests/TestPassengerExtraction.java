package fr.epita.titanic.tests;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.epita.titanic.datamodel.Passenger;
import fr.epita.titanic.services.PassengerCSVReader;
import fr.epita.titanic.services.PassengerCSVReader2;

public class TestPassengerExtraction {

    public static void main(String[] args) throws IOException {

        givenTestFile_thenWeShouldHaveAValidListOfPassengers();



    }

    private static void givenTestFile_thenWeShouldHaveAValidListOfPassengers() throws IOException {
        File file = new File("./titanic-training/test.csv");
        PassengerCSVReader2 reader = new PassengerCSVReader2();
        List<Passenger> passengers = reader.readPassengers(file);

        System.out.println(passengers);

    }

    private static void computeStatisticalFacts() {
        PassengerCSVReader reader = new PassengerCSVReader();
        List<Passenger> passengers = reader.read(new File("S:\\Work\\ae\\Epita\\workspaces\\2021-t3-java-uml-ais\\titanic-training\\test.csv"));

        Double totalAge = 0.0;
        for(Passenger passenger : passengers){
            totalAge += passenger.getAge();
        }
        double averageAge = totalAge / passengers.size();
        System.out.println(averageAge);
    }
}
