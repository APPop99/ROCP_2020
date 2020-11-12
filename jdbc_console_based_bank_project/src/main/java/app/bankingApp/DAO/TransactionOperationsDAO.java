package app.bankingApp.DAO;

import java.util.List;

import app.bankingApp.exception.BusinessException;
//import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
import app.bankingApp.model.User;

public interface TransactionOperationsDAO 
{
	//CREATE operation
	public int createTransaction(User user, BankTransaction transaction)throws BusinessException;
		
	//READ operation
	public BankTransaction getTransactionById(int transactionId) throws BusinessException;
	public List<BankTransaction> getAllTransactions() throws BusinessException;
	public List<BankTransaction> getTransactionsListByUserId(int userId) throws BusinessException;
	public List<BankTransaction> getTransactionsByBankAccount(int bankAccountId) throws BusinessException;
}
