package concertmanagementMain;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//import concertmanagement.utility.InvalidTicketException;
import concertmanagementServiceImpl.*;

public class ConcertManagementOperations {

    public static void main(String[] args)   {
        Configuration cf = new Configuration();
        cf.configure("config.xml");
        SessionFactory factory = cf.buildSessionFactory();

        Scanner sc = new Scanner(System.in);
        OrganizerServiceImpl organizerService = new OrganizerServiceImpl();
        ConcertServiceImpl concertService = new ConcertServiceImpl();
        VenueServiceImpl venueService = new VenueServiceImpl();
        TicketServiceImpl ticketService = new TicketServiceImpl();
        ArtistServiceImpl artistService = new ArtistServiceImpl();
        AttendeeServiceImpl attendeeService = new AttendeeServiceImpl();

        try {
            while (true) {
                System.out.println("Select Entity to Manage:" +
                        "\n1. Organizer" +
                        "\n2. Concert" +
                        "\n3. Venue" +
                        "\n4. Ticket" +
                        "\n5. Artist" +
                        "\n6. Attendee" +
                        "\n7. Exit");

                int entityChoice = sc.nextInt();
                if (entityChoice == 7) {
                    System.out.println("Exiting the application");
                    break;
                }

                performOperation(entityChoice, factory, sc, organizerService, concertService, venueService, ticketService, artistService, attendeeService);
            }
        } finally {
            sc.close();
            factory.close();
        }
    }

    private static void performOperation(int entityChoice, SessionFactory factory, Scanner sc,
                                         OrganizerServiceImpl organizerService, ConcertServiceImpl concertService,
                                         VenueServiceImpl venueService, TicketServiceImpl ticketService,
                                         ArtistServiceImpl artistService, AttendeeServiceImpl attendeeService) {
        System.out.println("Select Operation" +
                "\n1. Insert" +
                "\n2. Update" +
                "\n3. Delete" +
                "\n4. Select All" +
                "\n5. Get a Specific record" +
                "\n6. Count of all records");

        int operation = sc.nextInt();

        switch (entityChoice) {
            case 1:
                handleOrganizerOperation(operation, organizerService, factory);
                break;
            case 2:
                handleConcertOperation(operation, concertService, factory);
                break;
            case 3:
                handleVenueOperation(operation, venueService, factory);
                break;
            case 4:
                handleTicketOperation(operation, ticketService, factory);
                break;
            case 5:
                handleArtistOperation(operation, artistService, factory);
                break;
            case 6:
                handleAttendeeOperation(operation, attendeeService, factory);
                break;
            default:
                System.out.println("Invalid entity choice. Please try again.");
                break;
        }
    }

    private static void handleOrganizerOperation(int operation, OrganizerServiceImpl organizerService, SessionFactory factory) {
        switch (operation) {
            case 1:
                organizerService.insertOrganizer(factory);
                break;
            case 2:
                organizerService.updateOrganizer(factory);
                break;
            case 3:
                organizerService.deleteOrganizer(factory);
                break;
            case 4:
                organizerService.getAllOrganizer(factory);
                break;
            case 5:
                organizerService.getOrganizer(factory);
                break;
            case 6:
                organizerService.getOrganizerInformation(factory);
                break;
            default:
                System.out.println("Invalid operation. Please try again.");
                break;
        }
    }

    private static void handleConcertOperation(int operation, ConcertServiceImpl concertService, SessionFactory factory) {
        switch (operation) {
            case 1:
                concertService.insertConcert(factory);
                break;
            case 2:
                concertService.updateConcert(factory);
                break;
            case 3:
                concertService.deleteConcert(factory);
                break;
            case 4:
                concertService.getAllConcert(factory);
                break;
            case 5:
                concertService.getConcert(factory);
                break;
            case 6:
                concertService.getConcertInformation(factory);
                break;
            default:
                System.out.println("Invalid operation. Please try again.");
                break;
        }
    }

    private static void handleVenueOperation(int operation, VenueServiceImpl venueService, SessionFactory factory) {
        switch (operation) {
            case 1:
                venueService.insertVenue(factory);
                break;
            case 2:
                venueService.updateVenue(factory);
                break;
            case 3:
                venueService.deleteVenue(factory);
                break;
            case 4:
                venueService.getAllVenue(factory);
                break;
            case 5:
                venueService.getVenue(factory);
                break;
            case 6:
                venueService.getVenueInformation(factory);
                break;
            default:
                System.out.println("Invalid operation. Please try again.");
                break;
        }
    }

    private static void handleTicketOperation(int operation, TicketServiceImpl ticketService, SessionFactory factory)      {
        switch (operation) {
            case 1:
                ticketService.insertTicket(factory);
                break;
            case 2:
                ticketService.updateTicket(factory);
                break;
            case 3:
                ticketService.deleteTicket(factory);
                break;
            case 4:
                ticketService.getAllTicket(factory);
                break;
            case 5:
                ticketService.getTicket(factory);
                break;
            case 6:
                ticketService.getTicketInformation(factory);
                break;
            default:
                System.out.println("Invalid operation. Please try again.");
                break;
        }
    }

    private static void handleArtistOperation(int operation, ArtistServiceImpl artistService, SessionFactory factory) {
        switch (operation) {
            case 1:
                artistService.insertArtist(factory);
                break;
            case 2:
                artistService.updateArtist(factory);
                break;
            case 3:
                artistService.deleteArtist(factory);
                break;
            case 4:
                artistService.getAllArtist(factory);
                break;
            case 5:
                artistService.getArtist(factory);
                break;
            case 6:
                artistService.getArtistInformation(factory);
                break;
            default:
                System.out.println("Invalid operation. Please try again.");
                break;
        }
    }

    private static void handleAttendeeOperation(int operation, AttendeeServiceImpl attendeeService, SessionFactory factory) {
        switch (operation) {
            case 1:
                attendeeService.insertAttendee(factory);
                break;
            case 2:
                attendeeService.updateAttendee(factory);
                break;
            case 3:
                attendeeService.deleteAttendee(factory);
                break;
            case 4:
                attendeeService.getAllAttendee(factory);
                break;
            case 5:
                attendeeService.getAttendee(factory);
                break;
            case 6:
                attendeeService.getAttendeeInformation(factory);
                break;
            default:
                System.out.println("Invalid operation. Please try again.");
                break;
        }
    }
}
