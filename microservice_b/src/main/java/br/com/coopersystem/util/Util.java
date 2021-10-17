package br.com.coopersystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class Util {

    public static boolean isDayUtil(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH);
        LocalDate myDate = LocalDate.parse(data, formatter);
        if (myDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || myDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return false;
        }
        return true;
    }

    public static String diaUltilAnterior(String data) {
        String dataAnterior = null;
        try {
            while (true) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
                cal.setTime(sdf.parse(data));
                cal.add(Calendar.DATE, -1);
                dataAnterior = sdf.format(cal.getTime());
                System.out.println(dataAnterior);
                if (isDayUtil(dataAnterior)) {
                    break;
                }
                data = dataAnterior;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataAnterior;

    }
}
