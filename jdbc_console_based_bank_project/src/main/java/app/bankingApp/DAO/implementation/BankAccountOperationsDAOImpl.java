package app.bankingApp.DAO.implementation;

import java.util.List;

import app.bankingApp.DAO.BankAccountOperationsDAO;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.User;

public class BankAccountOperationsDAOImpl implements BankAccountOperationsDAO 
{

	@Override
	public int createBankAccount(User user) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBankAccount(User user) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BankAccount getBankAccountById(int id) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transferFundsOut(int sourceBankAccounId, int destinationBankAccounId, Double transferAmount, int userId)
			throws BusinessException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferFundsIn(int sourceBankAccounId, int destinationBankAccounId, Double transferAmount, int userId)
			throws BusinessException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdrawFunds(int bankAccounID, Double withdrawAmount, int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void depositFunds(int bankAccounID, Double deposit, int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeBankAccount(int bankAccounId, int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double getAccountBalance(int bankAccountId, int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> getAccountsList(int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
