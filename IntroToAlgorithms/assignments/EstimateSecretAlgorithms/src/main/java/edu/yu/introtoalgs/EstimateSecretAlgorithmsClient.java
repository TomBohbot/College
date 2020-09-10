package edu.yu.introtoalgs;

/**
 * EstimateSecretAlgorithmsClient
 * Assignment #2
 * @author Tom Bohbot
 * @version September 6, 2020
 */

public class EstimateSecretAlgorithmsClient {

    private int prevOne;
    private int prevTwo;
    private int prevThree;
    private int prevFour;

    private static double doubleAlgorithmOne (int n) {
        SecretAlgorithm1 algOne = new SecretAlgorithm1 ();
        algOne.setup(n);
        Stopwatch stopwatch = new Stopwatch();
        algOne.execute();
        return stopwatch.elapsedTime();
    }

    private static double doubleAlgorithmTwo (int n) {
        SecretAlgorithm2 algOne = new SecretAlgorithm2 ();
        algOne.setup(n);
        Stopwatch stopwatch = new Stopwatch();
        algOne.execute();
        return stopwatch.elapsedTime();
    }

    private static double doubleAlgorithmThree (int n) {
        SecretAlgorithm3 algOne = new SecretAlgorithm3 ();
        algOne.setup(n);
        Stopwatch stopwatch = new Stopwatch();
        algOne.execute();
        return stopwatch.elapsedTime();
    }

    private static double doubleAlgorithmFour (int n) {
        SecretAlgorithm4 algOne = new SecretAlgorithm4 ();
        algOne.setup(n);
        Stopwatch stopwatch = new Stopwatch();
        algOne.execute();
        return stopwatch.elapsedTime();
    }

    private static class Stopwatch {

        private final long start;

        private Stopwatch() {
            start = System.currentTimeMillis();
        }
    
        private double elapsedTime() {
            long now = System.currentTimeMillis();
            return (now - start) / 1000.0;
        }    
    } 


    public static void main (final String [] args) {
        EstimateSecretAlgorithmsClient obj = new EstimateSecretAlgorithmsClient();
        System.out.println("Algorithm 1:");
        for (int i = 1; i <= 8192; i *= 2) { 
            System.out.println("time: " + obj.doubleAlgorithmOne(i) + "      " + "n: " + i); 
        }
        System.out.println("Algorithm 2:");
        for (int i = 256; i <= 2684354568L; i *= 2) {
            System.out.println("time: " + obj.doubleAlgorithmTwo(i) + "      " + "n: " + i); 
        }     
        System.out.println("Algorithm 3:");
        for (int i = 1; i <= 524288; i *= 2) {
            System.out.println("time: " + obj.doubleAlgorithmThree(i) + "      " + "n: " + i); 
        }
        System.out.println("Algorithm 4:");
        for (int i = 1; i <= 134217728; i = i * 2) {
            System.out.println("time: " + obj.doubleAlgorithmFour(i) + "      " + "n: " + i); 
        }
    }
    
}