package concertmanagementService;


import org.hibernate.SessionFactory;

public interface ArtistService {

	void insertArtist(SessionFactory sf);
	void updateArtist(SessionFactory sf);
	void deleteArtist(SessionFactory sf);
	void getAllArtist(SessionFactory sf);
	void getArtist(SessionFactory sf);
	void getArtistInformation(SessionFactory sf);
	
	}
	