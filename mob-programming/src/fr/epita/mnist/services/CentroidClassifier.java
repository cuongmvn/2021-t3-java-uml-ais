package fr.epita.mnist.services;

import fr.epita.mnist.datamodel.MNISTImage;

import java.util.*;
import java.util.stream.Collectors;

public class CentroidClassifier {
    public Map<Double, MNISTImage> trainCentroid(List<MNISTImage> images) {
        MNISTImageProcessor processor = new MNISTImageProcessor();
        Map<Double, List<MNISTImage>> imagesByLabel = images.stream().collect(Collectors.groupingBy(MNISTImage::getLabel));
        Map<Double, MNISTImage> centroids = new LinkedHashMap<>();

        for (Map.Entry<Double, List<MNISTImage>> entry : imagesByLabel.entrySet()){
            MNISTImage centroid = processor.computeCentroid(entry.getKey(), entry.getValue());
            centroids.put(centroid.getLabel(), centroid);
        }
        return centroids;
    }
}