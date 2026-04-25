/**
 * Represents a time management system that tracks current time, elapsed time, and enforces time boundaries.
 * Time is internally stored and manipulated in minutes, with support for 24-hour format display.
 * This class is useful for games or applications that need to track time progression and check against deadlines.
 */
public class Time {

    private int time;
    private int elapsedTime;
    private final int endTime;

    public Time(int time, int endHour) {
        this.time = time;
        this.elapsedTime = 0;
        this.endTime = endHour;
    }

    public void addMinute(int minutes) {
        this.time += minutes;
        elapsedTime += minutes;
    }

    public int getCurrentTime() {
        return time;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public boolean isTimeUp() {
        int currentHour = (time / 60) % 24;
        int endHour = endTime % 24;

        if (endHour < 22) {
            return currentHour >= endHour && currentHour < 22;
        }
        return false;
    }

    public String getFormattedTime() {
        int totalMinutes = time % 1440;
        int hours = (totalMinutes / 60) % 24;
        int minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
