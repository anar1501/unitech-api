package az.unibank.unitech.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtils {
    public static Date prepareRegistrationExpirationDate() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, 1);
        return cal.getTime();
    }
}
