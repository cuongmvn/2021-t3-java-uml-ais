package fr.epita.mnist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.epita.mnist.datamodel.MNISTImage;
import fr.epita.mnist.services.MNISTReader;

public class TestMNISTReader {


    public static void main(String[] args) throws Exception {
        MNISTReader reader = new MNISTReader();
        List<MNISTImage> images = reader.readImages(new File("./mob-programming/mnist_test.csv"), 100);


        Map<Double, List<MNISTImage>> imagesByLabel = images.stream().collect(Collectors.groupingBy(MNISTImage::getLabel));


        imagesByLabel.forEach((label, imageList) -> {
            System.out.println(label + ":" +imageList.size());
        });

        if ( !(images.get(0).getLabel() == 7)){
            throw new Exception("verification exception, expected 7 and got: "+ images.get(0).getLabel());
        };

        displayImage(images.get(0));
    }

    private static void displayImage (MNISTImage image){
        double[][] pixels = image.getPixels();
        for (int i = 0; i < pixels.length; i ++){
            for (int j = 0; j < pixels[i].length; j++){
                if (pixels[i][j] < 128){
                    System.out.print("..");
                }else{
                    System.out.print("xx");
                }
            }
            System.out.println();
        }

    }

}
