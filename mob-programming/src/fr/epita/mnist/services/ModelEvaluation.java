package fr.epita.mnist.services;

import fr.epita.mnist.datamodel.MNISTImage;

import java.util.List;
import java.util.Map;

public class ModelEvaluation {
    public static void evaluate(List<MNISTImage> images, Map<Double, MNISTImage> centroids, Predict predictMethod){
        ModelEvaluation E= new ModelEvaluation();
        int[][] confusionMatrix = E.calculateConfusionMatrix(images, centroids, predictMethod);
        displayConfusionMatrix(confusionMatrix);
        calculateAccuracy(confusionMatrix);
    }
    private int[][] calculateConfusionMatrix(List<MNISTImage> images, Map<Double, MNISTImage> centroids, Predict predictMethod){
        int[][] matrix = new int[10][10];
        for(MNISTImage image : images) {
            matrix[(int) image.getLabel()][predictMethod.predict(image, centroids)]++;
        }
        return matrix;
    }

    private static void displayConfusionMatrix(int[][] matrix) {
        System.out.println("True label|     Prediction of the model:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Number "+ i + "  | ");
            for (int j = 0; j < 10; j++) {
                System.out.format("%5s", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void calculateAccuracy(int[][] matrix) {
        int TP = 0;
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sum = sum + matrix[i][j];
            }
            TP = TP + matrix[i][i];
        }
        System.out.println(" Model Accuracy: "+ (double) TP/sum*100 +"%");
    }

}
