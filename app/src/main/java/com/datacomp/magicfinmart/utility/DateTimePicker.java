package com.datacomp.magicfinmart.utility;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateTimePicker {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void showDatePickerDialog(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showDatePickerDialogHealth(Context mContex, Calendar cal, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        int yearDiff = calendar.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if (yearDiff >= 18) {
            calendar.add(Calendar.YEAR, -18);
            dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        } else {
            calendar.add(Calendar.YEAR, -18);
            dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            dialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
        }

        dialog.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        dialog.show();
    }

    public static void showExpressCurrentDatePicker(Context mContex, Calendar cal, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


        calendar.add(Calendar.DATE, -1);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        if (cal != null) {
            //set existing date to calender
            dialog.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }

        dialog.show();
    }

    public static void showTimePickerDialog(Context mContex, TimePickerDialog.OnTimeSetListener callBack) {
        final Calendar c = Calendar.getInstance();
        // Current Hour

        TimePickerDialog timePickerDialog = new TimePickerDialog(mContex, callBack,
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.HOUR),
                true);

        timePickerDialog.show();
    }

    public static void firstRegNewDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, -6);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void firstRegReNewDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.YEAR, -15);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, -6);
        calendar.add(Calendar.YEAR, 15);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        dialog.show();
    }

    public static void policyExpDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, 6);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void scanVehicleExpDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, 12);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void manufactDatePicker(Context mContex, int year, int month, int date, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, year, month - 1, date);

        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.YEAR, -15);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showNextSixMonthDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, 6);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showPrevSixMonthDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, -6);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showFirstRegDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.MONTH, -9);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, 9);
        calendar.add(Calendar.YEAR, -15);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showHealthAgeDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        calendar.add(Calendar.MONTH, -9);
//       dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
//        calendar.add(Calendar.MONTH, 9);
        calendar.add(Calendar.YEAR, -18);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showKotakAgeDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        calendar.add(Calendar.MONTH, -9);
