package com.aaamidatlantic.scea.patterns.builder;

import java.util.ArrayList;
import java.util.Date;
public class Scheduler {

	public Appointment createAppointment(AppointmentBuilder builder,
	        Date startDate, Date endDate, String description,
	        Location location, ArrayList attendees) throws InformationRequiredException{
	            if (builder == null){
	                builder = new AppointmentBuilder();
	            }
	        builder.buildAppointment();
	        builder.buildDates(startDate, endDate);
	        builder.buildDescription(description);
	        builder.buildAttendees(attendees);
	        builder.buildLocation(location);
	        return builder.getAppointment();
	    }

}
