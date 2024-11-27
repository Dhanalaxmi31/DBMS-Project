package concertmanagementServiceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import concertmanagementEntities.Artist;
import concertmanagementEntities.Concert;
import concertmanagementService.ArtistService;
import jakarta.persistence.EntityTransaction;

public class ArtistServiceImpl implements ArtistService {

	Scanner sc = new Scanner(System.in);
	Session session;

	@Override
	public void insertArtist(SessionFactory sf) {
	    try (Session session = sf.openSession()) {
	        Transaction tx = session.beginTransaction();
	        Artist artist = new Artist();
	        System.out.println("Welcome Artist");

	        System.out.println("Enter Artist Id:");
	        int artistId = sc.nextInt();
	        artist.setArtist_id(artistId);

	        System.out.println("Enter the Artist Name:");
	        String artistName = sc.next();
	        artist.setArtist_name(artistName);

	        System.out.println("Enter the genre:");
	        String artistGenre = sc.next();
	        artist.setGenre(artistGenre);

	        System.out.println("Enter Concert Id:");
	        int concertId = sc.nextInt();
	        Concert concert = session.get(Concert.class, concertId);

	        if (concert != null) {
	            artist.setCon(concert);

	            System.out.println("Enter the Rating:");
	            int artistRating = sc.nextInt();
	            artist.setRating(artistRating);

	            System.out.println("Enter the ContactInfo:");
	            Long artistContactInfo = sc.nextLong();
	            artist.setContact_info(artistContactInfo);

	            System.out.println("Enter the venue:");
	            String artistVenue = sc.next();
	            artist.setVenue(artistVenue); // Corrected the field here

	            session.persist(artist);
	            tx.commit();
	            System.out.println("Artist added successfully.");
	        } else {
	            System.out.println("Concert Id not found.");
	        }
	    }  
	    finally {
	    	session.close();
	    }
	}
	
	@Override

	public void updateArtist(SessionFactory sf) {
		session = sf.openSession();
		Artist artist;

		try {
			while (true) {
				System.out.println("Choose an Option for Update"
						+ "\n1.Update Artist_Name\n2.Genre\n3.Rating\n4.Contact_info\n5.ArtistVenue\n6.Concert_id\n7.Exit");

				int option = sc.nextInt();

				// Exit the loop if the exit option is selected
				if (option == 7) {
					System.out.println("Exiting update menu.");
					break;
				}

				System.out.println("Enter Artist Id:");

				if (!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter a numeric Artist ID.");
					sc.next(); // clear the invalid input
					continue;
				}

				int artistId = sc.nextInt();
				artist = session.get(Artist.class, artistId);

				if (artist == null) {
					System.out.println("Artist ID not found. Please try again.");
					continue;
				}

				Transaction tx = session.beginTransaction();

				switch (option) {
				case 1:
					System.out.println("Update Artist Name");
					artist.setArtist_name(sc.next());
					break;
				case 2:
					System.out.println("Update Artist Genre");
					artist.setGenre(sc.next());
					break;
					
				case 3:
					System.out.println("Update Artist Rating");
					artist.setRating(sc.nextInt());
					break;
					

				case 4:
					System.out.println("Update Customer Phone Number");
					artist.setContact_info(sc.nextLong());
					break;
					
				case 5:
					System.out.println("Update Artist Venue");
					artist.setVenue(sc.next());
					break;
				
				case 6:

					System.out.println("Update Concert Id");
					int concertId = sc.nextInt();
					Concert concert = session.get(Concert.class, concertId);
					if (concert != null) {
						artist.setCon(concert);

					} else {
						System.out.println("ConcertId is not found.");
					}
					break;

				default:
					System.out.println("Invalid option, please try again.");
					continue;
				}

				session.saveOrUpdate(artist);
				tx.commit();
				System.out.println("Artist information updated successfully");
			}
		} finally {
			if (session != null) {
				session.close(); // Ensure session is closed to prevent leaks
			}
		}
	}

	@Override

	public void deleteArtist(SessionFactory sf) {

		session = sf.openSession();

		Transaction tx = session.beginTransaction();

		System.out.println("Enter Artist Id:");
		int id = sc.nextInt();
		Artist artist = session.get(Artist.class, id);

		try {
			if (artist != null) {
				session.delete(artist);
				tx.commit();
			} else {
				System.out.println("Please Enter  valid Artist Id");
			}
		} finally {
			session.close();
		}

	}

	@Override

	public void getAllArtist(SessionFactory sf) {
		session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("from Artist");
		List<Artist> resultList = query.getResultList();

		for (Artist artist : resultList)
			System.out.println(artist);
		tx.commit();
		session.close();

	}

	@Override

	public void getArtist(SessionFactory sf) {
		session = sf.openSession();
		System.out.println("Enter Artist Id:");
		int id = sc.nextInt();

		Artist artist = session.get(Artist.class, id);
		System.out.println(artist);
		session.close();

	}

	public void getArtistInformation(SessionFactory sf) {
		Session session = null;
		try {
			session = sf.openSession();
			Query<Long> query = session.createQuery("select count(a.artistId) from Artist a", Long.class);
			Long artistCount = query.getSingleResult();
			System.out.println("Total number of artists: " + artistCount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
