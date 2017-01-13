package booster.mingliu.boostertest.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuming on 17/1/13.
 */
public class FieldsUtils {


    public static double getDoubleValue(EditText field) {
        return Double.parseDouble(field.getText().toString().trim());
    }

    public static long getLongValue(EditText field) {
        return Long.parseLong(field.getText().toString().trim());
    }

    public static String getStringValue(EditText field) {
        return field.getText().toString().trim();
    }

    public static boolean isEmailValid(String email) {
        return (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(email)) ? false : true;
    }

    public static CharSequence convertTimestampToRelativeTimespan(Context context, String timestamp) {
        CharSequence str = "";

        try {
            // 2014-09-01 08:15:00
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(timestamp.replace("T", " "));
            long ms = date.getTime();

            str = DateUtils.getRelativeDateTimeString(context, ms, DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_12HOUR);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    public static boolean checkEmptyError(EditText editText, String errorMsg) {
        String text = getStringValue(editText);
        if (TextUtils.isEmpty(text)) {
            setEditTextError(editText, errorMsg);
            return true;
        }
        return false;
    }

    public static void setEditTextError(EditText editText, String errorMsg) {
        editText.requestFocus();
        editText.setError(errorMsg);
    }

    public static CharSequence getDateString(Context context, String timestamp) {
        CharSequence str = "";

        try {
            // 2014-09-01 08:15:00
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(format.parse(timestamp));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }


}
