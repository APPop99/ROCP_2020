package app.bankingApp.util.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
import app.bankingApp.model.StatusAccount;
import app.bankingApp.model.TransactionType;
import app.bankingApp.model.User;
import app.bankingApp.presenter.implementation.MainMenuPresenterImpl;
import app.bankingApp.service.BankAccountService;
import app.bankingApp.service.implementation.BankAccountServiceImpl;
import app.bankingApp.util.ValidatorUtil;

public class ValidatorUtilImpl implements ValidatorUtil 
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);
	private final Scanner scannerValidatorUtil = new Scanner(System.in);
    
//	private Pattern pattern;
//  private Matcher matcher;
    private final String EMAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    private final String MAIN_MENU_CHOICE_PATTERN ="^[1-3]";
//    private final String PHONE_NUMBER_PATTERN = 
//    		"^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
//    		+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
//    		+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$"
//    		+ "|^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
    
//	public ValidatorServiceImpl()
//	{
//        pattern = Pattern.compile(EMAIL_PATTERN);
//	}

    public boolean isMainMenuChoiceValid(final String choice)
	{
//        matcher = pattern.matcher(email);
//        return matcher.matches();		
			
		//Test if the format of email is valid, as it is used as username	
		if (choice.matches(MAIN_MENU_CHOICE_PATTERN))
		{
			return true;
		} 
		else 
		{
			System.out.println("\nPlease enter 1, 2 or 3 to continue!");
			log.warn("\nPlease enter 1, 2 or 3 to continue!");
			return false;
		}
	}
    
	@Override
	public boolean isPhoneNumberFormatValid(String phoneNumberAsString) 
	{
		//validate phone numbers of format "1234567890"
		if (phoneNumberAsString.matches("\\d{10}")) 
				return true;
		//validating phone number with -, . or spaces
		else 
			if(phoneNumberAsString.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) 
				return true;
		//validating phone number with extension length from 3 to 5
			else 
				if(phoneNumberAsString.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) 
					return true;	
		//validating phone number where area code is in braces ()
				else 
					if(phoneNumberAsString.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) 
						return true;
					else
						if (phoneNumberAsString.matches("\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}"))
							return true;
		//return false if nothing matches the input
		else 
			return false;
	}
    
    public boolean isEmailValid(final String email)
	{
//        matcher = pattern.matcher(email);
//        return matcher.matches();		
			
		//Test if the format of email is valid, as it is used as username	
		if (email.matches(EMAIL_PATTERN))
		{
			//System.out.println("Email format is valid!");
			log.info("Email format is valid");
			return true;
		} 
		else 
		{
			System.out.println("Email format is not valid. Please enter an email with a valid format!");
			log.warn("Email format is not valid. Please enter an email with a valid format!");
			return false;
		}
	}
	
	public boolean isPasswordValid(final String password)
	{
		if (password.matches(PASSWORD_PATTERN)) 
		{
			System.out.println("Password is valid!");
			log.info("Password is valid");
			return true;
		} 
		else 
		{
			System.out.println("Password is not valid!");
			log.warn("Password is not valid! Please enter a password with a valid format!");
			return false;
		}
	}
	
	public Date dateParsing(String dobString)
	{
		SimpleDateFormat sdfDobString = new SimpleDateFormat("dd/MM/yyyy");
		sdfDobString.setLenient(false);
		Date dob=null;
		
		try 
		{
			dob = sdfDobString.parse(dobString);
		} 
			catch (ParseException e1) 
		{
//				System.out.println(e1);
				log.error(e1);
		}
		return dob;
	}
	
	public BankAccount verifySelectedBankAccount(User sessionUser, List<BankAccount> bankAccountsListByUser)
	{	
		//several actions performed in this method can be split into smaller methods, assuring re-usability...
		
		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();
		//CustomerMenuPresenter depositFundsIntoBankAccount = new CustomerMenuPresenterImpl(userSession);
//		List<BankAccount> bankAccountsListByUser = null;

		int selectedBankAccountId = 0;
		BankAccount selectedBankAccount;
//		boolean isUserOwnerOfBankAccount = false;
		boolean isBankAccountApproved = false;

		try
		{
			do
			{
				do //test if the selected bank account exists
				{		
					System.out.println("Select the bank account where you want to operate the transaction: ");
					selectedBankAccountId = Integer.parseInt(scannerValidatorUtil.nextLine());
	
					//Code Here for SERVICE LAYER
					//test if the chosen bank account exists;
					//Step 1: retrieve all bank accounts owned by the User
					selectedBankAccount = bankAccountService.getBankAccountById(selectedBankAccountId);
					if (selectedBankAccount == null)
					{
						System.out.println("This account does not exists.\n"
								+ "Please select an APPROVED Bank Account from the list of bank accounts you own!");
					}
				} while (selectedBankAccount == null);
									
				if (selectedBankAccount.getAccountOwnerId() == sessionUser.getId())
				{	
					if (selectedBankAccount.getStatusBankAccount() == StatusAccount.APPROVED)
					{
						System.out.println("\nThe selected Bank Account has Id: " + selectedBankAccount.getBankAccountId() 
						+ " | Owner id: " + selectedBankAccount.getAccountOwnerId()
						+ " | Status: " + selectedBankAccount.getStatusBankAccount());
						isBankAccountApproved = true;
						return selectedBankAccount;
					}
					else
					{
						System.out.println("Your bank account has STATUS: " + selectedBankAccount.getStatusBankAccount() + " and cannot be used.\n"
								+ "Please select an APPROVED Bank Account from the list of bank accounts you own!");
						isBankAccountApproved = false;
					}
				}	
				else
				{
					System.out.println("You are not the owner of this Bank Account. \n"
							+ "Please select an APPROVED Bank Account from the list of bank accounts you own!");
				}
			} while(selectedBankAccount.getAccountOwnerId() != sessionUser.getId() || isBankAccountApproved != true);
			
			//Tried to use it but didn't work 
//			if (bankAccountsListByUser.contains(selectedBankAccount))
//			{
//				choiceBankAcctId = true;
//			}
//			scannerDepositFunds.close();
		} 
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
//			scannerDepositFunds.close();
		}
		return null;
	}
	
	public boolean isLoginSuccessful(String email, String password, List<User> userList) 
	{
		for(User user : userList) 
		{
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) 
			{
				return true;
			}
		}
		System.out.println("Login failed. Credentials are not correct. Please try again.\n");
		log.warn("Login failed. Credentials are not correct. Please try again.\n");
		return false;
	}
	
	public User selectUser(String email, String password, List<User> userList) 
	{
		for(User user : userList) 
		{
			if (user.getEmail().equals(email) && user.getPassword().equals(password))
			{
				return user;
			}
		}
		return null;
	}

	@Override
	public BankTransaction verifySelectedTransferTransaction(User userSession,
			List<BankTransaction> tempTransferBankTransactionsListByUser) 
	{
//		System.out.println("Method <verifySelectedTransferTransaction> is running!");		
		//several actions performed in this method can be split into smaller methods, assuring re-usability...
		
		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();
		//CustomerMenuPresenter depositFundsIntoBankAccount = new CustomerMenuPresenterImpl(userSession);
//		List<BankAccount> bankAccountsListByUser = null;

		int selectedTransferTransactionId = 0;
		BankTransaction selectedTransferTransaction;
//		boolean isUserOwnerOfBankAccount = false;
		boolean isTransferTransactionOK = false;

		try
		{
			do
			{
				do //test if the selected bank account exists
				{		
					System.out.println("Select the transaction you want to evaluate: ");
					selectedTransferTransactionId = Integer.parseInt(scannerValidatorUtil.nextLine());
	
					//Code Here for SERVICE LAYER
					//test if the chosen bank account exists;
					//Step 1: retrieve all bank accounts owned by the User
					selectedTransferTransaction = bankAccountService.getTransferTransactionById(selectedTransferTransactionId);
					if (selectedTransferTransaction == null)
					{
						System.out.println("This transaction does not exists.\n"
								+ "Please select a transaction from the list of transaction you are involved in!");
					}
				} while (selectedTransferTransaction == null);
									
				if (selectedTransferTransaction.getUserRecipient().getId() == userSession.getId())
				{	
					if (selectedTransferTransaction.getTransactionType() == TransactionType.POST_FUND_TRANSFER)
					{
						System.out.println("\nThe selected transfer transaction has Id: " + selectedTransferTransaction.getIdTransaction() 
						+ " | Sender: " + selectedTransferTransaction.getUserSender().getFirstName() + " " 
								+ selectedTransferTransaction.getUserSender().getLastName()
						+ " | Amount: " + selectedTransferTransaction.getAmount());
						isTransferTransactionOK = true;
						return selectedTransferTransaction;
					}
					else
					{
						System.out.println("The transaction you selected cannot be processed.\n"
								+ "Please select a transaction from the list of transaction you are involved in!");
						isTransferTransactionOK = false;
					}
				}	
				else
				{
					System.out.println("You are not the recipient of the selected transfer transaction.\n"
							+ "Please select a transaction from the list of transaction you are involved in!");
					isTransferTransactionOK = false;
				}
			} while(selectedTransferTransaction.getUserRecipient().getId() != userSession.getId() || (isTransferTransactionOK != true));
		} 
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
//			scannerDepositFunds.close();
		}
		return null;
	}
}