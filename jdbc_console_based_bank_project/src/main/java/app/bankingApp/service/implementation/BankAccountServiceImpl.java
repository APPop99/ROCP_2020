package app.bankingApp.service.implementation;

import java.util.List;

import org.apache.log4j.Logger;

import app.bankingApp.DAO.BankAccountOperationsDAO;
import app.bankingApp.DAO.UserOperationsDAO;
import app.bankingApp.DAO.implementation.BankAccountOperationsDAOImpl;
import app.bankingApp.DAO.implementation.UserOperationsDAOImpl;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.User;
import app.bankingApp.presenter.implementation.MainMenuPresenterImpl;
import app.bankingApp.service.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);

	//instantiate an object that will be used (through its methods) to transfer data to and from Data Access layer 
	private BankAccountOperationsDAO bankAccountDAO = new BankAccountOperationsDAOImpl();  

	@Override
	public int createNewBankAccount(User user, BankAccount bankAccount) throws BusinessException 
	{
		// System.out.println("Here is the CreateNewBank method from Service layer");
		log.info("Here is the CreateNewBank method from Service layer");
		int c = bankAccountDAO.createNewBankAccount(user, bankAccount);		
		return c;
	}

	@Override
	public int updateBankAccount(User user, BankAccount bankAccount) throws BusinessException 
	{
//		System.out.println("Here is the Update Bank Account approval status method from Service layer");
		log.info("Here is the Update Bank Account approval status method from Service layer");
		int c = bankAccountDAO.updateBankAccount(user, bankAccount);		
		return c;
	}

	@Override
	public void transferFundsOut(int sourceBankAccounId, int destinationBankAccounId, Double transferAmount, int userId)
			throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferFundsIn(int sourceBankAccounId, int destinationBankAccounId, Double transferAmount, int userId)
			throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdrawFunds(int bankAccounID, Double withdrawAmount, int userId) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void depositFunds(int bankAccounID, Double deposit, int userId) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeBankAccount(int bankAccounId, int userId) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BankAccount getBankAccountById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getAccountBalance(int bankAccountId, int userId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> getAccountsList(int userId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> getAllBankAccountsList() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBankAccountDuplicate(long bankAccountNumber) throws BusinessException 
	{
//		System.out.println("Here is the isBankAccountDuplicate Method from Account Service layer");
		log.info("Here is the isBankAccountDuplicate Method from Account Service layer");		
		return bankAccountDAO.isBankAccountDuplicate(bankAccountNumber);
	}

	@Override
	public int addNewBankAccountToApprovalTable(User user, BankAccount bankAccount) throws BusinessException 
	{
//		System.out.println("Here is the <Add New Bank Account To Approval Table> Method from Service layer");
		log.info("Here is the <Add New Bank Account To Approval Table> Method from Service layer");
		int c = bankAccountDAO.addNewBankAccountToApprovalTable(user, bankAccount);		
		return c;
	}

	@Override
	public List<BankAccount> getBankAccountsFromApprovalTable(boolean bankAccountApprovalPendingStatus)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount getUserById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