//       dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
//        calendar.add(Calendar.MONTH, 9);
        calendar.add(Calendar.YEAR, -21);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showExpressAgeDatePicker_21(Context mContex, Calendar cal, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        calendar.add(Calendar.YEAR, -21);
        calendar.add(Calendar.DATE, -1);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        if (cal != null) {
            //set existing date to calender
            dialog.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }

        dialog.show();
    }

    public static void showExpressAgeDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        calendar.add(Calendar.YEAR, -25);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showIciciCreditCardDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        calendar.add(Calendar.YEAR, -23);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showDatePickerDialog_DateSelect(Context mContex, Calendar cal, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        if (cal != null) {
            //set existing date to calender
            dialog.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }

        dialog.show();
    }

    public static void showExpressAgeDatePickerRbl(Context mContex, Calendar cal, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        calendar.add(Calendar.YEAR, -25);
        calendar.add(Calendar.DATE, -1);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        if (cal != null) {
            //set existing date to calender
            dialog.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }

        dialog.show();
    }


    public static void showHealthAgeePicker(Context mContex, int age, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        calendar.add(Calendar.YEAR, -age);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showHealthParentAgeDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        calendar.add(Calendar.MONTH, -9);
//       dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
//        calendar.add(Calendar.MONTH, 9);
        calendar.add(Calendar.YEAR, -36);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showHealthKidsAgeDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        calendar.add(Calendar.MONTH, -9);
//       dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
//        calendar.add(Calendar.MONTH, 9);
        calendar.add(Calendar.MONTH, -3);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }


    public static void showDataPickerDialog(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public static long getDiffDays(Date first, Date last) {
        long diff = last.getTime() - first.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    public static void showDataPickerDialogBeforeTwentyOneTest(Context mContex, Calendar cal, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        //always shows 1 day ahead in calender
        // added 1 day in calender
        //calendar.add(Calendar.DATE,1);
        calendar.add(Calendar.YEAR, -21);
        calendar.add(Calendar.DATE, -1);


        // disable all before date,
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        if (cal != null) {
            //set existing date to calender
            dialog.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }

        dialog.show();
    }

    public static void showDataPickerDialogBeforeTwentyOne(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        //always shows 1 day ahead in calender
        // added 1 day in calender
        //calendar.add(Calendar.DATE,1);
        calendar.add(Calendar.YEAR, -21);
        calendar.add(Calendar.DATE, -1);


        // disable all before date,
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        /*if (cal != null) {
            dialog.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }*/

        dialog.show();
    }

    public static void testDatePicker(Context mContex, Calendar cal, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        //always shows 1 day ahead in calender
        // added 1 day in calender
        //calendar.add(Calendar.DATE,1);
        calendar.add(Calendar.YEAR, -21);
        calendar.add(Calendar.DATE, -1);


        // disable all before date,
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        if (cal != null) {
            dialog.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }

        dialog.show();
    }

    public static void showDataPickerBuisnessLoan(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        //always shows 1 day ahead in calender
        // added 1 day in calender
        //calendar.add(Calendar.DATE,1);
        calendar.add(Calendar.YEAR, -2);
        calendar.add(Calendar.DATE, -1);


        // disable all before date,
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }


    public static void invoiceNewValidation(Context context, DatePickerDialog.OnDateSetListener callBack) {

        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        calendar.add(Calendar.MONTH, -6);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        dialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());

        dialog.show();
    }

    public static void invoiceReNewValidation(Context context, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


        calendar.add(Calendar.MONTH, -6);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        calendar.add(Calendar.MONTH, 6);
        calendar.add(Calendar.YEAR, -15);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void mfgYearMonthValidation(Context context, Date date, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR), date.getMonth(), date.getDate());

        calendar.add(Calendar.YEAR, -15);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.YEAR, 15);

        int yearDiff = calendar.getTime().getYear() - date.getYear();
        int monthDif = date.getMonth() - calendar.getTime().getMonth();
        //calendar.set(date.getYear(), date.getMonth(), date.getDate());
        calendar.add(Calendar.YEAR, -yearDiff);
        calendar.add(Calendar.MONTH, monthDif);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());


        dialog.show();
    }

    public static void policyExpValidation(Context context, Date date, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog;
        if (date.getMonth() <= calendar.get(Calendar.MONTH))
            dialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR), date.getMonth(), date.getDate());
        else
            dialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        calendar.add(Calendar.MONTH, 2);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        dialog.show();
    }

    public static void policyExpValidationWithIn(Context context, Date date, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog;
        if (date.getMonth() <= calendar.get(Calendar.MONTH))
            dialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR), date.getMonth(), date.getDate());
        else
            dialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        calendar.add(Calendar.MONTH, -3);

        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        dialog.show();
    }

    public static void policyExpValidationBeyond(Context context, Date date, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog;
        if (date.getMonth() <= calendar.get(Calendar.MONTH))
            dialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR), date.getMonth(), date.getDate());
        else
            dialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        calendar.add(Calendar.MONTH, -3);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        dialog.show();
    }

    public static void BikepolicyExpValidation(Context context, Calendar calenderDate, DatePickerDialog.OnDateSetListener callBack) {

        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR), calenderDate.get(Calendar.MONTH), calenderDate.get(Calendar.DATE));

        Calendar calendarToday = Calendar.getInstance();
        calendarToday.add(Calendar.DAY_OF_MONTH, -180);
        Date currDate = calendarToday.getTime();

        Calendar calendarReg = Calendar.getInstance();
        calendarReg.setTime(calenderDate.getTime());
        calendarReg.add(Calendar.DAY_OF_MONTH, 180);
        Date regDate = calendarReg.getTime();

        if (!regDate.after(currDate)) {
            dialog.getDatePicker().setMinDate(calendarReg.getTimeInMillis());
        } else {
            dialog.getDatePicker().setMinDate(calendarToday.getTimeInMillis());
        }

        Calendar calendarMax = Calendar.getInstance();
        calendarMax.add(Calendar.DAY_OF_MONTH, 60);
        dialog.getDatePicker().setMaxDate(calendarMax.getTimeInMillis());


        dialog.show();
    }

    public static void currentDateAndForward(Context context, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.show();
    }

}
