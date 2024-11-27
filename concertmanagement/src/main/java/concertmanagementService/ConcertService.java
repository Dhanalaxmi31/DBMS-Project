package concertmanagementService;


import org.hibernate.SessionFactory;

public interface ConcertService {

	void insertConcert(SessionFactory sf);
	void updateConcert(SessionFactory sf);
	void deleteConcert(SessionFactory sf);
	void getAllConcert(SessionFactory sf);
	void getConcert(SessionFactory sf);
	void getConcertInformation(SessionFactory sf);
	}
	
