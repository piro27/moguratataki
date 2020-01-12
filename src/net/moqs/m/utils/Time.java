package net.moqs.m.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.Data;

/**
 * Created by moqs.net on 2019/10/29.
 */
// @AllArgsConstructor
@Data
public class Time {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;

	private Date date;

	@Override
	public String toString() {
		String uptime = "";

		if (this.hour > 0) {
			uptime += hour + "時間";
		}

		if (this.minute > 0) {
			uptime += minute + "分";
		}

		if (this.second > 0) {
			uptime += second + "秒";
		}

		if (uptime.trim().length() == 0) {
			uptime = "0秒";
		}

		return uptime;
	}

	public static Time getTimeFromDate(Date createdTime) {
		int[] units = { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND };
		Date now = new Date();
		int[] result = new int[units.length];

		Calendar sCal = Calendar.getInstance();
		Calendar tCal = Calendar.getInstance();

		sCal.setTime(createdTime);
		tCal.setTime(now);

		for (int i = units.length - 1; i >= 0; i--) {
			result[i] = tCal.get(units[i]) - sCal.get(units[i]);
			if (result[i] < 0) {
				tCal.add(units[i - 1], -1);
				int add = tCal.getActualMaximum(units[i]);
				result[i] += (units[i] == Calendar.DAY_OF_MONTH) ? add : add + 1;
			}
		}

		Time time = new Time();

		time.year = result[0];
		time.month = result[1];
		time.day = result[2];
		time.hour = result[3];
		time.minute = result[4];
		time.second = result[5];

		time.date = now;

		return time;
	}

	public static Time addTime(Time time1, Time time2) {
		int[] units = { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND };
		int[] result = new int[units.length];

		Calendar sCal = Calendar.getInstance();
		Calendar tCal = Calendar.getInstance();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d1 = null;
		try {
			d1 = simpleDateFormat.parse(time1.year + "/" + time1.month + "/" + time1.day + " " + time1.hour + ":" + time1.minute + ":" + time1.second);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sCal.setTime(d1);

		Date d2 = null;
		try {
			d2 = simpleDateFormat.parse(time2.year + "/" + time2.month + "/" + time2.day + " " + time2.hour + ":" + time2.minute + ":" + time2.second);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tCal.setTime(d2);

		for (int i = units.length - 1; i >= 0; i--) {
			result[i] = tCal.get(units[i]) - sCal.get(units[i]);
			if (result[i] < 0) {
				tCal.add(units[i - 1], -1);
				int add = tCal.getActualMaximum(units[i]);
				result[i] += (units[i] == Calendar.DAY_OF_MONTH) ? add : add + 1;
			}
		}

		Time time = new Time();

		time.year = result[0];
		time.month = result[1];
		time.day = result[2];
		time.hour = result[3];
		time.minute = result[4];
		time.second = result[5];

		return time;
	}

}
