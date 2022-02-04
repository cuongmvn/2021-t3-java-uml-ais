package fr.epita.mnist.services;

import fr.epita.mnist.datamodel.MNISTImage;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PredictImproved extends Predict {
    public int predict(MNISTImage input, Map<Double, MNISTImage> centroids) {
        MNISTImageProcessor processor = new MNISTImageProcessor();
        double[] predict = new double[10];
        for (double i = 0 ; i <= 9 ; i++) {
            predict[(int) i] = processor.computeDeviation(input, centroids.get(i));
        }
        double min = Arrays.stream(predict).min().orElseThrow();
        return Arrays.stream(predict).boxed().collect(Collectors.toList()).indexOf(min);
    }
}
