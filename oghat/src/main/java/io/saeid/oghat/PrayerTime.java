package io.saeid.oghat;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Wrapper class for calculating prayer times
 *
 * @author Saeed Masoumi
 */
public class PrayerTime {

    private static PrayerTime sInstance = new PrayerTime();

    private static final int SOBH_AZAN_INDEX = 0;
    private static final int TOLOO_INDEX = 1;
    private static final int ZOHR_AZAN_INDEX = 2;
    private static final int ASR_AZAN_INDEX = 3;
    private static final int GHOROOB_INDEX = 4;
    private static final int MAGHREB_AZAN_INDEX = 5;
    private static final int ESHA_AZAN_INDEX = 6;

    private PrayerTimeCalculator mPrayersCalculator;
    private ArrayList<String> prayerTimes;
    private Calendar mCalendar;
    private double mLatitude;
    private double mLongitude;
    private double mTimeZone;
    private int mCalculationType = CalculationType.TEHRAN;
    private int mJuristicType = JuristicType.SHAFII;

    private PrayerTime() {
        mPrayersCalculator = new PrayerTimeCalculator();

    }

    public static PrayerTime getInstance() {
        return sInstance;
    }

    public PrayerTime setDate(int gregorianYear, int gregorianMonth, int gregorianDay) {
        mCalendar = Calendar.getInstance();
        mCalendar.set(gregorianYear, gregorianMonth - 1, gregorianDay);
        return this;
    }

    public PrayerTime setLatLong(double latitude, double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
        return this;
    }

    public PrayerTime setCalculationType(int type) {
        mCalculationType = type;
        return this;
    }

    public PrayerTime setJuristicType(int type) {
        mJuristicType = type;
        return this;
    }

    public PrayerTime setTimeZone(double timeZone) {
        mTimeZone = timeZone;
        return this;
    }

    public PrayerTime calculate() {

        mPrayersCalculator.setTimeFormat(mPrayersCalculator.Time24);
        mPrayersCalculator.setCalcMethod(mCalculationType);
        mPrayersCalculator.setAsrJuristic(mJuristicType);
        mPrayersCalculator.setAdjustHighLats(mPrayersCalculator.AngleBased);
        int[] offsets = {0, 0, 0, 0, 0, 0, 0}; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
        mPrayersCalculator.tune(offsets);

        prayerTimes = mPrayersCalculator.getPrayerTimes(mCalendar,
                mLatitude, mLongitude, mTimeZone);
        mPrayersCalculator.getMidNight();
        return this;
    }

    public String getSobhAzan() {
        return getDateTime(prayerTimes.get(SOBH_AZAN_INDEX));
    }

    public String getToloo() {
        return getDateTime(prayerTimes.get(TOLOO_INDEX));
    }

    public String getZohrAzan() {
        return getDateTime(prayerTimes.get(ZOHR_AZAN_INDEX));
    }

    public String getAsrAzan() {
        return getDateTime(prayerTimes.get(ASR_AZAN_INDEX));
    }

    public String getGhoroob() {
        return getDateTime(prayerTimes.get(GHOROOB_INDEX));
    }

    public String getMaghrebAzan() {
        return getDateTime(prayerTimes.get(MAGHREB_AZAN_INDEX));
    }

    public String getEshaAzan() {
        return getDateTime(prayerTimes.get(ESHA_AZAN_INDEX));
    }

    public LocalTime getSobhAzanTime() {
        return getLocalTimeObj(prayerTimes.get(SOBH_AZAN_INDEX));
    }

    public LocalTime getTolooTime() {
        return getLocalTimeObj(prayerTimes.get(TOLOO_INDEX));
    }

    public LocalTime getZohrAzanTime() {
        return getLocalTimeObj(prayerTimes.get(ZOHR_AZAN_INDEX));
    }

    public LocalTime getAsrAzanTime() {
        return getLocalTimeObj(prayerTimes.get(ASR_AZAN_INDEX));
    }

    public LocalTime getMaghrebAzanTime() {
        return getLocalTimeObj(prayerTimes.get(MAGHREB_AZAN_INDEX));
    }

    public LocalTime getEshaAzanTime() {
        return getLocalTimeObj(prayerTimes.get(ESHA_AZAN_INDEX));
    }

    private DateTime getMidnightDateTime() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        DateTime eveningDateTime = formatter.parseDateTime(getGhoroob());
        DateTime morningAzanDateTime = formatter.parseDateTime(getSobhAzan());
        int hour = (morningAzanDateTime.getHourOfDay() + (24 - eveningDateTime.getHourOfDay() - 1));
        int min = (morningAzanDateTime.getMinuteOfHour() + (60 - eveningDateTime.getMinuteOfHour()));
        DateTime midNightTime = eveningDateTime.plusMinutes((hour * 60 + min) / 2);
        return midNightTime;
    }

    public String getMidnight() {
        return getMidnightDateTime().toString("HH:mm");
    }

    public LocalTime getMidnightTime() {
        return getLocalTimeObj(getMidnight());
    }

    private String getDateTime(String str) {
        DateTime dateTime = getDateTimeObj(str);
        return dateTime.toString("HH:mm");
    }

    public DateTime getDateTimeObj(String str) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        DateTime dateTime = formatter.parseDateTime(str);
        return dateTime;
    }

    private LocalTime getLocalTimeObj(String str) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        LocalTime localTime = formatter.parseLocalTime(str);
        return localTime;
    }

}
