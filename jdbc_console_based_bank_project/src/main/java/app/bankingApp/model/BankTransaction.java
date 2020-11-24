package app.bankingApp.model;

import java.sql.Timestamp;

public class BankTransaction 
{
	private int idTransaction;
	private BankAccount sourceBankAccount;
	private User userSender;
	private int sourceBankAccountId;
//	private long sourceBankAccountNumber;
	private BankAccount destinationBankAccount;
	private User userRecipient;
	private int destinationBankAccountId;
//	private long destinationBankAccountNumber;
	private double amount;
	private TransactionType transactionType;
	private boolean isTransactionCleared; 		//to be user when accepting a money transfer from another account 
	private Timestamp transactionDate;
	private String transactionDescription;
	
	public int getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	public BankAccount getSourceBankAccount() {
		return sourceBankAccount;
	}

	public void setSourceBankAccount(BankAccount sourceBankAccount) {
		this.sourceBankAccount = sourceBankAccount;
	}

	public User getUserSender() {
		return userSender;
	}

	public void setUserSender(User userSender) {
		this.userSender = userSender;
	}

	public int getSourceBankAccountId() {
		return sourceBankAccountId;
	}

	public void setSourceBankAccountId(int sourceBankAccountId) {
		this.sourceBankAccountId = sourceBankAccountId;
	}

	public BankAccount getDestinationBankAccount() {
		return destinationBankAccount;
	}

	public void setDestinationBankAccount(BankAccount destinationBankAccount) {
		this.destinationBankAccount = destinationBankAccount;
	}

	public User getUserRecipient() {
		return userRecipient;
	}

	public void setUserRecipient(User userRecipient) {
		this.userRecipient = userRecipient;
	}

	public int getDestinationBankAccountId() {
		return destinationBankAccountId;
	}

	public void setDestinationBankAccountId(int destinationBankAccountId) {
		this.destinationBankAccountId = destinationBankAccountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public boolean isTransactionCleared() {
		return isTransactionCleared;
	}

	public void setTransactionCleared(boolean isTransactionCleared) {
		this.isTransactionCleared = isTransactionCleared;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
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
		return "BankTransaction [idTransaction=" + idTransaction + ", sourceBankAccount=" + sourceBankAccount
				+ ", userSender=" + userSender + ", sourceBankAccountId=" + sourceBankAccountId
				+ ", destinationBankAccount=" + destinationBankAccount + ", userRecipient=" + userRecipient
				+ ", destinationBankAccountId=" + destinationBankAccountId + ", amount=" + amount + ", transactionType="
				+ transactionType + ", isTransactionCleared=" + isTransactionCleared + ", transactionDate="
				+ transactionDate + ", transactionDescription=" + transactionDescription + "]";
	}
}