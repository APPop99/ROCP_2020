package app.bankingApp.service.implementation;

import java.util.List;

import org.apache.log4j.Logger;

import app.bankingApp.DAO.BankAccountOperationsDAO;
import app.bankingApp.DAO.UserOperationsDAO;
import app.bankingApp.DAO.implementation.BankAccountOperationsDAOImpl;
import app.bankingApp.DAO.implementation.UserOperationsDAOImpl;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
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
	public void closeBankAccount(int bankAccounId, int userId) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BankAccount getBankAccountById(int id) throws BusinessException 
	{
		BankAccount bankAccountsListById = null;
		bankAccountsListById = bankAccountDAO.getBankAccountById(id);
		return bankAccountsListById;	
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

	@Override
	public List<BankAccount> getBankAccountByUser(User userSession) throws BusinessException 
	{
		List<BankAccount> bankAccountsListByUser = null;
		bankAccountsListByUser = bankAccountDAO.getBankAccountByUser(userSession);
		return bankAccountsListByUser;	
	}

	@Override
	public List<BankAccount> getBankAccountByUser(int userId) throws BusinessException 
	{
//		List<BankAccount> bankAccountsListByUser = null;
//		bankAccountsListByUser = bankAccountDAO.getBankAccountByUser(userId);
//		return bankAccountsListByUser;	
		return null;
	}

	@Override
	public int transferFundsOut(BankAccount sourceBankAccount, BankAccount destinationBankAccount,
			Double amountToTransfer, int userId) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int transferFundsIn(BankAccount sourceBankAccount, BankAccount destinationBankAccount,
			Double amountToTransfer, int userId) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int withdrawFundsTransaction(BankAccount selectedBankAccount, Double amountToWithdraw, int userId)
			throws BusinessException 
	{
		// System.out.println("Here is the withdrawFundsTransaction method from Service layer");
		log.info("Here is the withdrawFundsTransaction method from Service layer");
		
		int c = bankAccountDAO.withdrawFundsTransaction(selectedBankAccount, amountToWithdraw, userId);		
		return c;
	}

	@Override
	public int depositFundsTransaction(BankAccount selectedBankAccount, Double amountToDeposit, int userId)
			throws BusinessException 
	{
		// System.out.println("Here is the depositFundsTransaction method from Service layer");
		log.info("Here is the depositFundsTransaction method from Service layer");
		
		int c = bankAccountDAO.depositFundsTransaction(selectedBankAccount, amountToDeposit, userId);		
		return c;
	}

	@Override
	public int recordTransaction(BankTransaction transactionObj) throws BusinessException 
	{
		// System.out.println("Here is the depositFundsTransaction method from Service layer");
		log.info("Here is the recordTransaction method from Service layer");
		
		int c = bankAccountDAO.recordTransaction(transactionObj);		
		return c;
	}

	@Override
	public List<BankTransaction> getAllTransactions() throws BusinessException 
	{
		List<BankTransaction> bankTransactionsListAll = null;
		bankTransactionsListAll = bankAccountDAO.getAllTransactions();
		return bankTransactionsListAll;	
	}
}