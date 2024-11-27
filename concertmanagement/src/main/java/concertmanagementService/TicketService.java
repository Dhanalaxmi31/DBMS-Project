package concertmanagementService;


import org.hibernate.SessionFactory;




public interface TicketService {

	void insertTicket(SessionFactory sf) ;
	void updateTicket(SessionFactory sf);
	void deleteTicket(SessionFactory sf);
	void getAllTicket(SessionFactory sf);
	void getTicket(SessionFactory sf);
	void getTicketInformation(SessionFactory sf);
	}
	
