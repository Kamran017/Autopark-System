package app;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParkRecordTest {

    Time enterTime, exitTime;
    int result;

    /** Test daily income when regular vehicle exits */
    @Test
    public void calculateDailyIncome() {
        Time enter1 = new Time(10,15);
        Time enter2 = new Time(17,30);
        Time enter3 = new Time(8,45);
        Time exit1 = new Time(10,25); // parked for 10min
        Time exit2 = new Time(18,40); // " for 1:10
        Time exit3 = new Time(7,00); // " for 22:15

        Time[] enters = {enter1, enter2, enter3};
        Time[] exits = {exit1, exit2, exit3};

        double hourlyFee = 1.0; // Assuming hourlyfee is 1.0
        double income, incomeDaily = 0.0;

        int[] dailyIncomes = {0, 1, 22};

        // gets parking durations
        for (int i = 0; i < dailyIncomes.length; i++) {
            enterTime = enters[i];
            exitTime = exits[i];

            if (enterTime.getMinute() > 60) {
                enterTime.setHour(enterTime.getHour() + 1);
                enterTime.setMinute(enterTime.getMinute() - 60);
            }
            if (exitTime.getMinute() > 60) {
                exitTime.setHour(exitTime.getHour() + 1);
                exitTime.setMinute(exitTime.getMinute() - 60);
            }
            int durationInMin = exitTime.getDifference(enterTime);
            if ((exitTime.getHour() * 60 + exitTime.getMinute()) < (enterTime.getHour() * 60 + enterTime.getMinute()))
                durationInMin = 1440 - durationInMin; // 24hrs
            int duration = durationInMin / 60;
            // if duration time is more than 0, pay fee
            if (duration > 0) {
                income = duration * hourlyFee;
                incomeDaily += income;
            }
            assertEquals(dailyIncomes[i], incomeDaily, 0.0001);
            incomeDaily = 0.0; // reset for other tests
        }
    }

    /** Test parking duration minutes */
    @Test
    public void getParkingDuration() {
        int[][] enters = {{1,0}, {9,41},
                {3,00}, {14,20}, {13,00}, {11,61}};
        int[][] exits = {{10,42}, {19,00},
                {1,00}, {15,10}, {9,00}, {9,31}};
        int[] expects = {582, 559, 1320, 50, 1200, 1290};
        for (int i = 0; i < expects.length; i++) {
            enterTime = new Time(enters[i][0], enters[i][1]);
            exitTime = new Time(exits[i][0], exits[i][1]);

            if (enterTime.getMinute() > 60) {
                enterTime.setHour(enterTime.getHour() + 1);
                enterTime.setMinute(enterTime.getMinute() - 60);
            }
            if (exitTime.getMinute() > 60) {
                exitTime.setHour(exitTime.getHour() + 1);
                exitTime.setMinute(exitTime.getMinute() - 60);
            }
            int result = exitTime.getDifference(enterTime);
            if ((exitTime.getHour() * 60 + exitTime.getMinute()) < (enterTime.getHour() * 60 + enterTime.getMinute()))
                result = 1440 - result; // 24hrs

            assertEquals(expects[i], result);
        }
    }

}