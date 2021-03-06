package app.bankingApp.presenter.implementation;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

import org.apache.log4j.Logger;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
import app.bankingApp.model.StatusAccount;
import app.bankingApp.model.StatusUser;
import app.bankingApp.model.TransactionType;
import app.bankingApp.model.User;
import app.bankingApp.presenter.CustomerMenuPresenter;
import app.bankingApp.service.BankAccountService;
import app.bankingApp.service.UserService;
import app.bankingApp.service.implementation.BankAccountServiceImpl;
import app.bankingApp.service.implementation.UserServiceImpl;
import app.bankingApp.util.DisplayOnScreenService;
import app.bankingApp.util.ValidatorUtil;
import app.bankingApp.util.implementation.DisplayOnScreenServiceImpl;
import app.bankingApp.util.implementation.ValidatorUtilImpl;

public class CustomerMenuPresenterImpl implements CustomerMenuPresenter
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);
	private final Scanner scannerCustomerMenu = new Scanner(System.in);
	
	public CustomerMenuPresenterImpl(User userLogin) 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showCustomerMenu(User userSession) 
	{
		System.out.println("Customer Menu!");
		System.out.println("Allows a logged in Customer to operate the apps' features!\n");
		
		System.out.println(userSession.getFirstName() + " " + userSession.getLastName() + ": Welcome to Console based Bank / Customer Menu!");
		System.out.println("----------------------------------------------");
		
		int choice = 0;
		
		try
		{
			do 
			{
				System.out.println("Console Bank CUSTOMER MENU");
				System.out.println("==========================");
				System.out.println("Please select your option:");
				System.out.println("--------------------------\n");
				System.out.println("1) Apply for a single Bank Account");
	//			System.out.println("2) Apply for a joint Bank Account"); //(?optional)
				System.out.println("2) View the balance of the selected Bank account");
				System.out.println("3) Make a deposit into the selected Bank Account");			
				System.out.println("4) Make a withdrawal from the selected Bank Account");
				System.out.println("5) Post a money transfer from the selected Bank Account");
				System.out.println("6) Accept a transfer into the selected Bank Account");
				System.out.println("7) Returning to previous menu");
				System.out.println("Please enter appropriate choice(1-7) :) ");

				choice = Integer.parseInt(scannerCustomerMenu.nextLine());

				switch (choice)
				{
					case 1:
						//apply for a bank account 
	//					System.out.println("Method <New Bank Account Registration> will create a new Bank Account and submit it for Bank's approval!");
						log.info("Method <New Bank Account Registration> will create a new Bank Account and submit it for Bank's approval!");
						CustomerMenuPresenter createBankAccount = new CustomerMenuPresenterImpl(userSession);
						createBankAccount.createNewBankAccount(userSession);
						break;
					case 2:
						//view the balance of the accounts
	//					System.out.println("Method <View Bank Account Balance> allows User to see Bank Account balance!");
						log.info("Method <View Bank Account Balance> allows User to see Bank Account balance!");
						CustomerMenuPresenter viewBankAccountBalance = new CustomerMenuPresenterImpl(userSession);
						viewBankAccountBalance.getBankAccountBalance(userSession);					
						break;
					case 3:
						//make a deposit into a specific account (reject invalid transactions)
	//					System.out.println("Method <Deposit amount in Bank Account> allows a Customer to deposit funds into a selected Bank Account he/she owns!");
						log.info("Method <Deposit amount in Bank Account> allows a Customer to deposit funds into a selected Bank Account he/she owns!");
						CustomerMenuPresenter depositFundsIntoBankAccount = new CustomerMenuPresenterImpl(userSession);
						depositFundsIntoBankAccount.updateDepositFundsIntoBankAccount(userSession);						
						break;
					case 4:
						//make a withdrawal from a specific account (reject invalid transactions)
	//					System.out.println("Method <Withdraw amount in Bank Account> allows a Customer to withdraw funds into a selected Bank Account he/she owns!");
						log.info("Method <Withdraw amount in Bank Account> allows a Customer to withdraw funds into a selected Bank Account he/she owns!");
						CustomerMenuPresenter withdrawFundsFromBankAccount = new CustomerMenuPresenterImpl(userSession);
						withdrawFundsFromBankAccount.updateWithdrawFundsFromBankAccount(userSession);								
						break;
					case 5:
						//post a money transfer to another account
						//System.out.println("Method <postFundsTransferFromBankAccount> allows a Customer to send funds into another selected Bank Account!");
						log.info("Method <postFundsTransferFromBankAccount> allows a Customer to send funds into another selected Bank Account!");
						CustomerMenuPresenter postFundsTransferFromBankAccount = new CustomerMenuPresenterImpl(userSession);
						postFundsTransferFromBankAccount.updatePostFundsTransferFromBankAccount(userSession);
						break;
					case 6:
						//accept money transfer from another account
						//System.out.println("Method <postFundsTransferFromBankAccount> allows a Customer to send funds into another selected Bank Account!");
						log.info("Method <postFundsTransferFromBankAccount> allows a Customer to send funds into another selected Bank Account!");
						CustomerMenuPresenter acceptFundsTransferFromBankAccount = new CustomerMenuPresenterImpl(userSession);
						acceptFundsTransferFromBankAccount.updateAcceptFundsTransferIntoBankAccount(userSession);
						break;
					case 7: 
						//return to previous menu
//						scannerCustomerMenu.close();
						break;
					default:
						System.out.println("Please enter 1, 2, 3, 4, 5, 6 or 7 to continue!");
						break;
				}
			} while (choice !=7 );
		} 
		catch (NumberFormatException e) 
		{
			System.out.println("Data entered has an invalid format. Please enter a number between 1 and 7!");
		}
	}

	@Override
	public void showNonCustomerMenu(User userSession) 
	{
		//This method allows an User to register for a customer account in order to access other features of the app 

		System.out.println("New Customer Menu!");
		System.out.println("Allows a logged in User to apply for an approved Customer Account to use apps features!\n");
		
		System.out.println("Welcome to Console based Bank / New Registered User Menu!");
		System.out.println("---------------------------------------------------------");
		
		int choice = 0;
		
		try
		{
			do 
			{
				System.out.println("Console Bank NON CUSTOMER MENU");
				System.out.println("==============================");
				System.out.println("Please select your option:");
				System.out.println("--------------------------\n");
				System.out.println("1) New CUSTOMER registration request");
				System.out.println("2) Returning to previous menu");
				System.out.println("Please enter appropriate choice(1-2) :) ");
	
				choice = Integer.parseInt(scannerCustomerMenu.nextLine());
				
				switch (choice)
				{
					case 1:
						if (!userSession.getStatusUser().equals(StatusUser.CUSTOMER)) //even if it is very unlikely to have this case...
						{
							if(userSession.isCustomerApprovalPending() != true)
							{
								//Step 1: add all Users that requested to be approved into a temp table, that will be accessed by Employee responsible with approval process
								//Step 2: the Employee retrieves the User from the temp table and approves and denies their request to become Customers  
								System.out.println("Method <Add NonCustomer User To Approval Table> will register for Bank's reviewal process the User's request to become a new Customer!");
								log.info("Method <Add NonCustomer User To Approval Table> will register for Bank's reviewal process the User's request to become a new Customer!");
								CustomerMenuPresenter approveCustomer = new CustomerMenuPresenterImpl(userSession);
								approveCustomer.addNonCustomerUserToApprovalTable(userSession);
								break;
							}
							else
							{
								System.out.println("\nUser with " + userSession.getId() + " | "+userSession.getFirstName() 
								+ " "+userSession.getLastName()+" already submitted a request for Bank's approval for a Customer Acoount");
								log.info("User with " + userSession.getId() + " | "+userSession.getFirstName() 
								+ " "+userSession.getLastName()+" already submitted a request for Bank's approval for a Customer Acoount");
	
								break;
							}
						}
						else
						{
							System.out.println("\nUser with " + userSession.getId() + " | "+userSession.getFirstName() 
							+ " "+userSession.getLastName()+" already has Customer status");
							log.info("User with " + userSession.getId() + " | "+userSession.getFirstName() 
							+ " "+userSession.getLastName()+" already has Customer status");
							break;
						}
					case 2:
						//return to previous menu
						break;
					default:
						System.out.println("Please enter a number between 1 and 2!");
						break;
				}
			} while(choice != 2);
		}
		catch (NumberFormatException e) 
		{
			System.out.println("Data entered has an invalid format. Please enter a number between 1 and 7!");
		}
	}

	@Override
	public void addNonCustomerUserToApprovalTable(User userSession) 
	{
		//instantiate an object that will be used () to transfer data to and from Service layer 
		UserService userService = new UserServiceImpl();
		
		System.out.println("Please explicitly give us your acceptance for being approved as Customer:");
		
		String choiceStatus;
		boolean choiceBool = false;
		
		try 
		{
			do
			{
				System.out.println("Do you like to have your User account registered as Customer Account ? (Y/N)");
				choiceStatus = scannerCustomerMenu.nextLine();

				switch (choiceStatus.toUpperCase())
				{
					case "Y":
						//Code Here for SERVICE LAYER
						System.out.println("\nUser with id " + userSession.getId() + " will be saved in temp db waiting for approval.");
						log.info("User with id " + userSession.getId() + "will be saved in temp db waiting for approval.");
						userSession.setCustomerApprovalPending(true); 	//set value to true - Customer Account approval is pending
						userSession.setCustomerStatusApproved(false);	//set value to false - this User has not yet been approved as Customer 
						
						if (userService.addNonCustomerUserToApprovalTable(userSession)>0)
						{
							System.out.println("\nUser: "+ userSession.getId() + " | " + userSession.getFirstName() + " " 
									+ userSession.getLastName()+" was saved in temp db waiting for approval");
							log.info("User: "+ userSession.getId() + " | " + userSession.getFirstName() + " " 
									+ userSession.getLastName()+" was saved in temp db waiting for approval");				
						}
						choiceBool = true;
						break;
					case "N":
						choiceBool = true;
						break;
					default: 
						System.out.println("Please enter only 'Y' or 'N' to continue!");
						choiceBool = false;
						break;
				}	
			} while (choiceBool != true);	
		}
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
			log.info(e.getMessage());
		}
		System.out.println("");		
	}

	@Override
	public void createNewBankAccount(User userSession) 
	{
		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();
		
		//System.out.println("System will generate the details for the new Bank Account you wish to open:");
		log.info("System will generate the details for the new Bank Account you wish to open:");
		
		try 
		{
			//generated a 9-digits random number as Bank Account Number 
			//test with the database the uniqueness of the Bank Account number generated
			long bankAccountNumber = 0;		
			boolean isBankAccountNumberUnique = false;
			
			while(isBankAccountNumberUnique == false)
			{
				do
				{	//apply random function to generate a 9 digit bank account number;
					bankAccountNumber = (long)(Math.random()*1000000 + 100000000L);
//					System.out.println("Bank Account generated number is: " + bankAccountNumber);
					log.info("Bank Account number randomly generated is: " + bankAccountNumber);
				} while(bankAccountService.isBankAccountDuplicate(bankAccountNumber) != true);
				// I test if the generated number bank account is already in use 
				
				isBankAccountNumberUnique = true;
			}
			double bankAccountBalance = 0;
			int bankAccountOwnerId = userSession.getId();
			
			//Code Here for SERVICE LAYER
			BankAccount newBankAccount = new BankAccount(bankAccountNumber, bankAccountBalance, bankAccountOwnerId);
			
			StatusAccount statusBankAccount = StatusAccount.PENDING;
			newBankAccount.setStatusBankAccount(statusBankAccount);
			
			Timestamp dateBankAccountCreation = new Timestamp(System.currentTimeMillis());
			newBankAccount.setDateBankAccountCreation(dateBankAccountCreation); 			
			
			Timestamp dateBankAccountApproval = null; 
			newBankAccount.setDateBankAccountApproval(dateBankAccountApproval);
			
			Timestamp dateBankAccountDeletion = null;
			newBankAccount.setDateBankAccountDeletion(dateBankAccountDeletion);

			//maybe I can move this to a Display on Screen Service/Util
			if(bankAccountService.createNewBankAccount(userSession, newBankAccount) > 0)
			{
				System.out.println("\nA new bank account was generated for the User: " + userSession.getFirstName() + 
						" " + userSession.getLastName() + "and will be sent to Bank for approval! Bank Account details are: ");
				log.info("A new bank account was created for the User: " + userSession.getFirstName() + 
						" "+userSession.getLastName() + "and will be sent to Bank for approval! Bank Account details are: ");				

				Date date = new Date(newBankAccount.getDateBankAccountCreation().getTime());
				SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM yyyy"); 
				
				System.out.println("User id: " + userSession.getId() + " | " + userSession.getFirstName() + " " 
						+ userSession.getLastName() + " | Bank Account Owner Id: " + newBankAccount.getAccountOwnerId() 
						+ " | Bank Account number: " + newBankAccount.getBankAccountNumber() 
						+ " | Date of Bank Account creation: " + formatter.format(date));				
				log.info("User id: " + userSession.getId() + " | " + userSession.getFirstName() + " " 
						+ userSession.getLastName() + " | Bank Account Owner Id: " + newBankAccount.getAccountOwnerId() 
						+ " | Bank Account number: " + newBankAccount.getBankAccountNumber() 
						+ " | Date of Bank Account creation: " + formatter.format(date));

				//save the newly created bank account into the the temp table for Bank's approval
				System.out.println("\nDear customer " + userSession.getFirstName() + " " + userSession.getLastName() 
					+ ", the newly generated bank account with number " + newBankAccount.getBankAccountNumber() 
					+ " will be saved in temp db waiting for Bank's approval.");
				log.info("Dear customer " + userSession.getFirstName() + " " + userSession.getLastName() 
					+ ", the newly generated bank account with number " + newBankAccount.getBankAccountNumber() 
					+ " will be saved in temp db waiting for Bank's approval.");

				//update the Bank Account approval status in corresponding table
				newBankAccount.setBankAccountApprovalPending(true); //set value to true - Bank Account approval is pending
				newBankAccount.setBankAccountApproved(false);		//set value to false - this Bank Account has not yet been approved by the Bank 
				
				if (bankAccountService.addNewBankAccountToApprovalTable(userSession, newBankAccount)>0)
				{
					System.out.println("\nDear customer " + userSession.getFirstName() + " " + userSession.getLastName() 
					+ ", the newly generated bank account with number " + newBankAccount.getBankAccountNumber() 
					+ " was saved in temp db waiting for Bank's approval.");
					log.info("Dear customer " + userSession.getFirstName() + " " + userSession.getLastName() 
					+ ", the newly generated bank account with number " + newBankAccount.getBankAccountNumber() 
					+ " was saved in temp db waiting for Bank's approval.");				
				}
			}
		}
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}
		System.out.println("");
	}

	@Override
	public List<BankAccount> getBankAccountBalance(User userSession) 
	{
		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();
		DisplayOnScreenService displayOnScreenService = new DisplayOnScreenServiceImpl();

		//get all the bank accounts a User has
		List<BankAccount> bankAccountsListByUser = null;

//		System.out.println("Get Bank Account Balance method allows a User to see the balance of one of his/her Bank Accounts.");
		log.info("Get Bank Account Balance method allows a User to see the balance of one of his/her Bank Accounts.");
		
		try 
		{
			//Code Here for SERVICE LAYER			
			bankAccountsListByUser = bankAccountService.getBankAccountsListByUser(userSession);

			//improvement: develop some service type functions for displaying the results.
			displayOnScreenService.printListOfBankAccountsByUser(bankAccountsListByUser, userSession);
		}
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}
		System.out.println("");
		return bankAccountsListByUser;
	}

	@Override
	public void updateDepositFundsIntoBankAccount(User userSession) 
	{
		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();

		List<BankAccount> bankAccountsListByUser = null;

		//instantiate an object to handle the data display on screen
		DisplayOnScreenService displayOnScreenService = new DisplayOnScreenServiceImpl();

		//instantiate an object to be used to validate the selected Bank Account Id
		ValidatorUtil selectedBankAccountValidator = new ValidatorUtilImpl();
		String transactionDescription = "";
		BankTransaction depositFundsTransactionObj = new BankTransaction();
		
		//get all the bank accounts a Customer has and display them on screen.
		//Customer can choose the destination account from the list;
		try 
		{
			bankAccountsListByUser = bankAccountService.getBankAccountsListByUser(userSession);
			displayOnScreenService.printListOfBankAccountsByUser(bankAccountsListByUser, userSession);
				
			//reads from keyboard, validates and returns the selected bank account where the deposit will be made 
			BankAccount selectedBankAccount = 
					selectedBankAccountValidator.verifySelectedBankAccount(userSession, bankAccountsListByUser);

			double amountToDeposit = 0;
			
			do
			{	//check if invalid transactions: 
				//A deposit of negative money
				System.out.println("Please enter the amount you want to deposit (it should be a positive amount): ");
				amountToDeposit = Double.parseDouble(scannerCustomerMenu.nextLine());
			} while(amountToDeposit <= 0);

			double newBalanceToRecord = amountToDeposit + selectedBankAccount.getAccountBalance();
			
			System.out.println("\nPlease enter a description for the deposit: ");
			transactionDescription = scannerCustomerMenu.nextLine();

			depositFundsTransactionObj.setSourceBankAccount(null);
			depositFundsTransactionObj.setDestinationBankAccount(selectedBankAccount);
			depositFundsTransactionObj.setAmount(newBalanceToRecord);
	
			Timestamp transactionDate = new Timestamp(System.currentTimeMillis());
			depositFundsTransactionObj.setTransactionDate(transactionDate);
		
			TransactionType transactionType = TransactionType.DEPOSIT_FUNDS;
			depositFundsTransactionObj.setTransactionType(transactionType);
			depositFundsTransactionObj.setTransactionDescription(transactionDescription);
		
			//Code Here for SERVICE LAYER
			//update the bank account balance
			if(bankAccountService.depositFundsTransaction(selectedBankAccount, newBalanceToRecord, 
					userSession.getId()) > 0)
			{
				Date date = new Date(depositFundsTransactionObj.getTransactionDate().getTime());
				SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM yyyy"); 

				System.out.println("\nOn " + formatter.format(date) + ", the amount of " + amountToDeposit 
						+ " was deposited in your bank account with Id: " 
						+ selectedBankAccount.getBankAccountId() + " | Bank Account Number: " 
						+ selectedBankAccount.getBankAccountNumber());
				log.info("\nOn " + formatter.format(date) + ", the amount of " + amountToDeposit 
						+ " was deposited in your bank account with Id: " 
						+ selectedBankAccount.getBankAccountId() + " | Bank Account Number: " 
						+ selectedBankAccount.getBankAccountNumber());				

				//maybe I can perform a query to extract the new balance from bank table from the db
				System.out.println("Your bank's account new balance is: " + newBalanceToRecord);
			}

			//change the amount to be recorded from newBalanceToBeRecorded to amountToDeposit;
			//we use the same object to hold the data to be saved in two different tables:
			//the Bank_Account table where we record only the New Balance and 
			//the Transactions table where we record the specific amount deposited 
			depositFundsTransactionObj.setAmount(amountToDeposit);
			System.out.println("\nThe amount instantiate into transacton Obj to record as transaction amount is: "
					+ depositFundsTransactionObj.getAmount());
		
			//record the transaction in transactions' table;
			if(bankAccountService.recordTransaction(depositFundsTransactionObj)>0)
			{
				System.out.println("\nTransaction " + depositFundsTransactionObj.getIdTransaction() 
					+ " was recorded into the database. Transaction amount: " 
					+ depositFundsTransactionObj.getAmount());
				log.info("\nTransaction " + depositFundsTransactionObj.getIdTransaction() 
					+ " was recorded into the database. Transaction amount: " 
					+ depositFundsTransactionObj.getAmount());
			}
			else
			{
				System.out.println("Transaction was not recorded in db!");
			}
		}
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
//			scannerDepositFunds.close();
		}
		System.out.println("");
	}

	@Override
	public void updateWithdrawFundsFromBankAccount(User userSession) 
	{
		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();

		List<BankAccount> bankAccountsListByUser = null;
		
		//instantiate an object to handle the data display on screen
		DisplayOnScreenService displayOnScreenService = new DisplayOnScreenServiceImpl();

		//instantiate an object to be used to validate the selected Bank Account Id
		ValidatorUtil selectedBankAccountValidator = new ValidatorUtilImpl();

		BankAccount selectedBankAccount = null;
		String transactionDescription = "";
		BankTransaction withdrawFundsTransactionObj = new BankTransaction();
		
		//get all the bank accounts a Customer has and display them on screen .
		//Customer can choose the destination account from the list;
		try
		{
			bankAccountsListByUser = bankAccountService.getBankAccountsListByUser(userSession);
			displayOnScreenService.printListOfBankAccountsByUser(bankAccountsListByUser, userSession);

			//reads from keyboard, validates and returns the selected bank account from where the withdrawal will be made 
			selectedBankAccount = 
					selectedBankAccountValidator.verifySelectedBankAccount(userSession, bankAccountsListByUser);
			
			double amountToWithdraw = 0;

			do
			{	//check if invalid transactions: 
				//A withdrawal that would result in a negative balance.
				//A deposit or withdrawal of negative money.
				System.out.println("\nPlease enter the amount you want to withdraw (it should be a positive amount): ");
				System.out.println("You can withdraw a maximum amount of: " + selectedBankAccount.getAccountBalance());
				amountToWithdraw = Double.parseDouble(scannerCustomerMenu.nextLine());
			} while (amountToWithdraw < 0 || (selectedBankAccount.getAccountBalance()-amountToWithdraw < 0));
				
			double newBalanceToRecord = selectedBankAccount.getAccountBalance() - amountToWithdraw;

			System.out.println("\nPlease enter a description for the withdraw: ");
			transactionDescription = scannerCustomerMenu.nextLine();
			
			withdrawFundsTransactionObj.setSourceBankAccount(selectedBankAccount);
			withdrawFundsTransactionObj.setDestinationBankAccount(null);
			withdrawFundsTransactionObj.setAmount(newBalanceToRecord);
		
			Timestamp transactionDate = new Timestamp(System.currentTimeMillis());
			withdrawFundsTransactionObj.setTransactionDate(transactionDate);
			
			TransactionType transactionType = TransactionType.WITHDRAW_FUNDS;
			withdrawFundsTransactionObj.setTransactionType(transactionType);
			withdrawFundsTransactionObj.setTransactionDescription(transactionDescription);

		
			//Code Here for SERVICE LAYER
			//update the bank account balance
			if(bankAccountService.withdrawFundsTransaction(selectedBankAccount, newBalanceToRecord, 
					userSession.getId()) > 0)
			{
				Date date = new Date(withdrawFundsTransactionObj.getTransactionDate().getTime());
				SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM yyyy"); 

				System.out.println("\nOn " + formatter.format(date) + ", the amount of " + amountToWithdraw 
					+ " was withdrawn from your bank account with" 
					+ " Bank Account Id: " + selectedBankAccount.getBankAccountId() 
					+ " | Bank Account Number: " + selectedBankAccount.getBankAccountNumber());
				log.info("\nOn " + formatter.format(date) + ", the amount of " + amountToWithdraw 
					+ " was withdrawn from your bank account with" + " Bank Account Id: " 
					+ selectedBankAccount.getBankAccountId() + " | Bank Account Number: " 
					+ selectedBankAccount.getBankAccountNumber());	
				
				//maybe I can perform a query to extract the new balance from bank table from the db
				System.out.println("Your bank's account new balance is: " + newBalanceToRecord);
			}

			//record the transaction in transactions' table;
			//change the amount to be recorded from newBalanceToBeRecorded to amountToWithdraw;
			//we use the same object to hold the data to be saved in two different tables:
			//the Bank_Account table where we record only the New Balance and 
			//the Transactions table where we record the specific amount withdrawn 
			withdrawFundsTransactionObj.setAmount(amountToWithdraw);
			
			//record the transaction in transactions' table;
			if(bankAccountService.recordTransaction(withdrawFundsTransactionObj)>0)
			{
				System.out.println("Transaction " + withdrawFundsTransactionObj.getIdTransaction() 
					+ "was recorded in the database. Transaction amount: "
					+ withdrawFundsTransactionObj.getAmount());
				log.info("Transaction " + withdrawFundsTransactionObj.getIdTransaction() 
					+ "was recorded in the database. Transaction amount: "
					+ withdrawFundsTransactionObj.getAmount());
			}
			else
			{
				System.out.println("Transaction was not recorded in db!");
			}
		}
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}
		System.out.println("");		
	}

	@Override
	public void updatePostFundsTransferFromBankAccount(User userSession) 
	{
		//Step 1: select the account from where the Funds transfer will initiated
		//Step 2: select the account where the Funds will be transfered to
		//Step 3: read the amount to be transfered
		//Step 4: perform the withdrawal of the funds and balance update of the sender's account
		//Step 5: record the transaction into a temp table, until the recipient accepts the transfer
		
		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();

		List<BankAccount> bankAccountsListByUser = null;

		//instantiate an object to handle the data display on screen
		DisplayOnScreenService displayOnScreenService = new DisplayOnScreenServiceImpl();

		//instantiate an object to be used to validate the selected Bank Account Id
		ValidatorUtil selectedBankAccountValidator = new ValidatorUtilImpl();

		BankAccount selectedSourceBankAccount = null;
		BankAccount selectedDestinationBankAccount = null;
		Long selectedDestinationBankAccountNumber = 0L;
		String transactionDescription = "";
		boolean isTransactionCleared = false;
		BankTransaction transferFundsTransactionObj = new BankTransaction();

		boolean isDestinationBankAccountApproved = false;
		
		//Step 1:
		try
		{
			bankAccountsListByUser = bankAccountService.getBankAccountsListByUser(userSession);
			displayOnScreenService.printListOfBankAccountsByUser(bankAccountsListByUser, userSession);

			//reads from keyboard, validates and returns the selected bank account from where the withdrawal will be made 
			selectedSourceBankAccount = 
					selectedBankAccountValidator.verifySelectedBankAccount(userSession, bankAccountsListByUser);
			
			
			//Step 2:			
			//read the Destination Account Number from keyboard
			//or I can read the User Id / Name to whom to send the funds (?)
			do
			{
				do 		
				{
					System.out.println("Select the bank account where you want to transfer the funds to: ");
					selectedDestinationBankAccountNumber = Long.parseLong(scannerCustomerMenu.nextLine());
	
					//get the bank account data from the database and instantiate the Bank Account object
					selectedDestinationBankAccount = bankAccountService.getBankAccountByNumber(selectedDestinationBankAccountNumber);

					//test if the selected source bank account is an Existing Bank account;
					if (selectedDestinationBankAccount == null)
					{
						System.out.println("This account does not exists.\n"
								+ "Please check the Bank Account number and select a different Bank Account!");
					}
				} while (selectedDestinationBankAccount == null);
			
				//test if the selected source bank account is an Approved Bank account;
				if (selectedDestinationBankAccount.getStatusBankAccount() == StatusAccount.APPROVED)
				{
					System.out.println("\nThe selected Bank Account has Id: " + selectedDestinationBankAccount.getBankAccountId() 
					+ " | Owner id: " + selectedDestinationBankAccount.getAccountOwnerId()
					+ " | Status: " + selectedDestinationBankAccount.getStatusBankAccount());
					isDestinationBankAccountApproved = true;
					break;
				}
				else
				{
					System.out.println("The chosen bank account has STATUS: " 
						+ selectedDestinationBankAccount.getStatusBankAccount() + " and cannot be used.\n"
						+ "Please select an APPROVED Bank Account!");
					isDestinationBankAccountApproved = false;
				}
			} while(isDestinationBankAccountApproved != true);
	
			
			//Step 3:
			double amountToTransfer = 0;

			do
			{	//check if invalid transactions: 
				//A withdrawal that would result in a negative balance.
				//A deposit or withdrawal of negative money.
				System.out.println("\nPlease enter the amount you want to transfer (it should be a positive amount): ");
				System.out.println("You can withdraw a maximum amount of: " + selectedSourceBankAccount.getAccountBalance());
				amountToTransfer = Double.parseDouble(scannerCustomerMenu.nextLine());
			} while (amountToTransfer < 0 || (selectedSourceBankAccount.getAccountBalance()-amountToTransfer < 0));
				
			double newBalanceToRecord = selectedSourceBankAccount.getAccountBalance() - amountToTransfer;
			
			System.out.println("\nPlease enter a description for the transfer: ");
			transactionDescription = scannerCustomerMenu.nextLine();

			
			//Step 4:
			transferFundsTransactionObj.setSourceBankAccount(selectedSourceBankAccount);
			transferFundsTransactionObj.setDestinationBankAccount(selectedDestinationBankAccount);
			transferFundsTransactionObj.setAmount(newBalanceToRecord);
		
			Timestamp transactionDate = new Timestamp(System.currentTimeMillis());
			transferFundsTransactionObj.setTransactionDate(transactionDate);
			
			TransactionType transactionType = TransactionType.POST_FUND_TRANSFER;
			transferFundsTransactionObj.setTransactionType(transactionType);
			
			transferFundsTransactionObj.setTransactionDescription(transactionDescription);
			transferFundsTransactionObj.setTransactionCleared(isTransactionCleared);
			//is_transaction_cleared is set to false when transfer is initiated
			//when is accepted will be set to true
			
			//Code Here for SERVICE LAYER
			//update the bank account balance
			if(bankAccountService.postTransferFundsTransaction(selectedSourceBankAccount, newBalanceToRecord, 
					userSession.getId()) > 0)
			{
				Date date = new Date(transferFundsTransactionObj.getTransactionDate().getTime());
				SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM yyyy"); 

				System.out.println("\nOn " + formatter.format(date) + ", the amount of " + amountToTransfer 
					+ " was transfered from your bank account with: \n" 
					+ "Bank Account Id: " + selectedSourceBankAccount.getBankAccountId() 
					+ " | Bank Account Number: " + selectedSourceBankAccount.getBankAccountNumber()
					+ " | to Bank Account Number: " + selectedDestinationBankAccount.getBankAccountNumber());
				log.info("\nOn " + formatter.format(date) + ", the amount of " + amountToTransfer 
					+ " was transfered from your bank account with: \n" 
					+ "Bank Account Id: " + selectedSourceBankAccount.getBankAccountId() 
					+ " | Bank Account Number: " + selectedSourceBankAccount.getBankAccountNumber()
					+ " | to Bank Account Number: " + selectedDestinationBankAccount.getBankAccountNumber());	
				
				//maybe I can perform a query to extract the new balance from bank table from the db
				System.out.println("Your bank's account new balance is: " + newBalanceToRecord);
			}

			
			//Step 5:
			//record the transaction in transfers transactions' table;
			//change the amount to be recorded from newBalanceToBeRecorded to amountToTranfer;
			//we use the same object to hold the data to be saved in three different tables:
			//the Bank_Account table where we record only the New Balance and 
			//the Transactions table where we record the specific amount transfered 
			//the Transfers TRansactions table where we record the specific amount transfered
			transferFundsTransactionObj.setAmount(amountToTransfer);
		
			//record the transaction in transfer transactions' table;
			if(bankAccountService.recordTransferTransaction(transferFundsTransactionObj)>0)
			{						
				System.out.println("Transfer transaction " + transferFundsTransactionObj.getIdTransaction() 
					+ " was recorded in the database. Transaction amount: "
					+ transferFundsTransactionObj.getAmount());
				log.info("Transfer transaction " + transferFundsTransactionObj.getIdTransaction() 
					+ " was recorded in the database. Transaction amount: "
					+ transferFundsTransactionObj.getAmount());
			}
			else
			{
				System.out.println("Transaction was not recorded in db!");
			}
		}
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}
		System.out.println("");	
	}

	@Override
	public void updateAcceptFundsTransferIntoBankAccount(User userSession) 
	{
		System.out.println("Feature not yet fully implemented!");
		//Step 1: retrieve and display the incoming funds transaction(s) pending approval from the temp db, related to current User;
		//Step 2: select the transaction to be approved/accepted;
		//Step 3: select if accept of decline the transaction
		//Step 3A.1 perform the deposit of the funds and balance update of the recipient's account;
		//Step 3A.2: record the transaction into the final transactions table, 
		//		while updating the status of the transfer transaction in the transfer_transaction table;
		//or
		//Step 3B: reject the transfer:
		//Step 3B.1: re-deposit the funds into the sender's account
		//Step 3B.2: change the transaction's status in the transfer_transaction table in TRANSFER_DECLINED
		
		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();

		//instantiate an object to handle the data display on screen
		DisplayOnScreenService displayOnScreenService = new DisplayOnScreenServiceImpl();

		//instantiate an object to be used to validate the selected Bank Account Id
		ValidatorUtil selectedTransferTransactionValidator = new ValidatorUtilImpl();

		BankAccount selectedSourceBankAccount = null;
		BankAccount selectedDestinationBankAccount = null;
		BankTransaction transferFundsTransactionObj = new BankTransaction();
		List<BankTransaction> tempTransferBankTransactionsListByRecipientUser;
		String choiceStatus;
		TransactionType transactionType = TransactionType.POST_FUND_TRANSFER;
		boolean isTransactionCleared = false;
				
		//Step 1:Retrieve all pending transactions for the current user
		try {
			//Code here for SERVICE LAYER	
			tempTransferBankTransactionsListByRecipientUser = 
					bankAccountService.getAllTransferTransactionsByRecipientUser(userSession, transactionType, isTransactionCleared);	
			displayOnScreenService.printListOfTransferTransactionsByRecipientUser(tempTransferBankTransactionsListByRecipientUser, userSession);

			//Step 2: select the transaction to be approved/accepted;
			//reads from keyboard, validates and returns the selected transfer transaction for the deposit to be made 
			BankTransaction selectedTransferTransaction = 
					selectedTransferTransactionValidator.verifySelectedTransferTransaction(userSession, tempTransferBankTransactionsListByRecipientUser);
						
			//instantiate the source and destination bank account objects
			selectedDestinationBankAccount = selectedTransferTransaction.getDestinationBankAccount();
			selectedSourceBankAccount = selectedTransferTransaction.getSourceBankAccount();
			
			//Step 3: select from keyboard if transaction is accepted or declined
			System.out.println("Do you accept the selected transfer? (Y - approve / N - reject)");
			choiceStatus = scannerCustomerMenu.nextLine();

			switch (choiceStatus.toUpperCase())
			{
				case "Y":				//Code here for SERVICE LAYER
					
					//Step 3A.1 perform operations: deposit of the funds and update the balance of the recipient's account;

					double newBalanceToRecord = selectedDestinationBankAccount.getAccountBalance() 
							+ selectedTransferTransaction.getAmount();
										
					//prepare the transaction object properties for recording the accepted transfer transaction into transfer transaction table 
					transferFundsTransactionObj.setSourceBankAccount(selectedTransferTransaction.getSourceBankAccount());
					transferFundsTransactionObj.setDestinationBankAccount(selectedTransferTransaction.getDestinationBankAccount());
					transferFundsTransactionObj.setAmount(newBalanceToRecord);
				
					Timestamp transactionDate = new Timestamp(System.currentTimeMillis());
					transferFundsTransactionObj.setTransactionDate(transactionDate);
					
					TransactionType transactionTypeIn = TransactionType.ACCEPT_FUND_TRANSFER;
					transferFundsTransactionObj.setTransactionType(transactionTypeIn);
					isTransactionCleared = true;
					transferFundsTransactionObj.setTransactionCleared(isTransactionCleared);
					transferFundsTransactionObj.setTransactionDescription(selectedTransferTransaction.getTransactionDescription());
										
					//Code Here for SERVICE LAYER
					//update the bank account balance
					if(bankAccountService.postTransferFundsTransaction(selectedDestinationBankAccount, newBalanceToRecord, 
							userSession.getId()) > 0)
					{
						Date date = new Date(transferFundsTransactionObj.getTransactionDate().getTime());
						SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM yyyy"); 

						System.out.println("\nOn " + formatter.format(date) + ", the amount of " 
							+ selectedTransferTransaction.getAmount() 
							+ " was transfered to your bank account with: \n" 
							+ "Bank Account Id: " + selectedDestinationBankAccount.getBankAccountId() 
							+ " | Bank Account Number: " + selectedDestinationBankAccount.getBankAccountNumber()
							+ " | from Bank Account Number: " + selectedSourceBankAccount.getBankAccountNumber());
						log.info("\nOn " + formatter.format(date) + ", the amount of " 
							+ selectedTransferTransaction.getAmount() 
							+ " was transfered to your bank account with: \n" 
							+ "Bank Account Id: " + selectedDestinationBankAccount.getBankAccountId() 
							+ " | Bank Account Number: " + selectedDestinationBankAccount.getBankAccountNumber()
							+ " | from Bank Account Number: " + selectedSourceBankAccount.getBankAccountNumber());	
						
						//maybe I can perform a query to extract the new balance from bank table from the db
						System.out.println("Your bank's account new balance is: " + newBalanceToRecord);
						
						
						//Step 3A.2: record the accept transfer transaction into the transfer_transactions table, 
						//  while updating the status of the post transfer transaction in the transfer_transaction table;
						
						transferFundsTransactionObj.setAmount(selectedTransferTransaction.getAmount());
						selectedTransferTransaction.setTransactionCleared(isTransactionCleared);
						
						//record the transaction in transfer transactions' table;
						if(bankAccountService.recordTransferTransaction(transferFundsTransactionObj)>0)
						{									
							System.out.println("Transfer transaction " + transferFundsTransactionObj.getIdTransaction() 
								+ " was recorded in the database. Transaction amount: "
								+ transferFundsTransactionObj.getAmount());
							log.info("Transfer transaction " + transferFundsTransactionObj.getIdTransaction() 
								+ " was recorded in the database. Transaction amount: "
								+ transferFundsTransactionObj.getAmount());
														
							if(bankAccountService.updateTransferTransactionClearingStatus(selectedTransferTransaction, isTransactionCleared)>0)
							{
								System.out.println("The clearance status of initial transfer transaction with id: " 
									+ selectedTransferTransaction.getIdTransaction() 
									+ " was updated to TRUE in the database. New clearance status is: "
									+ selectedTransferTransaction.isTransactionCleared());
								log.info("The clearance status of initial transfer transaction with id: " 
									+ selectedTransferTransaction.getIdTransaction() 
									+ " was updated to TRUE in the database. New clearance status is: "
									+ selectedTransferTransaction.isTransactionCleared());
							}
						}
						else
						{
							System.out.println("Accepted Transfer Transaction was not recorded in the db!");
						}
					}
					break;
				case "N":
					//Code here for SERVICE LAYER
					//Step 3B: reject the transfer:
					//Step 3B.1: re-deposit the funds into the sender's account
					//Step 3B.2: change the transaction's status in the transfer_transaction table in TRANSFER_DECLINED
					System.out.println("Method for final step of rejecting transfers not yet implemented!");

					//set the parameters for updating the declined transaction in transfer_transactions table
					transactionTypeIn = TransactionType.TRANSFER_DECLINED;
					selectedTransferTransaction.setTransactionType(transactionTypeIn);
					isTransactionCleared = true;
					selectedTransferTransaction.setTransactionCleared(isTransactionCleared);
					transactionDate = new Timestamp(System.currentTimeMillis());
					transferFundsTransactionObj.setTransactionDate(transactionDate);
					
					System.out.println("Initial transfer to be updated with declined status: " + selectedTransferTransaction.getIdTransaction());
					
					if(bankAccountService.updateTransferTransactionTypeAndClearingStatus(selectedTransferTransaction)>0)
					{
						System.out.println("The initial transfer transaction with id: " 
							+ selectedTransferTransaction.getIdTransaction() 
							+ " was declined and its clearance status was updated to TRUE in the database. "
							+ " \nNew transaction type was set to : " + selectedTransferTransaction.getTransactionType() 
							+ " while the new clearance status was set to: "
							+ selectedTransferTransaction.isTransactionCleared());
						log.info("The initial transfer transaction with id: " 
								+ selectedTransferTransaction.getIdTransaction() 
								+ " was declined and its clearance status was updated to TRUE in the database. "
								+ " \nNew transaction type was set to : " + selectedTransferTransaction.getTransactionType() 
								+ " while the new clearance status was set to: "
								+ selectedTransferTransaction.isTransactionCleared());
					}
					
					//deposit the funds in the source bank account
					newBalanceToRecord = selectedSourceBankAccount.getAccountBalance() 
						+ selectedTransferTransaction.getAmount();

					if(bankAccountService.postTransferFundsTransaction(selectedSourceBankAccount, newBalanceToRecord) > 0)
					{
						Date date = new Date(transferFundsTransactionObj.getTransactionDate().getTime());
						SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM yyyy"); 

						System.out.println("\nOn " + formatter.format(date) + ", the amount of " 
							+ selectedTransferTransaction.getAmount() 
							+ " was transfered back to source bank account with: \n" 
							+ "Bank Account Id: " + selectedSourceBankAccount.getBankAccountId() 
							+ " | Bank Account Number: " + selectedSourceBankAccount.getBankAccountNumber());
						log.info("\nOn " + formatter.format(date) + ", the amount of " 
							+ selectedTransferTransaction.getAmount() 
							+ " was transfered back to source bank account with: \n" 
							+ " Bank Account Id: " + selectedSourceBankAccount.getBankAccountId() 
							+ " | Bank Account Number: " + selectedSourceBankAccount.getBankAccountNumber());	
						
						//maybe I can perform a query to extract the new balance from bank table from the db
						System.out.println("Your bank's account new balance is: " + newBalanceToRecord);
					}
					
					break;
				default: 
					System.out.println("Please enter only 'Y' or 'N' to continue!");
					break;
			}			
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}
}