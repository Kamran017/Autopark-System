package app;

import org.junit.Test;
import static org.junit.Assert.*;

public class SubscriptionTest {

    Date begin, end;
    Subscription subscription;

    /** Test subscription date's validness */
    @Test
    public void isValid() {
        int[][] begins = {{13,5,2019},
                {13,4,2019}, {15,6,2000}, {14,5,2019}};
        int[][] ends = {{18,5,2019},
                {10,5,2019}, {15, 5, 2001}, {14,5,2019}};
        boolean[] expects = {true,false,false,false};
        for (int i = 0; i < expects.length; i++) {
            begin = new Date(begins[i][0], begins[i][1], begins[i][2]);
            end = new Date(ends[i][0], ends[i][1], ends[i][2]);
            boolean expect = expects[i];
            subscription = new Subscription(begin, end, "TEMP");
            boolean result = subscription.isValid();
            assertEquals(expect, result);
        }
    }
}