package concertmanagementServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

import concertmanagementEntities.Concert;
import concertmanagementEntities.Ticket;
import concertmanagementService.TicketService;

public class TicketServiceImpl implements TicketService {

	
	Scanner sc = new Scanner(System.in);
	Session session;
	
	//=========================Insert=============================//
	@Override
	public void insertTicket(SessionFactory sf)      {
	    try (Session session = sf.openSession()) {
	        Transaction tx = session.beginTransaction();

	        Ticket ticket = new Ticket();
	        System.out.println("Welcome to Ticket");

	        System.out.println("Enter Ticket Id:");
	        int ticket_id = sc.nextInt();
	        ticket.setTicket_id(ticket_id);

	        System.out.println("Enter Concert Id:");
	        int concertId = sc.nextInt();
	        Concert concert = session.get(Concert.class, concertId);

	        if (concert != null) {
	            ticket.setCon(concert);

	            System.out.println("Enter the Ticket Price:");
	            int price = sc.nextInt();
	            ticket.setPrice(price);

	            Scanner scanner = new Scanner(System.in);
	            System.out.println("Enter a boolean value (true/false):");
	            boolean isValidInput = false;
	            boolean value = false;

	            while (!isValidInput) {
	                try {
	                    value = scanner.nextBoolean();
	                    isValidInput = true; // Exit the loop on successful input
	                } catch (InputMismatchException e) {
	                    System.out.println("Invalid input. Please enter true or false.");
	                    scanner.next(); // Clear the invalid input
	                }
	            }
	            ticket.setAvailability(value);

	            session.persist(ticket);  // Persist after setting all values
	            tx.commit();
	            System.out.println("Ticket added successfully.");
	        } else {
	            System.out.println("Concert Id not found.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	//=============================Update=========================//

	@Override
	
		public void updateTicket(SessionFactory sf) {

			session = sf.openSession();
		     Ticket ticket;

			try {
				while (true) {
					System.out.println(
							"Choose an Option for Update" + "\n1.Update concert_id\n2.Ticket Price\n3.Ticket Availability\n4.Exit");

					int option = sc.nextInt();

					// Exit the loop if the exit option is selected
					if (option == 4) {
						System.out.println("Exiting update menu.");
						break;
					}

					System.out.println("Enter Ticket Id:");

					if (!sc.hasNextInt()) {
						System.out.println("Invalid input. Please enter a numeric Ticket ID.");
						sc.next(); // clear the invalid input
						continue;
					}

					int ticket_id = sc.nextInt();
					ticket = session.get(Ticket.class, ticket_id);

					if (ticket == null) {
						System.out.println("Ticket ID not found. Please try again.");
						continue;
					}

					Transaction tx = session.beginTransaction();

					switch (option) {
					case 1:
						System.out.println("Update Concert Id");
						int concert_id = sc.nextInt();
						Concert concert = session.get(Concert.class, concert_id);
						if (concert != null) {
							ticket.setCon(concert);

						} else {
							System.out.println("Concert Id is not found.");
						}
						break;
					case 2:
					
						System.out.println("Update Ticket price");
					   ticket.setPrice(sc.nextInt());
						break;

					case 3:
						System.out.println("Enter new Ticket Availability (true/false):");
	                    boolean isValidInput = false;
	                    boolean availability = false;

	                    while (!isValidInput) {
	                        try {
	                            availability = sc.nextBoolean();
	                            isValidInput = true; // Exit loop on successful input
	                        } catch (InputMismatchException e) {
	                            System.out.println("Invalid input. Please enter true or false.");
	                            sc.next(); // Clear the invalid input
	                        }
	                    }
	                    ticket.setAvailability(availability);
	                    break;
						

					case 4:
						System.out.println("Exiting update.");

					default:
						System.out.println("Invalid option, please try again.");
						continue;
					}

					session.saveOrUpdate(ticket);
					tx.commit();
					System.out.println("Ticket information updated successfully");
				}
			} finally {
				if (session != null) {
					session.close(); // Ensure session is closed to prevent leaks
				}
			}
	}
	
	//==============================Delete================================//		
	@Override
	public void deleteTicket(SessionFactory sf) {
		
		session = sf.openSession();

		Transaction tx = session.beginTransaction();

		System.out.println("Enter Ticket Id:");
		int id = sc.nextInt();
		Ticket ticket= session.get(Ticket.class, id);

		try {
			if (ticket != null) {
				session.delete(ticket);
				tx.commit();
			} else {
				System.out.println("Please Enter  valid Ticket Id");
			}
		} finally {
			session.close();
		}

	}
     @Override
	public void getAllTicket(SessionFactory sf) {
	    Session session = sf.openSession();
	    
	    org.hibernate.query.Query<Ticket> query = session.createQuery("from Ticket", Ticket.class);
	    List<Ticket> resultList = query.list();
	    
	    for (Ticket ticket : resultList) {
	        System.out.println(ticket);
	    }
	    
	    session.close();
	}

	@Override
	public void getTicket(SessionFactory sf) {
	    Session session = sf.openSession();
	    System.out.println("Enter Ticket Id:");
	    int id = sc.nextInt();
	    
	    Ticket ticket = session.get(Ticket.class, id);
	    System.out.println(ticket);
	    
	    session.close();
	}

	@Override
	public void getTicketInformation(SessionFactory sf) {
	    Session session = sf.openSession();
	    
	    Query query = (Query) session.createQuery("select count(ticket_id) from Ticket", Long.class);
	    Ticket count = ((org.hibernate.query.Query<Ticket>) query).uniqueResult();
	    
	    System.out.println("Total number of tickets: " + count);
	    
	    session.close();
	}
}









