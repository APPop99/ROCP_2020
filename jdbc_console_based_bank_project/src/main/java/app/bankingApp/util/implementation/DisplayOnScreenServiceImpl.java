package app.bankingApp.util.implementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
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

}
