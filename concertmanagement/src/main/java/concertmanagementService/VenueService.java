package concertmanagementService;

import org.hibernate.SessionFactory;

import concertmanagement.utility.VenueCapacityException;

public interface VenueService {

	void insertVenue(SessionFactory sf) throws VenueCapacityException;
	void updateVenue(SessionFactory sf);
	void deleteVenue(SessionFactory sf);
	void getAllVenue(SessionFactory sf);
	void getVenue(SessionFactory sf);
	void getVenueInformation(SessionFactory sf);
	
}
 