package app.bankingApp.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class User 
{
	private int id;
	private String firstName;
	private String lastName;
	private Date dob;
	private long phoneNumber;
	private String email; 						//will be used as username to access the account
	private String password;					//combination between 8-40 chars of letter, numbers and special chars
	private Timestamp dateUserAccountCreation; 	//will be used to track when the user accounts where created
	private Timestamp dateUserAccountDeletion;
	private StatusUser statusUser;
	private int statusUserId;
	private boolean customerApprovalStatus;
	private boolean customerApprovalPending;
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public Date getDob() 
	{
		return dob;
	}
	public void setDob(Date dob) 
	{
		this.dob = dob;
	}
	public long getPhoneNumber() 
	{
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}
	public Timestamp getDateUserAccountCreation() 
	{
		return dateUserAccountCreation;
	}
	public void setDateUserAccountCreation(Timestamp dateUserAccountCreation) 
	{
		this.dateUserAccountCreation = dateUserAccountCreation;
	}
	public StatusUser getStatusUser() 
	{
		return statusUser;
	}
	public void setStatusUser(StatusUser statusUser) 
	{
		this.statusUser = statusUser;
	}
	public int getStatusUserId() {
		return statusUserId;
	}
	public void setStatusUserId(int statusUserId) {
		this.statusUserId = statusUserId;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public Timestamp getDateUserAccountDeletion() 
	{
		return dateUserAccountDeletion;
	}
	public void setDateUserAccountDeletion(Timestamp dateUserAccountDeletion) 
	{
		this.dateUserAccountDeletion = dateUserAccountDeletion;
	}
	public User(String firstName, String lastName, String email, Date dob, long phoneNumber, String password) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public User(int id, String firstName, String lastName, Date dob, long phoneNumber, String email, String password,
			Timestamp dateUserAccountCreation, Timestamp dateUserAccountDeletion, StatusUser statusUser) 
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.dateUserAccountCreation = dateUserAccountCreation;
		this.dateUserAccountDeletion = dateUserAccountDeletion;
		this.statusUser = statusUser;
	}
	
	public User(int id, String firstName, String lastName, String email, StatusUser statusUser) 
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.statusUser = statusUser;
	}
	
	public User() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", LastName=" + lastName + ", Email=" + email + ", dob="
				+ dob + ", phoneNumber=" + phoneNumber + ", dateUserAccountCreation=" + dateUserAccountCreation
				+ ", statusUser=" + statusUser + ", password=" + password + ", dateUserAccountDeletion=" + dateUserAccountDeletion
				+ "]";
	}
	public boolean isCustomerStatusApproved() 
	{
		return customerApprovalStatus;
	}
	public void setCustomerStatusApproved(boolean customerApprovalStatus) 
	{
		this.customerApprovalStatus = customerApprovalStatus;
	}
	public boolean isCustomerApprovalPending() 
	{
		return customerApprovalPending;
	}
	public void setCustomerApprovalPending(boolean customerApprovalPending) {
		this.customerApprovalPending = customerApprovalPending;
	}
}
