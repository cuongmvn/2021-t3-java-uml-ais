package fr.epita.mnist;

import fr.epita.mnist.datamodel.MNISTImage;
import fr.epita.mnist.services.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CentroidClassifierTest {
    public static void main(String[] args) throws FileNotFoundException {
        MNISTImageProcessor processor = new MNISTImageProcessor();
        CentroidClassifier classifier = new CentroidClassifier();
        MNISTReader reader = new MNISTReader();
        List<MNISTImage> images = reader.readImages(new File("mob-programming/mnist_test.csv"), 1000);

        int[] distribution = CalculateDistribution.calculateDistribution(images);
        for(int i = 0; i < distribution.length; i++) {
            System.out.println(" Number "+ i +": "+ distribution[i]);
        }

        Map<Double, List<MNISTImage>> imagesByLabel = images.stream().collect(Collectors.groupingBy(MNISTImage::getLabel));

        List<MNISTImage> listOfOnes = imagesByLabel.get(1.0);
        List<MNISTImage> listOfZeros = imagesByLabel.get(0.0);
        MNISTImage centroidFor1 = processor.computeCentroid(1.0, listOfOnes);

        Map<Double, MNISTImage> centroids = classifier.trainCentroid(images);

        for(double i = 0; i<=9.0; i++){
            MNISTImageProcessor.displayImage(centroids.get(i));
        }
        Predict prediction = new Predict();
        processor.displayImage(listOfZeros.get(1));
        System.out.println("  "+ prediction.predict(listOfZeros.get(1), centroids));
    }
}
