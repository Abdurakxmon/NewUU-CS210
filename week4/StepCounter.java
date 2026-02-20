public class StepCounter {

    private int individualSteps = 0;
    static int totalStepsAllUsers = 0;

    public void walk(int stepCount) {
        individualSteps += stepCount;
        totalStepsAllUsers += stepCount;
    }

    public int getIndividualSteps() {
        return individualSteps;
    }

    public static void resetGlobalSteps() {
        totalStepsAllUsers = 0;
    }
}