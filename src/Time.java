public class Time {

    private int time;
    private int elapsedTime;
    private int endTime;

    public Time(int time, int endHour) {
        this.time = time;
        this.elapsedTime = 0;
        this.endTime = endHour;
    }

    public void addMinute(int time) {
        this.time += time;
        elapsedTime += time;
    }
}
