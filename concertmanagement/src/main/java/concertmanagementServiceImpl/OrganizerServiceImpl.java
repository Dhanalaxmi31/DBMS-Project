package concertmanagementServiceImpl;

import java.sql.Date;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import concertmanagementEntities.Concert;
import concertmanagementEntities.Organizer;
import concertmanagementEntities.Venue;
import concertmanagementService.OrganizerService;

public class OrganizerServiceImpl implements OrganizerService {

	Scanner sc = new Scanner(System.in);
	Session session;

	// =============================Insert=======================================//

    @Override
    public void insertOrganizer(SessionFactory sf) {
        Transaction tx = null;
        try (Session session = sf.openSession()) {
            tx = session.beginTransaction();

            Organizer organizer = new Organizer();
            System.out.println("Welcome to Organizer");

            System.out.println("Enter Organizer Id:");
            int organizer_id = sc.nextInt();
            organizer.setOrganizer_id(organizer_id);

            System.out.println("Enter the Organizer Name:");
            String organizerName = sc.next();
            organizer.setOrganizer_name(organizerName);

            System.out.println("Enter the Organizer location:");
            String location = sc.next();
            organizer.setLocation(location);

            System.out.println("Enter the Organizer ContactInfo:");
            Long contact_info = sc.nextLong();
            organizer.setContact_info(contact_info);

            System.out.println("Enter Concert Id:");
            int concertId = sc.nextInt();
            Concert concert = session.get(Concert.class, concertId);

            if (concert != null) {
                organizer.setCon(concert);
                session.save(organizer);
                tx.commit();
                System.out.println("Organizer added successfully.");
            } else {
                System.out.println("Concert ID not found.");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

	
	//=====================================Update==================================//
	@Override
		public void updateOrganizer(SessionFactory sf) {

			session = sf.openSession();
			Organizer organizer;

			try {
				while (true) {
					System.out.println(
							"Choose an Option for Update" + "\n1.Update OrganizerName\n2.Concert_id\n3.Organizer location\n4.contact_info\n5.Exit");

					int option = sc.nextInt();

					// Exit the loop if the exit option is selected
					if (option == 5) {
						System.out.println("Exiting update menu.");
						break;
					}

					System.out.println("Enter Organizer Id:");

					if (!sc.hasNextInt()) {
						System.out.println("Invalid input. Please enter a numeric Organizer ID.");
						sc.next(); // clear the invalid input
						continue;
					}

					int organizerId = sc.nextInt();
					organizer = session.get(Organizer.class, organizerId);

					if (organizer == null) {
						System.out.println("Organizer ID not found. Please try again.");
						continue;
					}

					Transaction tx = session.beginTransaction();

					switch (option) {
					case 1:
						System.out.println("Update Organizer Name");
						organizer.setOrganizer_name(sc.next());
						break;

		          	case 2:
						System.out.println("Update Concert Id");
						int concertId = sc.nextInt();
						Concert concert = session.get(Concert.class, concertId);
						if (concert != null) {
							organizer.setCon(concert);

						} else {
							System.out.println("ConcertId is not found.");
						}
						break;
		          	case 3:
						System.out.println("Update Organizer location");
						organizer.setLocation(sc.next());
						break;

					case 4:
						System.out.println("Update Organizer Phone Number");
						organizer.setContact_info(sc.nextLong());
						break;
						
					case 5:
						System.out.println("Exiting update.");

					default:
						System.out.println("Invalid option, please try again.");
						continue;
					}

					session.saveOrUpdate(organizer);
					tx.commit();
					System.out.println("Organizer information updated successfully");
				}
			} finally {
				if (session != null) {
					session.close(); // Ensure session is closed to prevent leaks
				}
			}
		}
     //=======================================Delete=====================================//
	@Override

		public void deleteOrganizer(SessionFactory sf) {

			session = sf.openSession();

			Transaction tx = session.beginTransaction();

			System.out.println("Enter Organizer Id:");
			int id = sc.nextInt();
			Organizer organizer = session.get(Organizer.class, id);

			try {
				if (organizer != null) {
					session.delete(organizer);
					tx.commit();
				} else {
					System.out.println("Please Enter  valid Organizer Id");
				}
			} finally {
				session.close();
			}

		}

		
	@Override
		public void getAllOrganizer(SessionFactory sf) {
			session = sf.openSession();
			Transaction tx = session.beginTransaction();

			Query query = session.createQuery("from Organizer");
			List<Organizer> resultList = query.getResultList();

			for (Organizer organizer : resultList)
				System.out.println(organizer);
			tx.commit();
			session.close();

		}
	
	@Override
	
		public void getOrganizer(SessionFactory sf) {
			session = sf.openSession();
			System.out.println("Enter Organizer Id:");
			int id = sc.nextInt();

			Organizer organizer = session.get(Organizer.class, id);
			System.out.println(organizer);
			session.close();
		
	}
	@Override
	public void getOrganizerInformation(SessionFactory sf) {
		session = sf.openSession();

		Query query = session.createQuery("select count(organizerId) from Organizer");
		List<Integer> list = query.getResultList();

		System.out.println("Total number of organizer:" + list.get(0));
		session.close();

	}

}