package app.bankingApp.DAO.implementation;

import java.util.List;

import app.bankingApp.DAO.TransactionOperationsDAO;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.Transaction;
import app.bankingApp.model.User;

public class TransactionOperationsDAOImpl implements TransactionOperationsDAO 
{

	@Override
	public int createTransaction(User user, Transaction transaction) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Transaction getTransactionById(int transactionId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllTransactions() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionsListByUserId(int userId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionsByBankAccount(int bankAccountId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
