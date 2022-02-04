package fr.epita.mnist;

import fr.epita.mnist.datamodel.MNISTImage;
import fr.epita.mnist.services.CalculateDistribution;
import fr.epita.mnist.services.MNISTReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class TestCalculateDistribution {
    public static void main(String[] args) throws FileNotFoundException {

        MNISTReader reader = new MNISTReader();
        List<MNISTImage> images = reader.readImages(new File("mob-programming/mnist_train.csv"), 100);

        int[] distribution = CalculateDistribution.calculateDistribution(images);
        for(int i = 0; i < distribution.length; i++) {
            System.out.println(" Number "+ i +": "+ distribution[i]);
        }
    }
}
