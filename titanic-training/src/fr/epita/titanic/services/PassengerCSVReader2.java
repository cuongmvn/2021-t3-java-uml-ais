package fr.epita.titanic.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import fr.epita.titanic.datamodel.Passenger;

public class PassengerCSVReader2 {


    public List<Passenger> readPassengers(File file) throws IOException {

        //given
        List<String> lines = Files.readAllLines(file.toPath());
        List<String> errorLines = new ArrayList<>();

        //when
        GenericCSVReader csvReader = new GenericCSVReader(",","\"");
        lines.remove(0);
        List<Passenger> passengers = new ArrayList<>();
        for (String line : lines){
            try {
                List<String> strings = csvReader.extractColumnValues(checkForNull(line));
                Passenger instance = new Passenger();
                instance.setPassengerId(Integer.parseInt(strings.get(0)));
                instance.setpClass(Integer.parseInt(strings.get(1)));
                instance.setName(strings.get(2));
                instance.setSex(strings.get(3));
                String s = strings.get(4);
                instance.setAge(Double.parseDouble(checkForNull(s)));
                passengers.add(instance);
                //to be completed
            }catch (Exception e){
                errorLines.add(checkForNull(line));
            }
        }

        return passengers;


    }

    private String checkForNull(String s) {
        if (s == null){
            return "";
        }
        return s;
    }

    private String checkForNullNumber(String s) {
        if (s == null){
            return "0";
        }
        return s;
    }


}
