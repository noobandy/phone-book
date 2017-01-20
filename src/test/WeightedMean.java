package test;
import java.util.Scanner;

public class WeightedMean {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        
        int sampleSize = scanner.nextInt();
        int[] samples = new int[sampleSize];
        
        for(int i = 0; i < sampleSize; i++) {
            samples[i] = scanner.nextInt();
        }
        
        int weightedSum = 0;
        int totalWeight = 0;
        
        for(int i = 0; i < sampleSize; i++) {
            int weight = scanner.nextInt();
            
            totalWeight += weight;
            weightedSum += (samples[i] * weight);
        }
        
        System.out.format("%.1f", (double) weightedSum / totalWeight);
    }
}