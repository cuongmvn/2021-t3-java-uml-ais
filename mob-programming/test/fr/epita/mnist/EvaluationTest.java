package fr.epita.mnist;

import fr.epita.mnist.datamodel.MNISTImage;
import fr.epita.mnist.services.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class EvaluationTest {
    public static final File train_file = new File("mob-programming/mnist_train.csv");
    public static final File test_file = new File("mob-programming/mnist_test.csv");
    static int READLINE = 50000;
    public static void main(String[] args) throws FileNotFoundException {

        MNISTReader reader = new MNISTReader();
        List<MNISTImage> images = reader.readImages(train_file, READLINE);
        List<MNISTImage> images_test = reader.readImages(test_file, 10000);

        CentroidClassifier classifier = new CentroidClassifier();

        Map<Double, MNISTImage> centroids = classifier.trainCentroid(images);

        Predict predict = new Predict();
        Predict predictImproved = new PredictImproved();

        ModelEvaluation evaluation = new ModelEvaluation();

        evaluation.evaluate(images_test, centroids, predict);
        evaluation.evaluate(images_test, centroids, predictImproved);
    }

}
