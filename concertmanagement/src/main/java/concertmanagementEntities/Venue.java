package concertmanagementEntities;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "venue")
public class Venue {
    @Override
	public String toString() {
		return "Venue [venueId=" + venueId + ", venueName=" + venueName + ", location=" + location + ", capacity="
				+ capacity + "]";
	}
	@Id
   
    private int venueId;
    private String venueName;
    private String location;
    private int capacity;
    
	public int getVenueId() {
		return venueId;
	}
	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Venue() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}