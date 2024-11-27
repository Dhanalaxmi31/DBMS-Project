package concertmanagementEntities;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Attendee {
	@Override
	public String toString() {
		return "Attendee [attendee_id=" + attendee_id + ", attendee_name=" + attendee_name + ", attendee_email="
				+ attendee_email + ", contact_info=" + contact_info + ", tic=" + tic + "]";
	}

	@Id
	private int attendee_id;
	private String attendee_name;
	private String attendee_email;
	private long contact_info;
	
	@ManyToOne
	@JoinColumn(name="ticket_id")
	private Ticket tic;

	public int getAttendee_id() {
		return attendee_id;
	}

	public void setAttendee_id(int attendee_id) {
		this.attendee_id = attendee_id;
	}

	public String getAttendee_name() {
		return attendee_name;
	}

	public void setAttendee_name(String attendee_name) {
		this.attendee_name = attendee_name;
	}

	public String getAttendee_email() {
		return attendee_email;
	}

	public void setAttendee_email(String attendee_email) {
		this.attendee_email = attendee_email;
	}

	public long getContact_info() {
		return contact_info;
	}

	public void setContact_info(long contact_info) {
		this.contact_info = contact_info;
	}

	public Ticket getTic() {
		return tic;
	}

	public void setTic(Ticket tic) {
		this.tic = tic;
	}

	public Attendee() {
		super();
		
	}
	

}
