package concertmanagementServiceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import concertmanagement.utility.VenueCapacityException;
import concertmanagementEntities.Venue;
import concertmanagementService.VenueService;

public class VenueServiceImpl implements VenueService {

	
	Scanner sc=new Scanner(System.in);
	 Session session;
	 
	
	@Override
	public void insertVenue(SessionFactory sf) {
	    try {
	        session = sf.openSession();
	        Transaction tx = session.beginTransaction();

	        Venue venue = new Venue();
	        System.out.println("Welcome to Venue");

	        // Venue Id Input with Validation
	        System.out.println("Enter the venue Id:");
	        while (!sc.hasNextInt()) {
	            System.out.println("Invalid input. Please enter a numeric value for Venue Id.");
	            sc.next(); // Clear the invalid input
	        }
	        int venueId = sc.nextInt();
	        venue.setVenueId(venueId);

	        // Venue Name Input
	        System.out.println("Enter the Venue Name:");
	        String venueName = sc.nextLine();
	        venue.setVenueName(venueName);

	        // Venue Location Input
	        System.out.println("Enter the Venue Location:");
	        sc.nextLine(); // Consume the newline
	        String location = sc.nextLine();
	        venue.setLocation(location);

	        // Venue Capacity Input with Validation
	        System.out.println("Enter the Venue Capacity:");
	        while (!sc.hasNextInt()) {
	            System.out.println("Invalid input. Please enter a numeric value for Venue Capacity.");
	            sc.next(); // Clear the invalid input
	        }
	        int capacity = sc.nextInt();
            int Capacity=testcheckVenueCapacityException(capacity);
   	     if(capacity==1500) {
   	    	 throw new VenueCapacityException("we are sorry Hall is full");
   	     }

	        venue.setCapacity(capacity);

	       
	        session.persist(venue);
	        tx.commit();
	        System.out.println("Venue added successfully.");

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}
	public int testcheckVenueCapacityException(int cap) {
    	if(cap>1500) {
    		return 0;
    	}else {
    		return 1;
    	}
    }

	@Override
	public void updateVenue(SessionFactory sf) {
	    session = sf.openSession();
	    Venue venue;

	    try {
	        while (true) {
	            System.out.println("Choose an Option for Update" +
	                               "\n1.Update VenueName\n2.Location\n3.Capacity\n4.Exit");

	            int option;
	            if (sc.hasNextInt()) {
	                option = sc.nextInt();
	            } else {
	                System.out.println("Invalid option, please enter a number between 1 and 4.");
	                sc.next(); // clear invalid input
	                continue;
	            }

	            // Exit the loop if the exit option is selected
	            if (option == 4) {
	                System.out.println("Exiting update menu.");
	                break;
	            }

	            System.out.println("Enter Venue Id:");

	            if (!sc.hasNextInt()) {
	                System.out.println("Invalid input. Please enter a numeric Venue ID.");
	                sc.next(); // clear the invalid input
	                continue;
	            }

	            int venueId = sc.nextInt();
	            venue = session.get(Venue.class, venueId);

	            if (venue == null) {
	                System.out.println("Venue ID not found. Please try again.");
	                continue;
	            }

	            Transaction tx = session.beginTransaction();

	            switch (option) {
	                case 1:
	                    System.out.println("Update Venue Name");
	                    sc.nextLine(); // clear newline from previous input
	                    venue.setVenueName(sc.nextLine());
	                    break;
	                case 2:
	                    System.out.println("Update Venue Location");
	                    sc.nextLine(); // clear newline from previous input
	                    venue.setLocation(sc.nextLine());
	                    break;
	                case 3:
	                    System.out.println("Update Venue Capacity");
	                    if (sc.hasNextInt()) {
	                        venue.setCapacity(sc.nextInt());
	                    } else {
	                        System.out.println("Invalid input. Please enter a numeric capacity.");
	                        sc.next(); // clear invalid input
	                        tx.rollback();
	                        continue;
	                    }
	                    break;
	                default:
	                    System.out.println("Invalid option, please try again.");
	                    tx.rollback();
	                    continue;
	            }

	            session.saveOrUpdate(venue);
	            tx.commit();
	            System.out.println("Venue information updated successfully");

	            // Clear any remaining input from the buffer before the next iteration
	            sc.nextLine();
	        }
	    } finally {
	        if (session != null) {
	            session.close(); // Ensure session is closed to prevent leaks
	        }
	    }
	}
	@Override

	public void deleteVenue(SessionFactory sf) {
	    session = sf.openSession();
	    Transaction tx = session.beginTransaction();

	    System.out.println("Enter Venue Id:");
	    int id = sc.nextInt();
	    Venue venue = session.get(Venue.class, id);

	    try {
	        if (venue != null) {
	            // Check for dependent records in the Concert table
	            long dependentConcerts = (long) session.createQuery(
	                "SELECT COUNT(c) FROM Concert c WHERE c.venue.venueId = :venueId"
	            ).setParameter("venueId", id).uniqueResult();

	            if (dependentConcerts > 0) {
	                System.out.println("Cannot delete Venue as there are associated Concert records.");
	            } else {
	                session.delete(venue);
	                tx.commit();
	                System.out.println("Venue deleted successfully.");
	            }
	        } else {
	            System.out.println("Please enter a valid venue Id.");
	        }
	    } finally {
	        session.close();
	    }
	}


		
		
		
		
		
		

	@Override
	
		public void getAllVenue(SessionFactory sf) {
			session = sf.openSession();
			Transaction tx = session.beginTransaction();

			Query query = session.createQuery("from Venue");
			List<Venue> resultList = query.getResultList();

			for (Venue venue : resultList)
				System.out.println(venue);
			tx.commit();
			session.close();
		
		
	}

	@Override
	
		public void getVenue(SessionFactory sf) {
			session = sf.openSession();
			System.out.println("Enter Venue Id:");
			int id = sc.nextInt();

		Venue venue = session.get(Venue.class, id);
			System.out.println(venue);
			session.close();
	}

	@Override
	public void getVenueInformation(SessionFactory sf) {
		session = sf.openSession();

		Query query = session.createQuery("select count(venueId) from Venue");
		List<Integer> list = query.getResultList();

		System.out.println("Total number of venue:" + list.get(0));
		session.close();

	}

	
}
