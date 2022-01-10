package fr.epita.titanic.tests;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import fr.epita.titanic.services.GenericCSVReader;

public class TestCSVReading {

    public static final String DELIM = ",";
    public static final String ESCAPE_CHAR = "\"";

    public static void main(String[] args) throws Exception {
        givenCSVLineIsRegular_ThenWeShouldBeAbleToExtractPassenger();
        givenAnEscapedColumnContainingDelim_thenWeShouldBeAbleToExtractPassenger();
        givenTheTestFile_ThenTheCSVReaderShouldExtractAllTheLines();

    }


//    private void old() throws Exception {
//
//
//
//        //then for train
//        lines = Files.readAllLines(Path.of("S:\\Work\\ae\\Epita\\workspaces\\2021-t3-java-uml-ais\\titanic-training\\train.csv"));
//
//        for (String line : lines){
//            try {
//                System.out.println(csvReader.extractColumnValues(line));
//            }catch (Exception e){
//                errorLines.add(line);
//            }
//        }
//
//        //then
//        System.out.println("errors: " +errorLines);
//    }

    private static void givenTheTestFile_ThenTheCSVReaderShouldExtractAllTheLines() throws Exception{
        //given
        List<String> lines = Files.readAllLines(Path.of("./titanic-training/test.csv"));
        List<String> errorLines = new ArrayList<>();

        //when
        GenericCSVReader csvReader = new GenericCSVReader(DELIM, ESCAPE_CHAR);
        for (String line : lines){
            try {
               csvReader.extractColumnValues(line);
            }catch (Exception e){
                errorLines.add(line);
            }
        }
        //then for test
        if (lines.size() != 419){
            throw new Exception("the count was not as expected, got : " + lines.size() + "expected 419");
        }else{
            System.out.println("givenTheTestFile_ThenTheCSVReaderShouldExtractAllTheLines : successfully read and extracted file");
        }
    }


    private static void givenCSVLineIsRegular_ThenWeShouldBeAbleToExtractPassenger() throws Exception {
        //given - start state / hypothesis
        String line = "62,1,1,\"Icard Miss. Amelie\",female,38,0,0,113572,80,B28,";


        //when
        GenericCSVReader csvReader = new GenericCSVReader(",","\"");
        List<String> strings = csvReader.extractColumnValues(line);


        //then
        if (strings.size() != 12){
            throw new Exception("expected size : 12, got "+ strings.size());
        }else{
            System.out.println("givenCSVLineIsRegular_ThenWeShouldBeAbleToExtractPassenger : success");
        }

    }

    private static void givenAnEscapedColumnContainingDelim_thenWeShouldBeAbleToExtractPassenger() throws Exception {
        //given - start state / hypothesis
        String line = "62,1,1,\"Icard, Miss. Amelie\",female,38,0,0,113572,80,B28,";


        //when
        GenericCSVReader csvReader = new GenericCSVReader(",","\"");
        List<String> strings = csvReader.extractColumnValues(line);


        //then
        if (strings.size() != 12){
            throw new Exception("expected size : 12, got "+ strings.size());
        }else{
            System.out.println("givenAnEscapedColumnContainingDelim_thenWeShouldBeAbleToExtractPassenger: success");
        }

    }

}
