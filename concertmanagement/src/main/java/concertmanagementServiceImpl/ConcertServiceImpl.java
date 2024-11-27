package concertmanagementServiceImpl;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import concertmanagementEntities.Venue;

import concertmanagementEntities.Concert;
import concertmanagementService.ConcertService;

import java.io.DataInput;
import java.io.DataInputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ConcertServiceImpl implements ConcertService {

	Scanner sc = new Scanner(System.in);
	Session session;

	// =============================Insert=======================================//
   @Override   
	public void insertConcert(SessionFactory sf) {
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();

			Concert concert = new Concert();
			System.out.println("Welcome to Concert");

			System.out.println("Enter Concert Id:");
			int concertId = sc.nextInt();
			concert.setConcertId(concertId);

			System.out.println("Enter the Concert Name:");
			String concertName = sc.next();
			concert.setConcertName(concertName);

			System.out.println("Enter the Concert date (YYYY-MM-DD):");
			String dateInput;
			boolean validDate = false;
			while (!validDate) {
			    dateInput = sc.next();
			    try {
			        concert.setDate(Date.valueOf(dateInput));  // Converts to SQL Date if format is correct
			        validDate = true;  // Exit the loop if date format is correct
			    } catch (IllegalArgumentException e) {
			        System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format:");
			    }
			}


			System.out.println("Enter Venue Id:");
			int venueId = sc.nextInt();
			Venue venue = session.get(Venue.class, venueId);

			if (venue != null) {
				concert.setVenue(venue);
				session.persist(concert);
				tx.commit();
				System.out.println("Concert added successfully.");
			} else {
				System.out.println("Venue Id not found.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//==================================Update==================================//
	@Override
	public void updateConcert(SessionFactory sf) {

		session = sf.openSession();
		Concert concert;

		try {
			while (true) {
				System.out.println(
						"Choose an Option for Update" + "\n1.Update ConcertName\n2.ConcertDate\n3.VenueId\n4.Exit");

				int option = sc.nextInt();

				// Exit the loop if the exit option is selected
				if (option == 4) {
					System.out.println("Exiting update menu.");
					break;
				}

				System.out.println("Enter Concert Id:");

				if (!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter a numeric Concert ID.");
					sc.next(); // clear the invalid input
					continue;
				}

				int concertId = sc.nextInt();
				concert = session.get(Concert.class, concertId);

				if (concert == null) {
					System.out.println("Concert ID not found. Please try again.");
					continue;
				}

				Transaction tx = session.beginTransaction();

				switch (option) {
				case 1:
					System.out.println("Update Concert Name");
					concert.setConcertName(sc.next());
					break;
				case 2:
					System.out.println("Update Concert Date(YYYY-MM-DD):");
					String dateInput = sc.next();
					try {
						concert.setDate(Date.valueOf(dateInput));
					} catch (IllegalArgumentException e) {
						System.out.println("Invalid date format. Please use YYYY-MM-DD.");
						tx.rollback();
						continue;
					}
					break;

				case 3:
					System.out.println("Update Venue Id");
					int venueId = sc.nextInt();
					Venue venue = session.get(Venue.class, venueId);
					if (venue != null) {
						concert.setVenue(venue);

					} else {
						System.out.println("VenueId is not found.");
					}
					break;

				case 4:
					System.out.println("Exiting update.");

				default:
					System.out.println("Invalid option, please try again.");
					continue;
				}

				session.saveOrUpdate(concert);
				tx.commit();
				System.out.println("Concert information updated successfully");
			}
		} finally {
			if (session != null) {
				session.close(); // Ensure session is closed to prevent leaks
			}
		}
	}

	// ====================================Delete===========================================//

	@Override
	public void deleteConcert(SessionFactory sf) {

		session = sf.openSession();

		Transaction tx = session.beginTransaction();

		System.out.println("Enter Concert Id:");
		int id = sc.nextInt();
		Concert concert = session.get(Concert.class, id);

		try {
			if (concert != null) {
				session.delete(concert);
				tx.commit();
			} else {
				System.out.println("Please Enter  valid Concert Id");
			}
		} finally {
			session.close();
		}

	}

	@Override

	public void getAllConcert(SessionFactory sf) {
		session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("from Concert");
		List<Concert> resultList = query.getResultList();

		for (Concert concert : resultList)
			System.out.println(concert);
		tx.commit();
		session.close();

	}

	@Override

	public void getConcert(SessionFactory sf) {
		session = sf.openSession();
		System.out.println("Enter Concert Id:");
		int id = sc.nextInt();

		Concert concert = session.get(Concert.class, id);
		System.out.println(concert);
		session.close();
	}

	@Override
	public void getConcertInformation(SessionFactory sf) {
		session = sf.openSession();

		Query query = session.createQuery("select count(concertId) from Concert");
		List<Integer> list = query.getResultList();

		System.out.println("Total number of concerts:" + list.get(0));
		session.close();

	}

}
