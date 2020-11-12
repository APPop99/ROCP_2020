package app.bankingApp.presenter.implementation;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.lang.Math;
//import java.util.Random;
//import java.util.stream.LongStream;

import org.apache.log4j.Logger;

//import app.bankingApp.DAO.dbUtil.LongStream;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.StatusAccount;
import app.bankingApp.model.StatusUser;
import app.bankingApp.model.User;
import app.bankingApp.presenter.CustomerMenuPresenter;
import app.bankingApp.service.BankAccountService;
//import app.bankingApp.presenter.MainMenuPresenter;
import app.bankingApp.service.UserService;
import app.bankingApp.service.implementation.BankAccountServiceImpl;
import app.bankingApp.service.implementation.UserServiceImpl;

public class CustomerMenuPresenterImpl implements CustomerMenuPresenter
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);

	public CustomerMenuPresenterImpl(User userLogin) 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showCustomerMenu(User userSession) 
	{
		System.out.println("Customer Menu!");
		System.out.println("Allows a logged in Customer to operate the apps' features!\n");
		Scanner scannerCustomerMenu = new Scanner(System.in);
		
		System.out.println(userSession.getFirstName() + " " + userSession.getLastName() + ": Welcome to Console based Bank / Customer Menu!");
		System.out.println("----------------------------------------------");
		
		int choice = 0;
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
			System.out.println("6) Accept a trasfer into the selected Bank Account");
			System.out.println("7) Returning to previous menu");
			System.out.println("Please enter appropriate choice(1-7) :) ");

			try 
			{
				choice = Integer.parseInt(scannerCustomerMenu.nextLine());
			} 
			catch (NumberFormatException e) 
			{
				System.out.println("Please enter a number between 1 and 2!");
			}

			switch (choice)
			{
				case 1:
					//apply for a bank account 
					System.out.println("Method <New Bank Account Registration> will create a new Bank Account and submit it for Bank's approval!");
					log.info("Method <New Bank Account Registration> will create a new Bank Account and submit it for Bank's approval!");
					CustomerMenuPresenter createBankAccount = new CustomerMenuPresenterImpl(userSession);
					createBankAccount.createNewBankAccount(userSession);
					break;
				case 2:
					//view the balance of an account
					System.out.println("Feature not yet implemented!");
					break;
				case 3:
					//make a deposit into a specific account (reject invalid transactions)
					System.out.println("Feature not yet implemented!");
					break;
				case 4:
					//make a withdrawal from a specific account (reject invalid transactions)
					System.out.println("Feature not yet implemented!");
					break;
				case 5:
					//post a money transfer to another account
					System.out.println("Feature not yet implemented!");
					break;
				case 6:
					//accept money transfer from another account
					System.out.println("Feature not yet implemented!");
					break;
				case 7: 
					//return to previous menu
					break;
				default:
					System.out.println("Please enter 1, 2, 3, 4, 5, 6 or 7 to continue!");
					break;
			}
		} while (choice !=9 );
	}

	@Override
	public void showNonCustomerMenu(User userSession) 
	{
		//This method allows an User to register for a customer account in order to access other features of the app 

		System.out.println("New Customer Menu!");
		System.out.println("Allows a logged in User to apply for an approved Customer Account to use apps features!\n");

		Scanner scannerNonCustomerMenu = new Scanner(System.in);
		
		System.out.println("Welcome to Console based Bank / New Registered User Menu!");
		System.out.println("---------------------------------------------------------");
		
		int choice = 0;
		do 
		{
			System.out.println("Console Bank NON CUSTOMER MENU");
			System.out.println("==============================");
			System.out.println("Please select your option:");
			System.out.println("--------------------------\n");
			System.out.println("1) New CUSTOMER registration request");
			System.out.println("2) Returning to previous menu");
			System.out.println("Please enter appropriate choice(1-2) :) ");

			try 
			{
				choice = Integer.parseInt(scannerNonCustomerMenu.nextLine());
			} 
			catch (NumberFormatException e) 
			{
				System.out.println("Please enter a number between 1 and 2!");
			}
			
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
							System.out.println("User with " + userSession.getId() + " | "+userSession.getFirstName() 
							+ " "+userSession.getLastName()+" already submitted a request for Bank's approval for a Customer Acoount");
							log.info("User with " + userSession.getId() + " | "+userSession.getFirstName() 
							+ " "+userSession.getLastName()+" already submitted a request for Bank's approval for a Customer Acoount");

							break;
						}
					}
					else
					{
						System.out.println("User with " + userSession.getId() + " | "+userSession.getFirstName() 
						+ " "+userSession.getLastName()+" already has Customer status");
						log.info("User with " + userSession.getId() + " | "+userSession.getFirstName() 
						+ " "+userSession.getLastName()+" already has Customer status");
						break;
					}
				case 2:
					break;
			}
		} while(choice != 2);
	}

	@Override
	public void addNonCustomerUserToApprovalTable(User userSession) 
	{
		//instantiate an object that will be used () to transfer data to and from Service layer 
		UserService userService = new UserServiceImpl();
		
		System.out.println("Please explicitly give us your acceptance for being approved as Customer:");
		Scanner scannerAddNonCustomerUserToApprovalTable = new Scanner(System.in);
		
		String choiceStatus;
		boolean choiceBool = false;
		
		try 
		{
			do
			{
				System.out.println("Do you like to have your User account registered as Customer Account ? (Y/N)");
				choiceStatus = scannerAddNonCustomerUserToApprovalTable.nextLine();

				switch (choiceStatus.toUpperCase())
				{
					case "Y":
						//Code Here for SERVICE LAYER
						System.out.println("User with id " + userSession.getId() + " will be saved in temp db waiting for approval.");
						log.info("User with id " + userSession.getId() + "will be saved in temp db waiting for approval.");
						userSession.setCustomerApprovalPending(true); 	//set value to true - Customer Account approval is pending
						userSession.setCustomerStatusApproved(false);	//set value to false - this User has not yet been approved as Customer 
						
						if (userService.addNonCustomerUserToApprovalTable(userSession)>0)
						{
							System.out.println("User: "+ userSession.getId() + " | " + userSession.getFirstName() + " " 
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
		Scanner scannerUserCreation = new Scanner(System.in);

		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();
		
		System.out.println("System will generate the details for the new Bank Account you wish to open:");
		try 
		{
			//generated a 9-digits random number as Bank Account Number 
			//test with the database the unicity of the Bank Account number generated
			long bankAccountNumber = 0;		
			boolean isBankAccountNumberUnique = false;
			
			while(isBankAccountNumberUnique == false)
			{
				do
				{	//apply random function to generate a 9 digit bank account number;
					bankAccountNumber = (long)(Math.random()*1000000 + 100000000L);
					log.info("Bank Account number randomly generated is: " + bankAccountNumber);
				} while(bankAccountService.isBankAccountDuplicate(bankAccountNumber) != true);
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

			if(bankAccountService.createNewBankAccount(userSession, newBankAccount) > 0)
			{
				System.out.println("A new bank account was generated for the User: " + userSession.getFirstName() + 
						" " + userSession.getLastName() + "and will be sent to Bank for approval! Bank Account details are: ");
				log.info("A new bank account was created for the User: " + userSession.getFirstName() + 
						" "+userSession.getLastName() + "and will be sent to Bank for approval! Bank Account details are: ");				

				Date date = new Date(newBankAccount.getDateBankAccountCreation().getTime());
				SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMMM yyyy"); 
				
				System.out.println("User id: " + userSession.getId() + " | " + userSession.getFirstName() + " " 
						+ userSession.getLastName() + " | Bank Account number: " + newBankAccount.getBankAccountNumber() + 
						" | Date of Bank Account creation: " + formatter.format(date));				
				log.info("User id: " + userSession.getId() + " | " + userSession.getFirstName() + " " 
						+ userSession.getLastName() + " | Bank Account number: " + newBankAccount.getBankAccountNumber() + 
						" | Date of Bank Account creation: " + formatter.format(date));

				//save the newly created bank account into the the temp table for Bank's approval
				System.out.println("Dear customer " + userSession.getFirstName() + " " + userSession.getLastName() 
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
					System.out.println("User: "+ userSession.getId() + " | " + userSession.getFirstName() + " " 
							+ userSession.getLastName()+" was saved in temp db waiting for approval");
					log.info("User: "+ userSession.getId() + " | " + userSession.getFirstName() + " " 
							+ userSession.getLastName()+" was saved in temp db waiting for approval");				
				}
			}
		}
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}
		System.out.println("");
	}
}