package app.bankingApp.model;

import java.sql.Timestamp;

//import java.util.ArrayList;

public class BankAccount 
{
	private int bankAccountId;
	private long bankAccountNumber;
	private double accountBalance;
	private StatusAccount statusBankAccount;
	private int statusAccountId;
	private Timestamp dateBankAccountCreation; 	//will be used to track when the user accounts where created
	private Timestamp dateBankAccountApproval;
	private Timestamp dateBankAccountDeletion;
	private boolean isBankAccountApproved;
	private boolean isBankAccountApprovalPending;
	private int accountOwnerId;
	//for v1 will implement only Accounts with single owner;
	//joint accounts will be implemented in a future version
	
	public int getBankAccountId() 
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
	
	public int getStatusAccountId() 
	{
		return statusAccountId;
	}

	public void setStatusAccountId(int statusAccountId) 
	{
		this.statusAccountId = statusAccountId;
	}

	public Timestamp getDateBankAccountCreation() 
	{
		return dateBankAccountCreation;
	}

	public void setDateBankAccountCreation(Timestamp dateBankAccountCreation) 
	{
		this.dateBankAccountCreation = dateBankAccountCreation;
	}
	
	public Timestamp getDateBankAccountApproval() 
	{
		return dateBankAccountApproval;
	}

	public void setDateBankAccountApproval(Timestamp dateBankAccountApproval) 
	{
		this.dateBankAccountApproval = dateBankAccountApproval;
	}

	public Timestamp getDateBankAccountDeletion() 
	{
		return dateBankAccountDeletion;
	}

	public void setDateBankAccountDeletion(Timestamp dateBankAccountDeletion) 
	{
		this.dateBankAccountDeletion = dateBankAccountDeletion;
	}

	public boolean isBankAccountApproved() 
	{
		return isBankAccountApproved;
	}

	public void setBankAccountApproved(boolean isBankAccountApproved) 
	{
		this.isBankAccountApproved = isBankAccountApproved;
	}

	public boolean isBankAccountApprovalPending() 
	{
		return isBankAccountApprovalPending;
	}

	public void setBankAccountApprovalPending(boolean isBankAccountApprovalPending) 
	{
		this.isBankAccountApprovalPending = isBankAccountApprovalPending;
	}

	public int getAccountOwnerId() 
	{
		return accountOwnerId;
	}
	
	public void setAccountOwnerId(int accountOwnerId) 
	{
		this.accountOwnerId = accountOwnerId;
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
	
	public BankAccount(int bankAccountId, long bankAccountNumber, double accountBalance,
			StatusAccount statusBankAccount, int statusAccountId, Timestamp dateBankAccountCreation,
			boolean isBankAccountApproved, boolean isBankAccountApprovalPending, int accountOwnerId) 
	{
		super();
		this.bankAccountId = bankAccountId;
		this.bankAccountNumber = bankAccountNumber;
		this.accountBalance = accountBalance;
		this.statusBankAccount = statusBankAccount;
		this.statusAccountId = statusAccountId;
		this.dateBankAccountCreation = dateBankAccountCreation;
		this.isBankAccountApproved = isBankAccountApproved;
		this.isBankAccountApprovalPending = isBankAccountApprovalPending;
		this.accountOwnerId = accountOwnerId;
	}

	public BankAccount(long bankAccountNumber, double accountBalance, int accountOwnerId) 
	{
		super();
		this.bankAccountNumber = bankAccountNumber;
		this.accountBalance = accountBalance;
		this.accountOwnerId = accountOwnerId;
	}
	
	@Override
	public String toString() {
		return "BankAccount [bankAccountId=" + bankAccountId + ", bankAccountNumber=" + bankAccountNumber
				+ ", accountBalance=" + accountBalance + ", statusBankAccount=" + statusBankAccount
				+ ", statusAccountId=" + statusAccountId + ", dateBankAccountCreation=" + dateBankAccountCreation
				+ ", dateBankAccountApproval=" + dateBankAccountApproval + ", dateBankAccountDeletion="
				+ dateBankAccountDeletion + ", isBankAccountApproved=" + isBankAccountApproved
				+ ", isBankAccountApprovalPending=" + isBankAccountApprovalPending + ", accountOwnerId="
				+ accountOwnerId + "]";
	}
}