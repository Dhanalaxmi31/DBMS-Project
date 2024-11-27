package concertmanagementEntities;

import java.sql.Date;


import concertmanagementEntities.Venue;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class Concert {
	@Override
	public String toString() {
		return "Concert [concertId=" + concertId + ", concertName=" + concertName + ", date=" + date + ", venue="
				+ venue + "]";
	}

	@Id
	
	private int concertId;
	private String concertName;
	private Date date;

	@ManyToOne
	@JoinColumn(name = "venue_id", referencedColumnName = "venueId")
	private Venue venue;

	public Concert() {
		super();
		
	}

	public int getConcertId() {
		return concertId;
	}

	public void setConcertId(int concertId) {
		this.concertId = concertId;
	}

	public String getConcertName() {
		return concertName;
	}

	public void setConcertName(String concertName) {
		this.concertName = concertName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue2) {
		this.venue = venue2;
	}
	
	

}