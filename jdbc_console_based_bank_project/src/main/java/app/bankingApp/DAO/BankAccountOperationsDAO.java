package app.bankingApp.DAO;

import java.util.List;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
import app.bankingApp.model.User;

public interface BankAccountOperationsDAO 
{
	//CREATE operation
	public int createNewBankAccount(User user, BankAccount bankAccount)throws BusinessException;
	public int addNewBankAccountToApprovalTable(User user, BankAccount bankAccount) throws BusinessException;
	public int recordTransaction(BankTransaction transactionObj) throws BusinessException;

	//UPDATE operation
	public int updateBankAccount(User user, BankAccount bankAccount)throws BusinessException;
	public int transferFundsOut(BankAccount sourceBankAccount, BankAccount destinationBankAccount, double transferAmount, int userId) throws BusinessException;
	public int transferFundsIn(BankAccount sourceBankAccount, BankAccount destinationBankAccount, double transferAmount, int userId) throws BusinessException;
	//not sure if it cannot be used just one method, though
	public int withdrawFundsTransaction(BankAccount bankAccount, double amountToWithdraw, int userId) throws BusinessException;
	public int depositFundsTransaction(BankAccount bankAccount, double amountToDdeposit, int userId) throws BusinessException;
	
	//DELETE operation
	public void closeBankAccount(int bankAccounId, int userId)throws BusinessException;
	
	//READ operation
	public BankAccount getBankAccountById(int bankAccountId) throws BusinessException;
	public Double getAccountBalance(int bankAccountId, int userId) throws BusinessException;
	public List<BankAccount> getAccountsList(int userId) throws BusinessException;
	public List<BankAccount> getAllBankAccountsList() throws BusinessException;
	public boolean isBankAccountDuplicate(long bankAccountNumber) throws BusinessException;
	public List<BankAccount> getBankAccountByUser(User userSession) throws BusinessException;
	public List<BankAccount> getBankAccountByUser(int userId) throws BusinessException;
	public List<BankTransaction> getAllTransactions()  throws BusinessException;
}