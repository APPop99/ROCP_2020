package app.bankingApp.model;

//import java.util.ArrayList;

public class BankAccount 
{
	private int bankAccountId;
	private long bankAccountNumber;
	private double accountBalance;
	private StatusAccount statusBankAccount;
	private int accountOwnerId;
	//for v1 will implement only Accounts with single owner;
	//joint accounts will be implemented in a future version
	
	public long getBankAccountId() 
	{
		return bankAccountId;
	}
	
	public void setBankAccountId(int bankAccountId) 
	{
		this.bankAccountId = bankAccountId;
	}
	
	public long getBankAccountNumber() 
	{
		return bankAccountNumber;
	}

	public void setBankAccountNumber(long bankAccountNumber) 
	{
		this.bankAccountNumber = bankAccountNumber;
	}

	public double getAccountBalance() 
	{
		return accountBalance;
	}
	
	public void setAccountBalance(double accountBalance) 
	{
		this.accountBalance = accountBalance;
	}
	
	public StatusAccount getStatusBankAccount() 
	{
		return statusBankAccount;
	}
	
	public void setStatusBankAccount(StatusAccount statusBankAccount) 
	{
		this.statusBankAccount = statusBankAccount;
	}
	
	public int getAccountOwner() 
	{
		return accountOwnerId;
	}
	
	public void setAccountOwner(int accountOwner) 
	{
		this.accountOwnerId = accountOwner;
	}

	public BankAccount(int bankAccountId, long bankAccountNumber, double accountBalance,
			StatusAccount statusBankAccount, int accountOwnerId) 
	{
		super();
		this.bankAccountId = bankAccountId;
		this.bankAccountNumber = bankAccountNumber;
		this.accountBalance = accountBalance;
		this.statusBankAccount = statusBankAccount;
		this.accountOwnerId = accountOwnerId;
	}

	@Override
	public String toString() {
		return "BankAccount [bankAccountId=" + bankAccountId + ", bankAccountNumber=" + bankAccountNumber
				+ ", accountBalance=" + accountBalance + ", statusBankAccount=" + statusBankAccount + ", accountOwnerId="
				+ accountOwnerId + "]";
	}
}