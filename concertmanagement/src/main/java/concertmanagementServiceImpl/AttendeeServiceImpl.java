package concertmanagementServiceImpl;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import concertmanagementEntities.Attendee;
import concertmanagementEntities.Concert;
import concertmanagementEntities.Ticket;
import concertmanagementEntities.Venue;
import concertmanagementService.AttendeeService;

public class AttendeeServiceImpl implements AttendeeService {
      
	
	Scanner sc = new Scanner(System.in);
	Session session;
	
	//=======================Insert================================//

	@Override
	
		public void insertAttendee(SessionFactory sf) {
			try (Session session = sf.openSession()) {
				Transaction tx = session.beginTransaction();

			Attendee attendee = new Attendee();
				System.out.println("Welcome to Attendee");

				System.out.println("Enter Attendee Id:");
				int AttendeeId = sc.nextInt();
			     attendee.setAttendee_id(AttendeeId);

				System.out.println("Enter the Attendee Name:");
				String attendee_name = sc.next();
				attendee.setAttendee_name(attendee_name);

				
				System.out.println("Enter the Attende Email:");
				String attendee_email = sc.next();
				attendee.setAttendee_email(attendee_email);
				
				System.out.println("Enter the ContactInfo:");
				Long contact_info = sc.nextLong();
				attendee.setContact_info(contact_info);


				System.out.println("Enter Ticket Id:");
				int ticket_id = sc.nextInt();
				Ticket ticket = session.get(Ticket.class, ticket_id);

				if (ticket != null) {
					attendee.setTic(ticket);
					session.persist(attendee);
					tx.commit();
					System.out.println("Ticket added successfully.");
				} else {
					System.out.println("Ticket Id not found.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//==============================Update==================================//


	@Override
	public void updateAttendee(SessionFactory sf) {

		session = sf.openSession();
	      Attendee attendee;

		try {
			while (true) {
				System.out.println(
						"Choose an Option for Update" + "\n1.Update Attendee_name\n2.Attendee_email\n3.Contact_info\n4.Ticket_id\n5.Exit");

				int option = sc.nextInt();

				// Exit the loop if the exit option is selected
				if (option == 5) {
					System.out.println("Exiting update menu.");
					break;
				}

				System.out.println("Enter Attendee Id:");

				if (!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter a numeric Attendee ID.");
					sc.next(); // clear the invalid input
					continue;
				}

				int attendee_id = sc.nextInt();
				attendee = session.get(Attendee.class, attendee_id);

				if (attendee == null) {
					System.out.println("Attendee ID not found. Please try again.");
					continue;
				}

				Transaction tx = session.beginTransaction();

				switch (option) {
				case 1:
					System.out.println("Update Attendee Name");
					attendee.setAttendee_name(sc.next());
					break;
					
				case 2:
					System.out.println("Update Attendee email");
				   attendee.setAttendee_email(sc.next());
					break;
					
				case 3:
					System.out.println("Update Customer Phone Number");
					attendee.setContact_info(sc.nextLong());
					break;

				case 4:
					System.out.println("Update Ticket Id");
					int ticket_id = sc.nextInt();
				Ticket ticket = session.get(Ticket.class, ticket_id);
					if (ticket != null) {
						attendee.setTic(ticket);

					} else {
						System.out.println("Ticket_id is not found.");
					}
					break;

				case 5:
					System.out.println("Exiting update.");

				default:
					System.out.println("Invalid option, please try again.");
					continue;
				}

				session.saveOrUpdate(attendee);
				tx.commit();
				System.out.println("Attendee information updated successfully");
			}
		} finally {
			if (session != null) {
				session.close(); // Ensure session is closed to prevent leaks
			}
		}
	}

	@Override

		public void deleteAttendee(SessionFactory sf) {

			session = sf.openSession();

			Transaction tx = session.beginTransaction();

			System.out.println("Enter Attendee Id:");
			int id = sc.nextInt();
			Attendee attendee = session.get(Attendee.class, id);

			try {
				if (attendee != null) {
					session.delete(attendee);
					tx.commit();
				} else {
					System.out.println("Please Enter  valid Attendee Id");
				}
			} finally {
				session.close();
			}

		}


	

	@Override
	

		public void getAllAttendee(SessionFactory sf) {
			session = sf.openSession();
			Transaction tx = session.beginTransaction();

			Query query = session.createQuery("from Attendee");
			List<Attendee> resultList = query.getResultList();

			for (Attendee attendee : resultList)
				System.out.println(attendee);
			tx.commit();
			session.close();

		}

		
	

	@Override

	public void getAttendee(SessionFactory sf) {
		session = sf.openSession();
		System.out.println("Enter Attendee Id:");
		int id = sc.nextInt();

		Attendee attendee = session.get(Attendee.class, id);
		System.out.println(attendee);
		session.close();
	}


	@Override
	public void getAttendeeInformation(SessionFactory sf) {
		session = sf.openSession();

		Query query = session.createQuery("select count(attendee_id) from Attendee");
		List<Integer> list = query.getResultList();

		System.out.println("Total number of attendee:" + list.get(0));
		session.close();

	}

}
