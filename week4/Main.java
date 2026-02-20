import java.util.Random;


import java.util.*;


public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        LibraryBook book1 = new LibraryBook("Test 1", "Someone", 200);
        LibraryBook book2 = new LibraryBook("Test 2 not valid", "No one", -20);
        System.out.println(book1.getPages());
        System.out.println(book2.getPages());


        Sensor[] sensors = new Sensor[5];
        for (int i = 0; i < sensors.length; i++) {
            double randomReading = -50 + (100 * random.nextDouble());
            sensors[i] = new Sensor("Location " + (i + 1), randomReading);
        }
        double average = Sensor.calculateAverage(sensors);
        System.out.println("Average reading: " + average);

        StepCounter userA = new StepCounter();
        StepCounter userB = new StepCounter();
        userA.walk(20);
        userB.walk(24);
        System.out.println(userA.getIndividualSteps());
        System.out.println(userB.getIndividualSteps());
        System.out.println(StepCounter.totalStepsAllUsers);
        StepCounter.resetGlobalSteps();
        System.out.println(StepCounter.totalStepsAllUsers);

        int iterations = 1000000;
        int n = 20;
        long recursiveAns = 0;
        long iterativeAns = 0;
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        for (int i = 0; i < iterations; i++) {
            recursiveAns = MathTool.factorialRecursive(n);
        }
        stopWatch.stop();
        System.out.println("Recursive Answer: " + recursiveAns + ", Time (ms): " + stopWatch.getElapsedTime() / 1000000.0);

        stopWatch.start();
        for (int i = 0; i < iterations; i++) {
            iterativeAns = MathTool.factorialIterative(n);
        }
        stopWatch.stop();
        System.out.println("Iterative Answer: " + iterativeAns + ", Time (ms): " + stopWatch.getElapsedTime() / 1000000.0);

    }


}
