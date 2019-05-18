package app;

import java.util.Calendar;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Checks if the today's time is after than the time represented
     * by the argument.
     * @param other Date we want to compare to.
     * @return true if @param is after than today
     */
    public boolean isAfterThan(Date other) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Calendar otherCalendar = Calendar.getInstance();
        otherCalendar.set(other.getYear(), other.getMonth(), other.getDay());
        if (calendar.compareTo(otherCalendar) > 0) return true;
        return false;
    }

    /**
     * Checks if the today's time is before than the time represented
     * by the argument.
     * @param other Date we want to compare to.
     * @return true if @param is before than today
     */
    public boolean isBeforeThan(Date other) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Calendar otherCalendar = Calendar.getInstance();
        otherCalendar.set(other.getYear(), other.getMonth(), other.getDay());
        if (calendar.compareTo(otherCalendar) < 0) return true;
        return false;
    }

    /**
     * Checks if the today's time is equals to the time represented
     * by the argument.
     * @param other Date we want to compare to.
     * @return true if dates are equal.
     */
    public boolean isEqualsWith(Date other) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Calendar otherCalendar = Calendar.getInstance();
        otherCalendar.set(other.getYear(), other.getMonth(), other.getDay());
        if (calendar.compareTo(otherCalendar) == 0) return true;
        return false;
    }

    /**
     * Get today's date from Calendar class
     * @return new Date.
     */
    public static Date getToday() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        return new Date(day, month+1, year);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
