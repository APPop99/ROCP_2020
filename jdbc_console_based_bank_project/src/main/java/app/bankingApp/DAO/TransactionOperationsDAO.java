package app.bankingApp.DAO;

import java.util.List;

import app.bankingApp.exception.BusinessException;
//import app.bankingApp.model.BankAccount;
import app.bankingApp.model.Transaction;
import app.bankingApp.model.User;

public interface TransactionOperationsDAO 
{
	//CREATE operation
	public int createTransaction(User user, Transaction transaction)throws BusinessException;
		
	//READ operation
	public Transaction getTransactionById(int transactionId) throws BusinessException;
	public List<Transaction> getAllTransactions() throws BusinessException;
	public List<Transaction> getTransactionsListByUserId(int userId) throws BusinessException;
	public List<Transaction> getTransactionsByBankAccount(int bankAccountId) throws BusinessException;
}
