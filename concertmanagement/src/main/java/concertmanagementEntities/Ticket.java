package concertmanagementEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticket {
	
	@Id
	private int ticket_id;
	
	@ManyToOne
	@JoinColumn(name="concertId")
	private Concert con;
	
	
	private int price;
	private Boolean availability;
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	public Concert getCon() {
		return con;
	}
	public void setCon(Concert con) {
		this.con = con;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Boolean getAvailability() {
		return availability;
	}
	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}
	public Ticket() {
		super();
		
		}
	@Override
	public String toString() {
		return "Ticket [ticket_id=" + ticket_id + ", con=" + con + ", price=" + price + ", availability=" + availability
				+ "]";
	}
}
