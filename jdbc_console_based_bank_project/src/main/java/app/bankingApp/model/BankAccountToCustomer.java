package app.bankingApp.model;

public class BankAccountToCustomer 
{	//will be used to manage operations for joint bank accounts
	
	private long bankAccountId;
	private int customerId;
	
	public long getBankAccountId() 
	{
		return bankAccountId;
	}
	
	public void setBankAccountId(long bankAccountId) 
	{
		this.bankAccountId = bankAccountId;
	}
	
	public int getUserId() 
	{
		return customerId;
	}
	
	public void setUserId(int userId) 
	{
		this.customerId = userId;
	}
	
	public BankAccountToCustomer(long bankAccountId, int userId) 
	{
		super();
		this.bankAccountId = bankAccountId;
		this.customerId = userId;
	}
	
	@Override
	public String toString() 
	{
		return "BankAccountToUser [bankAccountId=" + bankAccountId + ", userId=" + customerId + "]";
	}	
}
