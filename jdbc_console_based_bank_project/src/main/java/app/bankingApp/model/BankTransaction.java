package app.bankingApp.model;

import java.sql.Timestamp;

public class BankTransaction 
{
	private int idTransaction;
	private BankAccount sourceBankAccount;
	private int sourceBankAccountId;
	private BankAccount destinationBankAccount;
	private int destinationBankAccountId;
	private double amount;
	private TransactionType transactionType;
	private boolean isTransactionCleared; 		//to be user when accepting a money transfer from another account 
	private Timestamp transactionDate;
	
	public int getIdTransaction() 
	{
		return idTransaction;
	}
	
	public void setIdTransaction(int idTransaction) 
	{
		this.idTransaction = idTransaction;
	}

	public BankAccount getSourceBankAccount() {
		return sourceBankAccount;
	}

	public void setSourceBankAccount(BankAccount sourceBankAccount) {
		this.sourceBankAccount = sourceBankAccount;
	}

	public BankAccount getDestinationBankAccount() {
		return destinationBankAccount;
	}

	public void setDestinationBankAccount(BankAccount destinationBankAccount) {
		this.destinationBankAccount = destinationBankAccount;
	}

	public boolean isTransactionCleared() {
		return isTransactionCleared;
	}

	public void setTransactionCleared(boolean isTransactionCleared) {
		this.isTransactionCleared = isTransactionCleared;
	}
	
	public double getAmount() 
	{
		return amount;
	}
	
	public void setAmount(double amount) 
	{
		this.amount = amount;
	}
	
	public TransactionType getTransactionType() 
	{
		return transactionType;
	}
	
	public void setTransactionType(TransactionType transactionType) 
	{
		this.transactionType = transactionType;
	}
	
	public Timestamp getTransactionDate() 
	{
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) 
	{
		this.transactionDate = transactionDate;
	}

	public int getSourceBankAccountId() 
	{
		return sourceBankAccountId;
	}

	public void setSourceBankAccountId(int sourceBankAccountId) 
	{
		this.sourceBankAccountId = sourceBankAccountId;
	}

	public int getDestinationBankAccountId() 
	{
		return destinationBankAccountId;
	}

	public void setDestinationBankAccountId(int destinationBankAccountId) 
	{
		this.destinationBankAccountId = destinationBankAccountId;
	}
	
	public BankTransaction(int idTransaction, BankAccount sourceBankAccount, BankAccount destinationBankAccount,
			double amount, TransactionType transactionType, Timestamp transactionDate) 
	{
		super();
		this.idTransaction = idTransaction;
		this.sourceBankAccount = sourceBankAccount;
		this.destinationBankAccount = destinationBankAccount;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
	}
	
	public BankTransaction(int idTransaction, int sourceBankAccountId, int destinationBankAccountId,
			double amount, TransactionType transactionType, Timestamp transactionDate) 
	{
		super();
		this.idTransaction = idTransaction;
		this.sourceBankAccountId = sourceBankAccountId;
		this.destinationBankAccountId = destinationBankAccountId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
	}
	
	public BankTransaction(int idTransaction, double amount, TransactionType transactionType, Timestamp transactionDate) 
	{
		super();
		this.idTransaction = idTransaction;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
	}
	
	public BankTransaction(int idTransaction, BankAccount sourceBankAccount, BankAccount destinationBankAccount,
			double amount, Timestamp transactionDate) {
		super();
		this.idTransaction = idTransaction;
		this.sourceBankAccount = sourceBankAccount;
		this.destinationBankAccount = destinationBankAccount;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}

//	public BankTransaction(int idTransaction, BankAccount singleBankAccountId,
//			double amount, TransactionType transactionType, Timestamp transactionDate) {
//		super();
//		this.idTransaction = idTransaction;
//		this.singleBankAccountId = singleBankAccountId;
//		this.amount = amount;
//		this.transactionType = transactionType;
//		this.transactionDate = transactionDate;
//	}
	
	public BankTransaction() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Transaction [idTransaction=" + idTransaction + ", sourceBankAccountId=" + sourceBankAccount
				+ ", destinationBankAccountId=" + destinationBankAccount + ", amount=" + amount + ", transactionType="
				+ transactionType + ", isTransactionCleared=" + isTransactionCleared + ", transactionDate="
				+ transactionDate + "]";
	}
}