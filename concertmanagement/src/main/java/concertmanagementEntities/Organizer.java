package concertmanagementEntities;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Organizer {
	
	@Override
	public String toString() {
		return "Organizer [organizer_id=" + organizer_id + ", con=" + con + ", organizer_name=" + organizer_name
				+ ", location=" + location + ", contact_info=" + contact_info + "]";
	}
	@Id
	private int organizer_id;
	
	@ManyToOne
	@JoinColumn(name="concertId", nullable = false)
	private Concert con;
	
	private String organizer_name;
	private  String location;
	private long contact_info;
	public int getOrganizer_id() {
		return organizer_id;
	}
	public void setOrganizer_id(int organizer_id) {
		this.organizer_id = organizer_id;
	}
	public Concert getCon() {
		return con;
	}
	public void setCon(Concert con) {
		this.con = con;
	}
	public String getOrganizer_name() {
		return organizer_name;
	}
	public void setOrganizer_name(String organizer_name) {
		this.organizer_name = organizer_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public long getContact_info() {
		return contact_info;
	}
	public void setContact_info(long contact_info) {
		this.contact_info = contact_info;
	}
	public Organizer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}