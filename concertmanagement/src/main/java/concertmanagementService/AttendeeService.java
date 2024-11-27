package concertmanagementService;

import org.hibernate.SessionFactory;

public interface AttendeeService {

	void insertAttendee(SessionFactory sf);
	void updateAttendee(SessionFactory sf);
	void deleteAttendee(SessionFactory sf);
	void getAllAttendee(SessionFactory sf);
	void getAttendee(SessionFactory sf);
	void getAttendeeInformation(SessionFactory sf);
	}
	
