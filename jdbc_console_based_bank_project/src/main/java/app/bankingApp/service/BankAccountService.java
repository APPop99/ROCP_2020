package app.bankingApp.service;

import java.util.List;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
import app.bankingApp.model.User;
import app.bankingApp.model.TransactionType;

public interface BankAccountService 
{
	//CREATE operation
	public int createNewBankAccount(User user, BankAccount bankAccount)throws BusinessException;
	public int addNewBankAccountToApprovalTable(User user, BankAccount bankAccount) throws BusinessException;
	public int recordTransaction(BankTransaction transactionObj) throws BusinessException;

	//UPDATE operation
	public int updateBankAccount(User user, BankAccount bankAccount)throws BusinessException;
	public int transferFundsOut(BankAccount sourceBankAccount, BankAccount destinationBankAccount, Double amountToTransfer, int userId) throws BusinessException;
	public int transferFundsIn(BankAccount sourceBankAccount, BankAccount destinationBankAccount, Double amountToTransfer, int userId) throws BusinessException;
	//not sure if it cannot be used just one method, though
	public int withdrawFundsTransaction(BankAccount selectedBankAccount, Double amountToWithdraw, int userId) throws BusinessException;
	public int depositFundsTransaction(BankAccount selectedBankAccount, Double amountToDeposit, int userId) throws BusinessException;
	
	//DELETE operation
	public void closeBankAccount(int bankAccounId, int userId)throws BusinessException;
	
	//READ operation
	public BankAccount getBankAccountById(int bankAccountId) throws BusinessException;
	public Double getAccountBalance(int bankAccountId, int userId) throws BusinessException;
	public List<BankAccount> getAccountsList(int userId) throws BusinessException;
	public List<BankAccount> getAllBankAccountsList() throws BusinessException;
	public boolean isBankAccountDuplicate(long bankAccountNumber) throws BusinessException;
	public List<BankAccount> getBankAccountsFromApprovalTable(boolean bankAccountApprovalPendingStatus)
			throws BusinessException;
	public List<BankAccount> getBankAccountsListByUser(User userSession) throws BusinessException;
	public List<BankAccount> getBankAccountsListByUser (int userId) throws BusinessException;
	public List<BankTransaction> getAllTransactions() throws BusinessException;
	public BankAccount getBankAccountByNumber(Long selectedDestinationBankAccountNumber) throws BusinessException;
	public int postTransferFundsTransaction(BankAccount selectedSourceBankAccount, 
			double newBalanceToRecord, int id) throws BusinessException;
	public int recordTransferTransaction(BankTransaction transferFundsTransactionObj) throws BusinessException;
	public List<BankTransaction> getAllTransferTransactionsByRecipientUser(User userSession, 
			TransactionType transactionType, boolean isTransactionCleared) throws BusinessException;
	public BankTransaction getTransferTransactionById(int selectedTransferTransactionId) throws BusinessException;
	public int updateTransferTransactionClearingStatus(BankTransaction selectedTransferTransaction, 
			boolean isTransactionCleared) throws BusinessException;
}
