package fr.epita.mnist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.epita.mnist.datamodel.MNISTImage;
import fr.epita.mnist.services.CentroidClassifier;
import fr.epita.mnist.services.MNISTImageProcessor;
import fr.epita.mnist.services.MNISTReader;
import fr.epita.mnist.services.Predict;

public class TestMNISTImageProcessor {


    public static void main(String[] args) throws FileNotFoundException {
        CentroidClassifier classifier = new CentroidClassifier();
        MNISTReader reader = new MNISTReader();
        List<MNISTImage> images = reader.readImages(new File("mob-programming/mnist_train.csv"), 10000);
        List<MNISTImage> test_images = reader.readImages(new File("mob-programming/mnist_test.csv"), 1000);

        Map<Double, MNISTImage> centroids = classifier.trainCentroid(images);

        Map<Double, List<MNISTImage>> imagesByLabel = test_images.stream().collect(Collectors.groupingBy(MNISTImage::getLabel));
        List<MNISTImage> listOfZeros = imagesByLabel.get(0.0);

        Predict predict = new Predict();
        System.out.println("Sample prediction for first 10 value of 0");
        for(int i = 0; i<=9; i++) {
            System.out.print(predict.predict(listOfZeros.get(i), centroids)+ "\t");
        }


    }
}
