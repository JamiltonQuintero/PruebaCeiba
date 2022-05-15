package com.ceiba.springmvc.util;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class UtilDate {
		
	public Date obtenerFechaCompletaActual() {
		   LocalDateTime now = LocalDateTime.now();
		   return Date
				      .from(now.atZone(ZoneId.systemDefault())
				      .toInstant());
	}

	public LocalDate convertirDateALocalDate(Date date) {

		return Instant.ofEpochMilli(date.getTime())
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}

	public String fechaformatoDiaMesAño(Date date) {


		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return convertirDateALocalDate(date).format(formato);
	}

	public static Date convertirLocalDateADate(LocalDate dateToConvert) {
		return Date.from(dateToConvert.atStartOfDay()
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}


	public static Date obtenerFechaSinDiasHabiles(int dias){
		LocalDate now = LocalDate.now();
		return convertirLocalDateADate(addDaysSkippingWeekends(now, dias));
	}


		/*public static Date obtenerFechaSinDiasHabiles(int dias){
			Calendar cal2 = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			int totalDays= dias/5*7;
			int remainder = dias % 5;
			cal2.add(Calendar.DATE, totalDays);

			switch(cal.get(Calendar.DAY_OF_WEEK)){
				case 1:
				case 2:
					break;
				case 3:
					if(remainder >3)
						cal2.add(Calendar.DATE,2);
					break;
				case 4:
					if(remainder >2)
						cal2.add(Calendar.DATE,2);
					break;
				case 5:
				case 6:
					if(remainder >1)
						cal2.add(Calendar.DATE,2);
					break;
				case 7:
					if(remainder >1)
						cal2.add(Calendar.DATE,1);
					break;
			}

			cal2.add(Calendar.DATE, remainder);

			//Date date = new Date(cal2.getTimeInMillis());

			return new Date(cal2.getTimeInMillis());

		}

		*/

	public static LocalDate addDaysSkippingWeekends(LocalDate date, int days) {
		LocalDate result = date;
		int addedDays = 0;
		while (addedDays < days) {
			result = result.plusDays(1);
			if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
				++addedDays;
			}
		}
		return result;
	}

}
