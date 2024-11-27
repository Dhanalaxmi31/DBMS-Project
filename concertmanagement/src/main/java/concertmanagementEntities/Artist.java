package concertmanagementEntities;

import java.math.BigInteger;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Artist {
	@Id
	private int artist_id;
	
	@ManyToOne
	@JoinColumn(name = "concertId") // Check this matches with 'concert_id' in Concert
	private Concert concert;

	
	
	private String artist_name;
	
	private String genre;
	private int rating;
	private Long contact_info;
	private String venue;
	public int getArtist_id() {
		return artist_id;
	}
	public void setArtist_id(int artist_id) {
		this.artist_id = artist_id;
	}
	public Concert getCon() {
		return concert;
	}
	public void setCon(Concert con) {
		this.concert= concert;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int artistrating) {
		this.rating = artistrating;
	}
	public Long getContact_info() {
		return contact_info;
	}
	public void setContact_info(Long contact_info) {
		this.contact_info = contact_info;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public Artist() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Artist [artist_id=" + artist_id + ", concert=" + concert + ", artist_name=" + artist_name + ", genre="
				+ genre + ", rating=" + rating + ", contact_info=" + contact_info + ", venue=" + venue + "]";
	}
	
	
	
}