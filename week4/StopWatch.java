public class StopWatch {
    private long startTime;
    private long stopTime;
    public StopWatch() {
        startTime = System.nanoTime();
    }
    public void start() {
        startTime = System.nanoTime();

    }
    public void stop() {
        stopTime = System.nanoTime();
    }
    public long getElapsedTime() {
        return stopTime - startTime;
    }
}
