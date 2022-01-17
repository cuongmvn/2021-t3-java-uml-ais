package fr.epita.titanic.data.exploration;


import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.epita.titanic.datamodel.Passenger;
import fr.epita.titanic.services.PassengerCSVReader2;

/**
 * main class to extract some statistical info about the dataset
 */
public class Launcher {

    public static void main(String[] args) throws IOException {

        //load the train dataset
        PassengerCSVReader2 passengerCSVReader2 = new PassengerCSVReader2();
        List<Passenger> passengers = passengerCSVReader2.readPassengers(new File("./titanic-training/train.csv"), PassengerCSVReader2.functionForTrain);
//
//        int counterSurvived = 0;
//        int counterNotSurvived = 0;
//        for (Passenger passenger : passengers){
//            if (passenger.getSurvived() == 0){
//                counterNotSurvived ++;
//            }else if (passenger.getSurvived() == 1){
//                counterSurvived ++;
//            }
//        }

        Map<Integer, Integer> countingMap = countSurvival(passengers);


        System.out.println(countingMap);

        //calculating the chance to not survive
        System.out.println("raw survival distribution :" + computeSurvivalStatePercentage(passengers, countingMap) +"%");


        List<Passenger> males = passengers.stream().filter(p -> p.getSex().equals("male")).collect(Collectors.toList());

        System.out.println("male survival :" + countSurvival(males));


        List<Passenger> females = passengers.stream().filter(p -> p.getSex().equals("female")).collect(Collectors.toList());

        Map<Integer, Integer> survivalCountFemale = countSurvival(females);
        System.out.println("female survival :" + survivalCountFemale);
        System.out.println("survived state: " + computeSurvivalStatePercentage(females, survivalCountFemale) + "%");


        //compute centroid for survived
        List<Passenger> survivedPassengers = passengers.stream().filter(p -> p.getSurvived()==1).collect(Collectors.toList());
        Passenger survivedCentroid = new Passenger();

        for(Passenger passenger:survivedPassengers){
            survivedCentroid.setAge(survivedCentroid.getAge() + passenger.getAge());
            survivedCentroid.setpClass(survivedCentroid.getpClass() + passenger.getpClass());
        }
        survivedCentroid.setAge(survivedCentroid.getAge() / survivedPassengers.size());


        //fix that using a array of double  survivedCentroid.setpClass(Double.valueOf(survivedCentroid.getpClass()) / survivedPassengers.size());

        System.out.println(survivedCentroid);




    }

    private static double computeSurvivalStatePercentage(List<Passenger> passengers, Map<Integer, Integer> countingMap) {
        Integer survived = countingMap.get(1);
        double ratio = Double.valueOf(survived) / passengers.size();
        return ratio * 100;
    }

    private static Map<Integer, Integer> countSurvival(List<Passenger> passengers) {
        Map<Integer, Integer> countingMap = new LinkedHashMap<>();
        for (Passenger passenger : passengers){
            Integer count = countingMap.get(passenger.getSurvived());
            if (count == null){
                count = 1;
            } else{
                count += 1;
            }
            countingMap.put(passenger.getSurvived(), count);
        }
        return countingMap;
    }
}
