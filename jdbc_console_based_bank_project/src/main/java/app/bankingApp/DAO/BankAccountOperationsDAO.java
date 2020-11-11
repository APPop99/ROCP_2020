package app.bankingApp.DAO;

import java.util.List;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.User;

public interface BankAccountOperationsDAO 
{
	//CREATE operation
	public int createBankAccount(User user)throws BusinessException;
	
	//UPDATE operation
	public int updateBankAccount(User user)throws BusinessException;
	public void transferFundsOut(int sourceBankAccounId, int destinationBankAccounId, Double transferAmount, int userId) throws BusinessException;
	public void transferFundsIn(int sourceBankAccounId, int destinationBankAccounId, Double transferAmount, int userId) throws BusinessException;
	//not sure if it cannot be used just one method, though
	public void withdrawFunds(int bankAccounID, Double withdrawAmount, int userId) throws BusinessException;
	public void depositFunds(int bankAccounID, Double deposit, int userId) throws BusinessException;
	
	//DELETE operation
	public void closeBankAccount(int bankAccounId, int userId)throws BusinessException;
	
	//READ operation
	public BankAccount getBankAccountById(int id) throws BusinessException;
	public Double getAccountBalance(int bankAccountId, int userId) throws BusinessException;
	public List<BankAccount> getAccountsList(int userId) throws BusinessException;
}
