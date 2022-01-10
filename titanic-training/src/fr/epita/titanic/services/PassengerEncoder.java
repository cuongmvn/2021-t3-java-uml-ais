package fr.epita.titanic.services;

import java.util.LinkedHashMap;
import java.util.Map;

import fr.epita.titanic.datamodel.Passenger;

public class PassengerEncoder {

    Map<String, Double> sexEncodedValues = new LinkedHashMap<>();

    public Map<String, Double> getSexEncodedValues() {
        return sexEncodedValues;
    }

    public void setSexEncodedValues(Map<String, Double> sexEncodedValues) {
        this.sexEncodedValues = sexEncodedValues;
    }

    public double[] encode(Passenger passenger){
        double[] vector = new double[5];
        vector[0] = passenger.getPassengerId();
        vector[1] = passenger.getpClass();
        Double encodedValue = sexEncodedValues.get(passenger.getSex());
        if (encodedValue == null){
            encodedValue = (double) (sexEncodedValues.size() + 1);
            sexEncodedValues.put(passenger.getSex(), encodedValue);
        }
        vector[2] = encodedValue;
        vector[3] = passenger.getAge();
        return vector;
    }


    public Passenger decode(double[] vector){
        return null;
    }


}
