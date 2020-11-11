package app.bankingApp.model;

public class Transaction 
{
	private int idTransaction;
	private BankAccount sourceBankAccountId;
	private BankAccount destinationBankAccountId;
	private double amount;
	private TransactionType transactionType;
	private boolean isTransactionCleared;
	
	public int getIdTransaction() 
	{
		return idTransaction;
	}
	
	public void setIdTransaction(int idTransaction) 
	{
		this.idTransaction = idTransaction;
	}

	public BankAccount getSourceBankAccountId() {
		return sourceBankAccountId;
	}

	public void setSourceBankAccountId(BankAccount sourceBankAccountId) {
		this.sourceBankAccountId = sourceBankAccountId;
	}

	public BankAccount getDestinationBankAccountId() {
		return destinationBankAccountId;
	}

	public void setDestinationBankAccountId(BankAccount destinationBankAccountId) {
		this.destinationBankAccountId = destinationBankAccountId;
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
	
	public Transaction(int idTransaction, BankAccount sourceBankAccountId, BankAccount destinationBankAccountId,
			double amount, TransactionType transactionType, boolean isTransactionCleared) {
		super();
		this.idTransaction = idTransaction;
		this.sourceBankAccountId = sourceBankAccountId;
		this.destinationBankAccountId = destinationBankAccountId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.isTransactionCleared = isTransactionCleared;
	}

	@Override
	public String toString() {
		return "Transaction [idTransaction=" + idTransaction + ", sourceBankAccountId=" + sourceBankAccountId
				+ ", destinationBankAccountId=" + destinationBankAccountId + ", amount=" + amount + ", transactionType="
				+ transactionType + ", isTransactionCleared=" + isTransactionCleared + "]";
	}
}