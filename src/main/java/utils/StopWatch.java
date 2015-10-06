package utils;

public class StopWatch {
    private long start;

    public void start() {
        this.start = System.nanoTime();
    }
    public long stop() {
        long now = System.nanoTime();
        long timeElapsed = now - start;
        System.out.println("time elapsed: " + timeElapsed);
        return timeElapsed;
    }
}
