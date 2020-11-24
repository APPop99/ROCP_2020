package app.bankingApp.util.implementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
import app.bankingApp.model.User;
import app.bankingApp.presenter.implementation.MainMenuPresenterImpl;
import app.bankingApp.util.DisplayOnScreenService;

public class DisplayOnScreenServiceImpl implements DisplayOnScreenService
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);

	@Override
	public void printListOfBankAccountsByUser(List<BankAccount> bankAccountsListByUser, User userSession)
			throws BusinessException 
	{
		if(bankAccountsListByUser!=null && bankAccountsListByUser.size()>0)
		{
			System.out.println("\nWe found " + bankAccountsListByUser.size()+" bank accounts opened by the User: " 
					+ userSession.getFirstName() + " " + userSession.getLastName() + "... Detailed list is:");
			log.info("We found " + bankAccountsListByUser.size()+" bank accounts opened by the User: " 
					+ userSession.getFirstName() + " " + userSession.getLastName() + "... Detailed list is:");
			
			for(BankAccount ba:bankAccountsListByUser)
			{
				Date date = new Date(ba.getDateBankAccountCreation().getTime());
				SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMMM yyyy"); 

				System.out.println("Bank Account Id: " + ba.getBankAccountId() 
					+ " | Bank Account Number: " + ba.getBankAccountNumber() 
					+ " | Bank Owner id: " + ba.getAccountOwnerId()
					+ " | Bank Account Balance: " + ba.getAccountBalance()
					+ " | Account active since: " + formatter.format(date)
					+ " | Bank Account status: " + ba.getStatusBankAccount());
				log.info("Bank Account Id: " + ba.getBankAccountId() 
					+ " | Bank Account Number: " + ba.getBankAccountNumber() 
					+ " | Bank Owner id: " + ba.getAccountOwnerId()
					+ " | Bank Account Balance: " + ba.getAccountBalance()
					+ " | Account active since: " + formatter.format(date)
					+ " | Bank Account status: " + ba.getStatusBankAccount());
			}
		}		
	}

	@Override
	public void printListOfTransferTransactionsByRecipientUser(List<BankTransaction> tempTransferBankTransactionsListByRecipientUser,
			User userSession) throws BusinessException 
	{
//		System.out.println("Method <printListOfTransferTransactionsByRecipientUser> is running now!");
		
		if(tempTransferBankTransactionsListByRecipientUser!=null && tempTransferBankTransactionsListByRecipientUser.size()>0)
		{
			System.out.println("\nWe found " + tempTransferBankTransactionsListByRecipientUser.size()+" transactions for the User: " 
					+ userSession.getFirstName() + " " + userSession.getLastName() + "... Detailed list is:");
			log.info("We found " + tempTransferBankTransactionsListByRecipientUser.size()+" transactions for the User: " 
					+ userSession.getFirstName() + " " + userSession.getLastName() + "... Detailed list is:");
			
			for(BankTransaction bt:tempTransferBankTransactionsListByRecipientUser)
			{
//				System.out.println("Bank Transaction: " + bt);
				Date date = new Date(bt.getTransactionDate().getTime());
				SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMMM yyyy"); 

				System.out.println("Transaction Id: " + bt.getIdTransaction()
					+ " | Source Bank Account Id: " + bt.getSourceBankAccountId() 
					+ " | Bank Account Number: " + bt.getSourceBankAccount().getBankAccountNumber() 
					+ " | Sender: " + bt.getUserSender().getFirstName() + " " + bt.getUserSender().getLastName()
					+ " | Transfer Amount: " + bt.getAmount()
					+ " | Description: " + bt.getTransactionDescription()
					+ " | Clearance Status: " + bt.isTransactionCleared() 
					+ " | Transfer Date: " + formatter.format(date));
				log.info("Transaction Id: " + bt.getIdTransaction()
					+ "	| Source Bank Account Id: " + bt.getSourceBankAccountId() 
					+ " | Bank Account Number: " + bt.getSourceBankAccount().getBankAccountNumber() 
					+ " | Sender: " + bt.getUserSender().getFirstName() + " " + bt.getUserSender().getLastName()
					+ " | Transfer Amount: " + bt.getAmount()
					+ " | Clearance Status: " + bt.isTransactionCleared()
					+ " | Description: " + bt.getTransactionDescription()
					+ " | Transfer Date: " + formatter.format(date));
			}
		}	
	}
}