package utils;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by VarunBhalla on 22/11/16.
 */
public class StringUtils {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static String removeLastChar(String s) {
        if (!TextUtils.isEmpty(s)) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    public static String trimAndUpperCase(@Nullable String s, int n) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        if (s.length() > n) {
            s = s.substring(0, n);
        }
        return s.toUpperCase();
    }

    public static boolean equals(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }

        return s1.equalsIgnoreCase(s2);
    }

    public static boolean equals(Object s1, Object s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        return s1.toString().equalsIgnoreCase(s2.toString());
    }

    public static boolean equalsJson(Object s1, Object s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        Gson gson = new Gson();
        String json1 = gson.toJson(s1);
        String json2 = gson.toJson(s2);
        return json1.equalsIgnoreCase(json2);
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;

        }
        if (phoneNumber.matches("(\\d){10}")) {
            return true;
        }
        return false;
    }

    public static boolean validateEmail(String emailId) {

        if (emailId != null) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailId);
            return matcher.find();
        }

        return false;
    }


    /**
     * From source of WordUtils
     */

    public static String capitalizeFully(String str, final char... delimiters) {
        final int delimLen = delimiters == null ? -1 : delimiters.length;
        if (TextUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        str = str.toLowerCase();
        return capitalize(str, delimiters);
    }

    public static String capitalizeFully(final String str) {
        return capitalizeFully(str, null);
    }


    public static String capitalize(final String str, final char... delimiters) {
        final int delimLen = delimiters == null ? -1 : delimiters.length;
        if (TextUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        final char[] buffer = str.toCharArray();
        boolean capitalizeNext = true;
        for (int i = 0; i < buffer.length; i++) {
            final char ch = buffer[i];
            if (isDelimiter(ch, delimiters)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                buffer[i] = Character.toTitleCase(ch);
                capitalizeNext = false;
            }
        }
        return new String(buffer);
    }


    /**
     * Is the character a delimiter.
     *
     * @param ch         the character to check
     * @param delimiters the delimiters
     * @return true if it is a delimiter
     */
    private static boolean isDelimiter(final char ch, final char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        for (final char delimiter : delimiters) {
            if (ch == delimiter) {
                return true;
            }
        }
        return false;
    }

    public static Calendar getCalenderFromDateString(String dateString, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);

        Date date;
        Calendar calendar;
        if (dateString != null && dateString.length() > 0) {
            try {
                date = formatter.parse(dateString);
                calendar = Calendar.getInstance();
                calendar.setTime(date);
                return calendar;
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }

    }

    @SuppressWarnings("deprecation")
    public static Spanned setTextViaHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }


    public static String getRupeeFormattedValue(String value) {
        value = value.replace(",", "");
        char lastDigit = value.charAt(value.length() - 1);
        String result = "";
        int len = value.length() - 1;
        int nDigits = 0;

        for (int i = len - 1; i >= 0; i--) {
            result = value.charAt(i) + result;
            nDigits++;
            if (((nDigits % 2) == 0) && (i > 0)) {
                result = "," + result;
            }
        }
        return (result + lastDigit);
    }

    public static ArrayList<String> getRentalReceiptsMonths(String checkInDate, String seperator) {
        ArrayList<String> RentalReceiptMonthsArray = new ArrayList<>();

        Calendar startDate = StringUtils.getCalenderFromDateString(checkInDate, "dd/MM/yyyy");

        int startYear = startDate.get(Calendar.YEAR);
        int startMonth = startDate.get(Calendar.MONTH) + 1;

        Calendar now = Calendar.getInstance();

        int endYear = now.get(Calendar.YEAR);
        int endMonth = now.get(Calendar.MONTH) + 1;


        for (int i = startYear; i <= endYear; i++) {

            int loopStart = 1;
            int loopEnd = 12;

            if (i == startYear && i == endYear) {
                loopStart = startMonth;
                loopEnd = endMonth;
            } else if (i == startYear) {
                loopStart = startMonth;
            } else if (i == endYear) {
                loopEnd = endMonth;
            }

            for (int j = loopStart; j <= loopEnd; j++) {
                RentalReceiptMonthsArray.add(getMonth(j) + seperator + i);
            }
        }

        return RentalReceiptMonthsArray;
    }


    public static String getMonth(int monthInt) {

        String month = null;
        switch (monthInt) {
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;

        }
        return month;
    }

    public static String getAbbreviatedPrettyDate(String string, boolean showYear) {
        Calendar date = StringUtils.getCalenderFromDateString(string, "dd/MM/yyyy");

        SimpleDateFormat tmp = new SimpleDateFormat("d", Locale.ENGLISH);
        String str = tmp.format(date.getTime());

        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        if (date.get(Calendar.DAY_OF_MONTH) > 10 && date.get(Calendar.DAY_OF_MONTH) < 14)
            str = str + "th ";
        else {
            if (str.endsWith("1")) str = str + "st ";
            else if (str.endsWith("2")) str = str + "nd ";
            else if (str.endsWith("3")) str = str + "rd ";
            else str = str + "th ";
        }
        tmp = new SimpleDateFormat("MMM", Locale.ENGLISH);
        str = str + tmp.format(date.getTime());
        if (showYear) {
            str = str + ", " + date.get(Calendar.YEAR);
        }
        return str;
    }

    public static String getAbbreviatedPrettyDateFullMonth(String string, boolean showYear) {
        Calendar date = StringUtils.getCalenderFromDateString(string, "dd/MM/yyyy");

        SimpleDateFormat tmp = new SimpleDateFormat("d", Locale.ENGLISH);
        String str = tmp.format(date.getTime());

        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        if (date.get(Calendar.DAY_OF_MONTH) > 10 && date.get(Calendar.DAY_OF_MONTH) < 14)
            str = str + "th ";
        else {
            if (str.endsWith("1")) str = str + "st ";
            else if (str.endsWith("2")) str = str + "nd ";
            else if (str.endsWith("3")) str = str + "rd ";
            else str = str + "th ";
        }
        tmp = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        str = str + tmp.format(date.getTime());
        if (showYear) {
            str = str + ", " + date.get(Calendar.YEAR);
        }
        return str;
    }

    public static String getDay(String string) {
        Calendar date = StringUtils.getCalenderFromDateString(string, "dd/MM/yyyy");

        SimpleDateFormat tmp = new SimpleDateFormat("d", Locale.ENGLISH);

        return tmp.format(date.getTime());
    }


    public static String getDayofWeek(String string) {
        Calendar date = StringUtils.getCalenderFromDateString(string, "dd/MM/yyyy");

        SimpleDateFormat tmp = new SimpleDateFormat("EEE", Locale.ENGLISH);

        return tmp.format(date.getTime());
    }

    public static String getDayofWeekFDullString(String string) {
        Calendar date = StringUtils.getCalenderFromDateString(string, "dd/MM/yyyy");

        SimpleDateFormat tmp = new SimpleDateFormat("EEEE", Locale.ENGLISH);

        return tmp.format(date.getTime());
    }




    public static String getMonth(String string) {
        Calendar date = StringUtils.getCalenderFromDateString(string, "dd/MM/yyyy");

        SimpleDateFormat tmp = new SimpleDateFormat("MMM", Locale.ENGLISH);

        return tmp.format(date.getTime());
    }

    public static String getTime(String oldstring) {
        String newstring = "";
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(oldstring);
            newstring = new SimpleDateFormat("h:mm a", Locale.ENGLISH).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return newstring;
    }

    public static boolean compareCheckOutDate(String string) {
        Calendar checkOutDate = StringUtils.getCalenderFromDateString(string, "dd/MM/yyyy");
        Calendar today = Calendar.getInstance();

        if (today.after(checkOutDate)) {
            return true;
        }else{
            return false;
        }
    }


    public static long calendarDaysBetween(Calendar startCal, Calendar endCal) {

        // Create copies so we don't update the original calendars.

        Calendar start = Calendar.getInstance();
        start.setTimeZone(startCal.getTimeZone());
        start.setTimeInMillis(startCal.getTimeInMillis());

        Calendar end = Calendar.getInstance();
        end.setTimeZone(endCal.getTimeZone());
        end.setTimeInMillis(endCal.getTimeInMillis());

        // Set the copies to be at midnight, but keep the day information.

        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        // At this point, each calendar is set to midnight on
        // their respective days. Now use TimeUnit.MILLISECONDS to
        // compute the number of full days between the two of them.

        return TimeUnit.MILLISECONDS.toDays(
                Math.abs(end.getTimeInMillis() - start.getTimeInMillis()));
    }

    public static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }
}