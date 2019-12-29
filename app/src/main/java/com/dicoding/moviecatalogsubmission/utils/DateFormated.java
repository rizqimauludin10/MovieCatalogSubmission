package com.dicoding.moviecatalogsubmission.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormated {
    private Context context;

    public DateFormated(Context context) {
        this.context = context;
    }

    public String setDateFormat(String dateM) {
        DateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        Date dates;
        SimpleDateFormat dateFormat;
        String outputText = null;
        try {
            dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone("ID"));
            dates = inputFormat.parse(dateM);
            assert dates != null;
            outputText = outputFormat.format(dates);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputText;
    }
}
