package app.bankingApp.service.implementation;

import java.util.List;

import org.apache.log4j.Logger;

import app.bankingApp.DAO.BankAccountOperationsDAO;
//import app.bankingApp.DAO.UserOperationsDAO;
import app.bankingApp.DAO.implementation.BankAccountOperationsDAOImpl;
//import app.bankingApp.DAO.implementation.UserOperationsDAOImpl;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
import app.bankingApp.model.TransactionType;
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
//		System.out.println("Here is the CreateNewBank method from Service layer");
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
	public BankAccount getBankAccountById(int id) throws BusinessException 
	{
		BankAccount bankAccountById = null;
		bankAccountById = bankAccountDAO.getBankAccountById(id);
		return bankAccountById;	
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
	public List<BankAccount> getBankAccountsFromApprovalTable(boolean isBankAccountApprovalPending)
			throws BusinessException 
	{
		//	System.out.println("Here is the getBankAccountsFromApprovalTable Method from Service layer");
		log.info("Here is the getBankAccountsFromApprovalTable Method from Service layer");		
		List<BankAccount> bankAccountsToBeApprovedList = null;
		bankAccountsToBeApprovedList = bankAccountDAO.getBankAccountsFromApprovalTable(isBankAccountApprovalPending);
		return bankAccountsToBeApprovedList;
	}

	@Override
	public List<BankAccount> getBankAccountsListByUser(User userSession) throws BusinessException 
	{
		List<BankAccount> bankAccountsListByUser = null;
		bankAccountsListByUser = bankAccountDAO.getBankAccountsListByUser(userSession);
		return bankAccountsListByUser;	
	}

	@Override
	public List<BankAccount> getBankAccountsListByUser(int userId) throws BusinessException 
	{
		List<BankAccount> bankAccountsListByUser = null;
		bankAccountsListByUser = bankAccountDAO.getBankAccountsListByUser(userId);
		return bankAccountsListByUser;	
	}

	@Override
	public int withdrawFundsTransaction(BankAccount selectedBankAccount, Double newBalanceToRecord, int userId)
			throws BusinessException 
	{
		// System.out.println("Here is the withdrawFundsTransaction method from Service layer");
		log.info("Here is the withdrawFundsTransaction method from Service layer");
		
		int c = bankAccountDAO.withdrawFundsTransaction(selectedBankAccount, newBalanceToRecord, userId);		
		return c;
	}

	@Override
	public int depositFundsTransaction(BankAccount selectedBankAccount, Double newBalanceToRecord, int userId)
			throws BusinessException 
	{
		// System.out.println("Here is the depositFundsTransaction method from Service layer");
		log.info("Here is the depositFundsTransaction method from Service layer");
		
		int c = bankAccountDAO.depositFundsTransaction(selectedBankAccount, newBalanceToRecord, userId);		
		return c;
	}

	@Override
	public int recordTransaction(BankTransaction transactionObj) throws BusinessException 
	{
//		System.out.println("Here is the depositFundsTransaction method from Service layer");
		log.info("Here is the recordTransaction method from Service layer");
		
		int c = bankAccountDAO.recordTransaction(transactionObj);		
		return c;
	}

	@Override
	public List<BankTransaction> getAllTransactions() throws BusinessException 
	{
//		System.out.println("Here is the getAllTransactions Method from Service layer");
		log.info("Here is the getAllTransactions Method from Service layer");	
		
		List<BankTransaction> bankTransactionsListAll = null;
		bankTransactionsListAll = bankAccountDAO.getAllTransactions();
		return bankTransactionsListAll;	
	}

	@Override
	public BankAccount getBankAccountByNumber(Long selectedDestinationBankAccountNumber) throws BusinessException 
	{
		BankAccount bankAccountByNumber = null;
		bankAccountByNumber = bankAccountDAO.getBankAccountByNumber(selectedDestinationBankAccountNumber);
		return bankAccountByNumber;		
	}

	@Override
	public int postTransferFundsTransaction(BankAccount selectedSourceBankAccount, double newBalanceToRecord, int userId) 
			throws BusinessException
	{
		// System.out.println("Here is the postTransferFundsTransaction method from Service layer");
		log.info("Here is the postTransferFundsTransaction method from Service layer");
		
		int c = bankAccountDAO.postTransferFundsTransaction(selectedSourceBankAccount, newBalanceToRecord, userId);		
		return c;
	}

	@Override
	public int postTransferFundsTransaction(BankAccount selectedSourceBankAccount, double newBalanceToRecord) 
			throws BusinessException
	{
		// System.out.println("Here is the postTransferFundsTransaction method from Service layer");
		log.info("Here is the postTransferFundsTransaction method from Service layer");
		
		int c = bankAccountDAO.postTransferFundsTransaction(selectedSourceBankAccount, newBalanceToRecord);		
		return c;
	}

	@Override
	public int recordTransferTransaction(BankTransaction transferFundsTransactionObj) throws BusinessException 
	{
//		System.out.println("Here is the depositFundsTransaction method from Service layer");
		log.info("Here is the recordTransaction method from Service layer");
		
		int c = bankAccountDAO.recordTransferTransaction(transferFundsTransactionObj);		
		return c;
	}

	@Override
	public List<BankTransaction> getAllTransferTransactionsByRecipientUser(User userSession, 
			TransactionType transactionType, boolean isTransactionCleared) throws BusinessException 
	{
//		System.out.println("Method <getAllTransferTransactionsByRecipientUser> from Service layer");
		log.info("Method <getAllTransferTransactionsByRecipientUser> from Service layer");
		
		List<BankTransaction> transferTransactionsListByRecipientUser = null;
		transferTransactionsListByRecipientUser = bankAccountDAO.getAllTransferTransactionsByRecipientUser(userSession, 
				transactionType, isTransactionCleared);
		return transferTransactionsListByRecipientUser;
	}

	@Override
	public BankTransaction getTransferTransactionById(int selectedTransferTransactionId) throws BusinessException 
	{
		log.info("Here is the getTransferTransactionById method from Service layer");
		BankTransaction bankTransferTransactionById = null;
		bankTransferTransactionById = bankAccountDAO.getTransferTransactionById(selectedTransferTransactionId);
		return bankTransferTransactionById;	
	}

	@Override
	public int updateTransferTransactionClearingStatus(BankTransaction selectedTransferTransaction, boolean isTransactionCleared)
			throws BusinessException 
	{
//		System.out.println("Here is the updateTransferTransactionClearingStatus method from Service layer");
		log.info("Here is the updateTransferTransactionClearingStatus method from Service layer");
		
		int c = bankAccountDAO.updateTransferTransactionClearingStatus(selectedTransferTransaction, isTransactionCleared);		
		return c;
	}

	@Override
	public int updateTransferTransactionTypeAndClearingStatus(BankTransaction selectedTransferTransaction) 
			throws BusinessException 
	{
//		System.out.println("Here is the updateTransferTransactionTypeAndClearingStatus method from Service layer");
		log.info("Here is the updateTransferTransactionTypeAndClearingStatus method from Service layer");
		
		int c = bankAccountDAO.updateTransferTransactionTypeAndClearingStatus(selectedTransferTransaction);		
		return c;
	}

	@Override
	public void closeBankAccount(int bankAccounId, int userId) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
}