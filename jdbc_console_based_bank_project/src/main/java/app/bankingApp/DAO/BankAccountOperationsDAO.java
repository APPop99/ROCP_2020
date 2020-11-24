package app.bankingApp.DAO;

import java.util.List;
//import java.util.Map;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
import app.bankingApp.model.User;
import app.bankingApp.model.TransactionType;

public interface BankAccountOperationsDAO 
{
	//CREATE operation
	public int createNewBankAccount(User user, BankAccount bankAccount)throws BusinessException;
	public int addNewBankAccountToApprovalTable(User user, BankAccount bankAccount) throws BusinessException;
	public int recordTransaction(BankTransaction transactionObj) throws BusinessException;

	//UPDATE operation
	public int updateBankAccount(User user, BankAccount bankAccount)throws BusinessException;
	//not sure if it cannot be used just one method, though
	public int withdrawFundsTransaction(BankAccount bankAccount, double amountToWithdraw, 
			int userId) throws BusinessException;
	public int depositFundsTransaction(BankAccount bankAccount, double amountToDdeposit, 
			int userId) throws BusinessException;
	
	//DELETE operation
	public void closeBankAccount(int bankAccounId, int userId)throws BusinessException;
	
	//READ operation
	public BankAccount getBankAccountById(int bankAccountId) throws BusinessException;
	public boolean isBankAccountDuplicate(long bankAccountNumber) throws BusinessException;
	public List<BankAccount> getBankAccountsListByUser(User userSession) throws BusinessException;
	public List<BankAccount> getBankAccountsListByUser(int userId) throws BusinessException;
	public List<BankTransaction> getAllTransactions()  throws BusinessException;
	public List<BankAccount> getBankAccountsFromApprovalTable(boolean isBankAccountApprovalPending) 
			throws BusinessException;
	public BankAccount getBankAccountByNumber(Long selectedDestinationBankAccountNumber) throws BusinessException;
	public int postTransferFundsTransaction(BankAccount selectedSourceBankAccount, double newBalanceToRecord,
			int userId) throws BusinessException;
	public int postTransferFundsTransaction(BankAccount selectedSourceBankAccount, double newBalanceToRecord) 
			throws BusinessException;
	public int recordTransferTransaction(BankTransaction transferFundsTransactionObj) throws BusinessException;
	public List<BankTransaction> getAllTransferTransactionsByRecipientUser(User userSession, 
			TransactionType transactionType, boolean isTransactionCleared) throws BusinessException;
	public BankTransaction getTransferTransactionById(int selectedTransferTransactionId) throws BusinessException;
	public int updateTransferTransactionClearingStatus(BankTransaction selectedTransferTransaction,
			boolean isTransactionCleared) throws BusinessException;
	public int updateTransferTransactionTypeAndClearingStatus(BankTransaction selectedTransferTransaction) throws BusinessException;
}