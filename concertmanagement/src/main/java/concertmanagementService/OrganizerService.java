package concertmanagementService;

import org.hibernate.SessionFactory;

//import concertmanagement.utility.EventCancellationException;

public interface OrganizerService {
    void insertOrganizer(SessionFactory sf);
    void updateOrganizer(SessionFactory sf);
    void deleteOrganizer(SessionFactory sf);
    void getAllOrganizer(SessionFactory sf);
    void getOrganizer(SessionFactory sf);
    void getOrganizerInformation(SessionFactory sf);

    // Method to cancel a concert
   // void cancelConcert(SessionFactory sf, int concertId) throws EventCancellationException;
}
