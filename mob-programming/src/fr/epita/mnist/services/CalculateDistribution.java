package fr.epita.mnist.services;

import fr.epita.mnist.datamodel.MNISTImage;

import java.util.List;

public class CalculateDistribution  {
    public static int[] calculateDistribution (List<MNISTImage> images) {
        int[] distribution = new int[10];

        for (MNISTImage image : images) {
            distribution[(int) image.getLabel()]++;
        }
        return distribution;
    }
}
