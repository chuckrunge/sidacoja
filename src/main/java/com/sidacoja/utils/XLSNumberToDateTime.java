//sidacoja - A Simple Data Conversion for Java
//Copyright (C) 2015  Chuck Runge
//Lombard, IL.
//CGRunge001@GMail.com

//This program is free software; you can redistribute it and/or
//modify it under the terms of the GNU General Public License
//as published by the Free Software Foundation; either version 2
//of the License, or (at your option) any later version.

//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.

//You should have received a copy of the GNU General Public License
//along with this program; if not, write to the Free Software
//Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

package com.sidacoja.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;

public class XLSNumberToDateTime {

	public XLSNumberToDateTime() {}
	
	public String xlsToDate(String sz) {

		BigDecimal whole = new BigDecimal(0);
		BigDecimal fraction = new BigDecimal(0);
		//Date newDate = new Date();
		String returnSz = "";
		String dateSz = "";
		String timeSz = "";
		
		if(sz == null) {
		//required input is missing
			returnSz = null;
			return returnSz;
		}

		Float fl = new Float(sz); 
		
		if(Float.isNaN(fl)) {
		//this is not a number!
			returnSz = null;
			return returnSz;
		}

		//round to whole number
		BigDecimal xx = new BigDecimal(sz);
		whole = returnWholeNumber(xx);
		//whole = new BigDecimal(fl);
		//whole = whole.setScale(0, RoundingMode.DOWN);

		//get the fractional part
		fraction = new BigDecimal(sz);
		console("fraction = "+fraction);
		fraction = fraction.subtract(whole);
		console("fraction = "+fraction);

		if(whole.compareTo(BigDecimal.ZERO) > 0) {
			dateSz = calcDate(whole);
			console("dateSz = "+dateSz);
		}
		
		if(fraction.compareTo(BigDecimal.ZERO) > 0) {
			timeSz = calcTime(fraction);
			console("timeSz = "+timeSz);
		}
		
		returnSz = dateSz+timeSz;
		
		return returnSz;
	
	} //end method

public String calcDate(BigDecimal bd) {
	String dateSz ="";
	BigDecimal whole = new BigDecimal(0);
	BigDecimal fraction = new BigDecimal(0);
	BigDecimal threeSixtyFive = new BigDecimal(365);
	BigDecimal four = new BigDecimal(4);
	BigDecimal result = new BigDecimal(0);
	BigDecimal years = new BigDecimal(0);
	BigDecimal leapYears = new BigDecimal(0);

	console("calcDate("+bd);
	//divide whole number by 365 for number of years (use whole number only)
	//precision of 9 digits
	result = bd.divide(threeSixtyFive, 8, RoundingMode.HALF_UP);
	
	//round years to whole number		
	whole = returnWholeNumber(result);
	years = whole;
	//years = returnWholeNumber(result);
	console("years = "+years);
	
	//get the fractional years
	fraction = result;
	fraction = fraction.subtract(whole);

	//divide years by 4 for number of leap years to subtract (use whole number only)
	leapYears = years.divide(four, 8, RoundingMode.HALF_UP);

	//round leapYears to whole number		
	leapYears = returnWholeNumber(leapYears);
	console("leap years = "+leapYears);
	
	//multiply fraction of result by 365 for day of year (use whole number only)
	console("fraction: "+fraction);
	result = fraction.multiply(threeSixtyFive); //.setScale(0, RoundingMode.HALF_UP);
	console("result: "+result);
	
	//round to whole number		
	whole = returnWholeNumber(result);
	//whole = result;
	console("day of year = "+whole);
	
	BigInteger dayOfYear = BigInteger.ZERO;
	long yearAdjust = 1900;
	//assemble gregorian date from julian date
	if(leapYears.compareTo(whole) > 0) { //leapYears > # days
		dayOfYear = threeSixtyFive.toBigInteger().subtract(
				leapYears.toBigInteger().subtract(
						whole.toBigInteger()));
		yearAdjust = 1899;
		console("*==>"+yearAdjust);
		console("A==>"+dayOfYear);
	} else if(leapYears.compareTo(whole) < 0) { //leapYears < #days
		dayOfYear = whole.toBigInteger().subtract(
						leapYears.toBigInteger());
		//years.add(new BigDecimal(1900));
		console("B==>"+dayOfYear);
	} else {
		//dayOfYear = whole.toBigInteger();
		//dayOfYear = dayOfYear.subtract(BigInteger.ONE);
		dayOfYear = threeSixtyFive.toBigInteger();
		yearAdjust = 1899;
		console("C==>"+dayOfYear);
	}
	if(yearAdjust == 1899) { //year adjustment in process
		BigInteger biFour = four.toBigInteger();
		BigInteger bi = years.toBigInteger();
		console("remainder: "+bi.remainder(biFour));
		if(bi.remainder(biFour).equals(BigInteger.ZERO)) {
			console(years+" was a leap year");
			console(dayOfYear+" must be adjusted");
			dayOfYear = dayOfYear.add(BigInteger.ONE);
		}
	}
	console("day of year adjusted: "+ dayOfYear);
	int i=0;
	long[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	long[] daysInYear = {31,59,90,120,151,181,212,243,273,304,334,365};
	for(i=0;i<12;i++) {
		if(daysInYear[i] >= dayOfYear.intValue()) {
			break;
		}
	}
	//console("month index: "+i+" "+ daysInYear[i] + " "+dayOfYear.intValue() );
	//console("daysInMonth: "+daysInMonth[i]);
	long yy = yearAdjust + years.longValue();
	console("year="+yy);
	console("daysInMonth[i] "+i+": "+daysInMonth[i]+" dayOfYear: "+dayOfYear.longValue() );
	long dd = daysInMonth[i] - (365 - dayOfYear.longValue());
	long mm = i+1; //index was one less
	console("month="+mm);
	console("day="+dd);
	
	dateSz = mm + "/" + dd + "/" + yy + " "; 
	
	return dateSz;
}

public String calcTime(BigDecimal bd) {
	String timeSz = "";
	BigDecimal whole = new BigDecimal(0);
	BigDecimal fraction = new BigDecimal(0);
	BigDecimal result = new BigDecimal(0);
	BigDecimal twentyFour = new BigDecimal(24);
	BigDecimal sixty = new BigDecimal(60);
	
	//multiply fractional portion by 24 for hour of day
	result = bd.multiply(twentyFour);
	console("result: "+result);
	//whole number only	
	whole = returnWholeNumber(result);
	timeSz = whole + ":";

	//get the fractional part
	fraction = result;
	fraction = fraction.subtract(whole);
	console("fraction: "+fraction);
	//multiply fraction of result by 60 for minutes (then use whole number only)
	result = fraction.multiply(sixty); 
	
	//round to whole number		
	whole = returnWholeNumber(result);
	whole = result.setScale(1, RoundingMode.HALF_UP);
	timeSz = timeSz + whole;
		
	return timeSz;
}

public BigDecimal returnWholeNumber(BigDecimal bd) {
	BigDecimal whole = new BigDecimal(0);
	whole = bd;
	whole = whole.setScale(0, RoundingMode.DOWN); //.HALF_UP);
	
	return whole;
}

public void console(String sz) {

	System.out.println(sz);

}

} //end class