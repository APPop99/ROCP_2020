package app.bankingApp.presenter.implementation;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
import app.bankingApp.model.StatusAccount;
import app.bankingApp.model.StatusUser;
import app.bankingApp.model.User;
//import app.bankingApp.presenter.CustomerMenuPresenter;
//import app.bankingApp.presenter.CustomerMenuPresenter;
import app.bankingApp.presenter.EmployeeMenuPresenter;
import app.bankingApp.service.BankAccountService;
import app.bankingApp.service.UserService;
import app.bankingApp.service.implementation.BankAccountServiceImpl;
import app.bankingApp.service.implementation.UserServiceImpl;
import app.bankingApp.exception.BusinessException;


public class EmployeeMenuPresenterImpl implements EmployeeMenuPresenter 
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);
	private final Scanner scannerEmployeeMenu = new Scanner(System.in);

	public EmployeeMenuPresenterImpl(User userSession) 
	{
		
	}

	public EmployeeMenuPresenterImpl() 
	{
		
	}

	@Override
	public void showEmployeeMenu(User userSession) 
	{
		System.out.println("Employee Menu!");
		System.out.println("Allows Bank's Employees to perform Bank-specific operations!\n");
		
		System.out.println("Welcome to Console based Bank / Employee Menu!");
		System.out.println("----------------------------------------------");
		
		int choice = 0;
		try 
		{		
			do
			{
				System.out.println("Console Bank EMPLOYEE MENU");
				System.out.println("==========================");
				System.out.println("Please select your option:");
				System.out.println("--------------------------\n");
				System.out.println("1) Approval or Rejection for New CUSTOMER registration request");
				System.out.println("2) Approval or Rejection for a CUSTOMER's bank account openning request");
				System.out.println("3) View a CUSTOMER's bank accounts");
				System.out.println("4) View logs of all transactions for all Customers (or for a Customer / ?=optional implementation)");
				System.out.println("5) Returning to previous menu");
				System.out.println("Please enter appropriate choice(1-5) :) ");
	
				choice = Integer.parseInt(scannerEmployeeMenu.nextLine());
				
				switch (choice)
				{
					case 1:
						//employee can approve or reject an User Customer Account
	//					System.out.println("Method <Approve Customer Account for NonCustomer User> will approve or reject the User's request to become a new Customer!");
						log.info("\nMethod <Approve Customer Account for NonCustomer User> will approve or reject the User's request to become a new Customer!");
	
						EmployeeMenuPresenter approveCustomerAccount = new EmployeeMenuPresenterImpl(userSession);
						approveCustomerAccount.approveCustomerUserAccount(userSession);
						break;
					case 2:
						//employee can approve or reject a User's Bank Account
	//					System.out.println("Method <Approve New Bank Account> will approve or reject the Customer's request to open a new bank account!");
						log.info("\nMethod <Approve New Bank Account> will approve or reject Customer's request to open a new bank account!");
	
						EmployeeMenuPresenter approveNewBankAccount = new EmployeeMenuPresenterImpl(userSession);
						approveNewBankAccount.approveBankAccount(userSession);					
						break;
					case 3:
						//view a customer's bank accounts
	//					System.out.println("Method <View Bank Account> allows an Employee to see an User's Bank Accounts!");
						log.info("\nMethod <View Bank Account> allows an Employee to see an User's Bank Accounts!");
						EmployeeMenuPresenter viewBankAccountsByUser = new EmployeeMenuPresenterImpl();
						viewBankAccountsByUser.getBankAccountListyByUser();										
						break;
					case 4:
						//view a log of all transactions (of a specific account or all accounts):
						//open accounts
						//funds deposit or funds withdrawal
						//transfers posted or accepted
	//					System.out.println("Method <View Bank Account> allows an Employee to see an User's Bank Accounts!");
						log.info("\nMethod <View Bank Account> allows an Employee to see an User's Bank Accounts!");
						EmployeeMenuPresenter viewAllTransactions = new EmployeeMenuPresenterImpl();
						viewAllTransactions.getAllTransactions();														
						break;
					case 5: 
						//returning to previous menu
//						scannerEmployeeMenu.close();
						break;	
					default:
						System.out.println("Please enter 1, 2, 3, 4 or 5 to continue!");
						break;
				}	
			} while (choice != 5);
		} 
		catch (NumberFormatException e) 
		{
			System.out.println("Please enter a number between 1 and 5!");
//			scannerEmployeeMenu.close();
		}
	}

	@Override
	public void approveCustomerUserAccount(User userSession) 
	{
		//instantiate an object that will be used () to transfer data to and from Service layer 
		UserService userService = new UserServiceImpl();

		//Step 1: Get all Users that are saved in temp db with Customer Status Approval Pending;
//		System.out.println("Retrieve and display a list with all User that request their Customer's accounts to be approved.");
		log.info("Retrieve and display a list with all User that request their Customer's accounts to be approved.");

		boolean userApprovalPendingStatus = true;
		int choice = 0;
		String choiceStatus;
//		Scanner scannerEmployeeMenu = new Scanner(System.in);

		//Code here for SERVICE LAYER
		try 
		{
			List<User> usersToBeApprovedAsCustomers;
			usersToBeApprovedAsCustomers = userService.getUsersFromApprovalTable(userApprovalPendingStatus);
			
			if(usersToBeApprovedAsCustomers !=null && usersToBeApprovedAsCustomers.size()>0)
			{
				System.out.println("\nWe found "+usersToBeApprovedAsCustomers.size()+" users in the DB waiting to get their Customer status/account approved... Detailed list is:");
				log.info("\nWe found "+usersToBeApprovedAsCustomers.size()+" users in the DB waiting to get their Customer status/account approved... Detailed list is:");
				
				for(User u:usersToBeApprovedAsCustomers)
				{
					if (u.isCustomerApprovalPending() == true)
					{
						String tempStatus = "PENDING"; 
						System.out.println("User: " + u.getId() + " | " + u.getFirstName() + " " + u.getLastName() 
							+ " | Email: " + u.getEmail() 
							+ " | User Status: " + u.getStatusUser() 
							+ " | Approval Status: " + tempStatus);
						log.info("User: "+ u.getId() + " | " + u.getFirstName() + " " + u.getLastName() 
							+ " | Email: " + u.getEmail() 
							+ " | User Status: " + u.getStatusUser() 
							+ " | Approval Status: " + tempStatus);
					}
				}
			}
			
			//Step 2a: Approve and change User Status in CUSTOMER for a specific User
			//Step 2b: Reject and change User Status in INACTIVE for a specific User		
			do
			{
				System.out.println("Console Bank EMPLOYEE Customer Account Approval Process MENU");
				System.out.println("============================================================");
				System.out.println("Please select your option:");
				System.out.println("--------------------------\n");
				System.out.println("1) Process a specific Approval Request");
//				System.out.println("1) Process all Approval Requests, one by one"); //(? optional)
				System.out.println("2) Returning to previous menu");
				System.out.println("Please enter appropriate choice(1-2) :) ");
				
				choice = Integer.parseInt(scannerEmployeeMenu.nextLine());	
				
				switch (choice)
				{
					case 1:
						System.out.println("Please enter the User's Id of the User whose approval request is to be processed:");
						int id = Integer.parseInt(scannerEmployeeMenu.nextLine());
		
						//Code Here for SERVICE LAYER
						//System.out.println("Launch the Service Layer");
						User user = userService.getUserById(id);
						
						if (user!=null)
						{
							System.out.println("\nUser found with id "+id+" details are : ");
							log.info("\nUser found with id "+id+" details are : ");
							System.out.println("User: "+ user.getId() + " | " + user.getFirstName() + " " 
								+ user.getLastName() + " | Email: " + user.getEmail() 
								+ " | User Status: " + user.getStatusUser() 
								+ " | Approval Status Pending?: " + user.isCustomerApprovalPending());
							log.info("User: "+ user.getId() + " | " + user.getFirstName() + " " 
								+ user.getLastName() + " | Email: " + user.getEmail() 
								+ " | User Status: " + user.getStatusUser() 
								+ " | Approval Status Pending?: " + user.isCustomerApprovalPending());
							// print only needed details
							System.out.println("Approve the request of Activating Customer Account? (Y - approve / N - reject)");

							//change status code
							choiceStatus = scannerEmployeeMenu.nextLine();
							
							switch (choiceStatus.toUpperCase())
							{
							case "Y":
								//Code here for SERVICE LAYER
								//change User Status to CUSTOMER, customer_approval_status to true, customer_approval_pending to false
								user.setStatusUser(StatusUser.CUSTOMER);	//set User Status to 'CUSTOMER'
								user.setCustomerApprovalPending(false); 	//set value to false - Customer Account approval is done
								user.setCustomerStatusApproved(true);	//set value to true - this User is approved as Customer 
								userService.updateUser(user);
								System.out.println("\nUser's status was updated in the database. Details:");
								System.out.println("User id: "+ user.getId() + " has now " + user.getStatusUser() + " status.");
								log.info("\nUser's status was updated in the database. Details:");
								log.info("User id: "+ user.getId() + " has now " + user.getStatusUser() + " status.");									
								break;
							case "N":
								//Code here for SERVICE LAYER
								//change User Status to INACTIVE, customer_approval_status to false, customer_approval_pending to false
								user.setStatusUser(StatusUser.INACTIVE);	//set User Status to 'CUSTOMER'
								user.setCustomerApprovalPending(false); 	//set value to false - Customer Account approval is done
								user.setCustomerStatusApproved(false);	//set value to false - this User's request to be approved as Customer is denied 
								userService.updateUser(user);
								System.out.println("\nUser's status was updated in the database. Details:");
								System.out.println("User id: "+ user.getId() + " has now " + user.getStatusUser() + " status.");									
								log.info("\nUser's status was updated in the database. Details:");
								log.info("User id: "+ user.getId() + " has now " + user.getStatusUser() + " status.");									
								break;
							default: 
								System.out.println("Please enter only 'Y' or 'N' to continue!");
								break;
							}
						}
						System.out.println("");
						break;
					
					case 2:
						//returning to previous menu
//						scannerEmployeeMenu.close();
						break;
					default:
						System.out.println("Please enter a number between 1 and 2!");
						break;
				}
			} while (choice != 2);
			
		} catch (BusinessException e) 
		{	
			System.out.println(e.getMessage());
//			scannerEmployeeMenu.close();
		}
		System.out.println("");
	}

	@Override
	public void approveBankAccount(User userSession) 
	{
		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();

		//Step 1: Get all Bank Accounts and Users that are saved in temp db with Bank Account Status Approval Pending;
//		System.out.println("Retrieve and display a list with all Users that request that their new Bank Accounts created to be approved.");
		log.info("\nRetrieve and display a list with all Users that request that their new Bank Accounts created to be approved.");
		
		//Code Here for SERVICE LAYER
		boolean isBankAccountApprovalPending = true;
		int choice = 0;
		String choiceStatus;
//		Scanner scannerEmployeeMenu = new Scanner(System.in);

		try 
		{
			List<BankAccount> bankAccountsToBeApproved;
			bankAccountsToBeApproved = bankAccountService.getBankAccountsFromApprovalTable(isBankAccountApprovalPending);
			
			if(bankAccountsToBeApproved !=null && bankAccountsToBeApproved.size()>0)
			{
				System.out.println("\nWe found "+bankAccountsToBeApproved.size()+" new bank account(s) in the DB waiting to get their status approved... Detailed list is:");
				log.info("\nWe found "+bankAccountsToBeApproved.size()+" new bank account(s) in the DB waiting to get their status approved... Detailed list is:");
				
				for(BankAccount ba:bankAccountsToBeApproved)
				{
					if (ba.isBankAccountApprovalPending() == true)
					{
						String tempStatus = "PENDING"; 
						System.out.println("User: "+ userSession.getId() + " | " 
							+ userSession.getFirstName() + " " + userSession.getLastName() 
							+ " | New Bank Account Details: | Id: " + ba.getBankAccountId() 
							+ " | Account Number: " + ba.getBankAccountNumber() 
							+ " | Owner Id: " + ba.getAccountOwnerId()
							+ " | Account Status: " + ba.getStatusBankAccount() 
							+ " | Approval Status: " + tempStatus);
						log.info("User: "+ userSession.getId() + " | " 
							+ userSession.getFirstName() + " " + userSession.getLastName() 
							+ " | New Bank Account Details: | Id: " + ba.getBankAccountId() 
							+ " | Account Number: " + ba.getBankAccountNumber() 
							+ " | Owner Id: " + ba.getAccountOwnerId()
							+ " | Account Status: " + ba.getStatusBankAccount() 
							+ " | Approval Status: " + tempStatus);
					}
					else
						System.out.println("There is no bank account with approval status PENDING!");
				}
				System.out.println("\n");
			}
			
			//Step 2a: Approve and change User Status in CUSTOMER for a specific User
			//Step 2b: Reject and change User Status in INACTIVE for a specific User
			do
			{
				System.out.println("Console Bank EMPLOYEE New Bank Account Approval Process MENU");
				System.out.println("============================================================");
				System.out.println("Please select your option:");
				System.out.println("--------------------------\n");
				System.out.println("1) Process a specific Bank Account Approval Request");
//				System.out.println("1) Process all Approval Requests, one by one"); //(? optional)
				System.out.println("2) Returning to previous menu");
				System.out.println("Please enter appropriate choice(1-2) :) ");
				
				choice = Integer.parseInt(scannerEmployeeMenu.nextLine());
				
				switch (choice)
				{
					case 1:
						System.out.println("Please enter the New Bank's Account Id whose approval request is to be processed:");
						
						int id = Integer.parseInt(scannerEmployeeMenu.nextLine());
			
						//Code Here for SERVICE LAYER
//						System.out.println("Launch the Service Layer");
						BankAccount newBankAccount = bankAccountService.getBankAccountById(id);
						
//						System.out.println(newBankAccount);
						
						if (newBankAccount!=null)
						{
							System.out.println("\nBank Account with id " + id+" was found. It's details are : ");
							System.out.println("User: "+ userSession.getId() + " | " 
								+ userSession.getFirstName() + " " + userSession.getLastName() 
								+ " | New bank account details: | Id: " + newBankAccount.getBankAccountId() 
								+ " | Account Number: " + newBankAccount.getBankAccountNumber()
								+ " | Owner Id: " + newBankAccount.getAccountOwnerId()
								+ " | Account Approval Status Pending?: " + newBankAccount.isBankAccountApprovalPending()  
								+ " | Approval Status: " + newBankAccount.isBankAccountApproved());
							log.info("\nBank Account found with id " + id+" details are : ");
							log.info("User: "+ userSession.getId() + " | " 
								+ userSession.getFirstName() + " " + userSession.getLastName() 
								+ " | New bank account details: | Id: " + newBankAccount.getBankAccountId() + 
									" | Account Number: " + newBankAccount.getBankAccountNumber() + 
									" | Owner Id: " + newBankAccount.getAccountOwnerId() + 
									" | Account Approval Status Pending?: " + newBankAccount.isBankAccountApprovalPending() +  
									" | Approval Status: " + newBankAccount.isBankAccountApproved());
							
							// to print only needed details: id, fname, lname, email, crtStatus
							System.out.println("Approve the request of Activating Customer's Bank Account? (Y - approve / N - reject)");

							//change status code

							choiceStatus = scannerEmployeeMenu.nextLine();
							
							switch (choiceStatus.toUpperCase())
							{
							case "Y":
								//Code Here for SERVICE LAYER
								//change User Status to CUSTOMER, bank_acct_appr_status to true, bank_acct_appr_pending to false
								newBankAccount.setStatusBankAccount(StatusAccount.APPROVED);	//set New Bank Account status to 'APPROVED'
								Timestamp dateBankAccountApproval = new Timestamp(System.currentTimeMillis());
								newBankAccount.setDateBankAccountApproval(dateBankAccountApproval);
								newBankAccount.setBankAccountApprovalPending(false);	//set value to false - Customer Account approval is done
								newBankAccount.setBankAccountApproved(true);			//set value to true - this Bank Account is approved as Customer 
								bankAccountService.updateBankAccount(userSession, newBankAccount);
								System.out.println("\nCustomer's bank account status was updated in the database. Details:");
								System.out.println("The account with id " + newBankAccount.getBankAccountId()
									+ " and account number: " +newBankAccount.getBankAccountNumber()
									+ " owned by Customer with id: "+ newBankAccount.getAccountOwnerId() 
									+ " has now " + newBankAccount.getStatusBankAccount()
									+ " status.");									
								log.info("\nCustomer's bank account status was updated in the database. Details:");
								log.info("The account with id " + newBankAccount.getBankAccountId()
									+ " and account number: " +newBankAccount.getBankAccountNumber()
									+ " owned by Customer with id: "+ newBankAccount.getAccountOwnerId() 
									+ " has now " + newBankAccount.getStatusBankAccount()
									+ " status.");									
								break;
							case "N":
								//Code Here for SERVICE LAYER
								//change Bank Account status to REJECTED, bank_acct_appr_status to false, bank_acct_appr_pending to false
								newBankAccount.setStatusBankAccount(StatusAccount.DECLINED);	//set New Bank Account status to 'DECLINED'
								dateBankAccountApproval = new Timestamp(System.currentTimeMillis());
								newBankAccount.setDateBankAccountApproval(dateBankAccountApproval);
								newBankAccount.setBankAccountApprovalPending(false); 	//set value to false - Customer Account approval is done
								newBankAccount.setBankAccountApproved(false);	//set value to false - this User's request to be approved as Customer is denied 
								bankAccountService.updateBankAccount(userSession, newBankAccount);
								System.out.println("\nCustomer's bank account status was updated in the database. Details:");
								System.out.println("The account with id " + newBankAccount.getBankAccountId()
									+ " and account number: " +newBankAccount.getBankAccountNumber()
									+ " owned by Customer with id: "+ newBankAccount.getAccountOwnerId() 
									+ " has now " + newBankAccount.getStatusBankAccount()
									+ " status.");									
								log.info("\nCustomer's bank account status was updated in the database. Details:");
								log.info("The account with id " + newBankAccount.getBankAccountId()
									+ " and account number: " +newBankAccount.getBankAccountNumber()
									+ " owned by Customer with id: "+ newBankAccount.getAccountOwnerId() 
									+ " has now " + newBankAccount.getStatusBankAccount()
									+ " status.");
								break;
							default: 
								System.out.println("Please enter only 'Y' or 'N' to continue!");
								break;
							}
						}
						break;
						
					case 2:
						//returning to previous menu
//						scannerEmployeeMenu.close();
						break;
					default:
						System.out.println("Please enter a number between 1 and 2!");
						break;
				}
			} while (choice != 2);
		} 
		catch (BusinessException e) 
		{	
			System.out.println(e.getMessage());
//			scannerEmployeeMenu.close();
		}
		System.out.println("");		
	}

	@Override
	public void getBankAccountListyByUser() 
	{
		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();
		UserService userService = new UserServiceImpl();

//		System.out.println("View Bank Accounts by User method allows a Bank Employee to see the Bank Accounts of a specified User.");
		log.info("View Bank Accounts by User method allows a Bank Employee to see the Bank Accounts of a specified User.");
		
//		Scanner scannerEmployeeMenu = new Scanner(System.in);
		System.out.println("Please enter the Id of the Customer whose Bank Accounts you want to retrieve:");

		User selectedUser;
		List<BankAccount> bankAccountsListByUser;

		try 
		{
			int userId = Integer.parseInt(scannerEmployeeMenu.nextLine());
			
			//Code Here for SERVICE LAYER
			//get all the bank accounts a User has			
			selectedUser = userService.getUserById(userId); 	//retrieve all bank account owner by an User/Customer
//			System.out.println("User's details are: "+selectedUser);
			
			bankAccountsListByUser = bankAccountService.getBankAccountsListByUser(selectedUser);			
			
			if(bankAccountsListByUser!=null && bankAccountsListByUser.size()>0)
			{
				System.out.println("\nWe found " + bankAccountsListByUser.size()
					+" bank accounts opened by the User: " 
					+ selectedUser.getFirstName() + " " + selectedUser.getLastName()
					+ "... Detailed list is:");
				log.info("We found " + bankAccountsListByUser.size()
					+" bank accounts opened by the User: " 
					+ selectedUser.getFirstName() + " " + selectedUser.getLastName() 
					+ "... Detailed list is:");
				
				for(BankAccount ba:bankAccountsListByUser)
				{
					Date date = new Date(ba.getDateBankAccountCreation().getTime());
					SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMMM yyyy"); 

					System.out.println("Bank Account Id: " + ba.getBankAccountId() 
						+ " | Bank Account Number: " + ba.getBankAccountNumber() 
						+ " | Bank Account active since: " + formatter.format(date)
						+ " | Bank Account status: " + ba.getStatusBankAccount()
						+ " | Bank Account Balance: " + ba.getAccountBalance());
					log.info("Bank Account Id: " + ba.getBankAccountId() 
						+ " | Bank Account Number: " + ba.getBankAccountNumber() 
						+ " | Bank Account active since: " + formatter.format(date)
						+ " | Bank Account status: " + ba.getStatusBankAccount()
						+ " | Bank Account Balance: " + ba.getAccountBalance());
				}
			}
		}
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
//			scannerEmployeeMenu.close();
		}
		System.out.println("");
	}

	@Override
	public void getAllTransactions() 
	{
		//instantiate an object that will be used () to transfer data to and from Service layer 
		BankAccountService bankAccountService = new BankAccountServiceImpl();
//		UserService userService = new UserServiceImpl();

//		System.out.println("getAllTransactions method allows a Bank Employee to see all the trasactions.");
		log.info("getAllTransactions method allows a Bank Employee to see all the trasactions.");

		List<BankTransaction> bankTransactionsListAll;
		
		try 
		{	//Code Here for SERVICE LAYER
			bankTransactionsListAll = bankAccountService.getAllTransactions();

			if(bankTransactionsListAll!=null && bankTransactionsListAll.size()>0)
			{
				System.out.println("\nWe found " + bankTransactionsListAll.size()
					+" transactions performed and recorded in bank's database!... Detailed list is:"); 
				log.info("We found " + bankTransactionsListAll.size()
					+" transactions performed and recorded in bank's database!... Detailed list is:");
			}
			else
			{
				System.out.println("No transactions found!");
			}			

				for(BankTransaction bt:bankTransactionsListAll)
				{
					Date date = new Date(bt.getTransactionDate().getTime());
					SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM yyyy"); 
	
					System.out.println("Transaction Id: " + bt.getIdTransaction() 
						+ " | Source Bank Account Id: " + bt.getSourceBankAccountId() 
						+ " | Destination Bank Account Id: " + bt.getDestinationBankAccountId() 
						+ " | Transaction Amount: " + bt.getAmount() 
						+ " | Transaction Type" + bt.getTransactionType() 
						+ " | Transaction Date: " + formatter.format(date));
					log.info("Transaction Id: " + bt.getIdTransaction() 
						+ " | Source Bank Account Id: " + bt.getSourceBankAccountId() 
						+ " | Destination Bank Account Id: " + bt.getDestinationBankAccountId() 
						+ " | Transaction Amount: " + bt.getAmount() 
						+ " | Transaction Type" + bt.getTransactionType() 
						+ " | Transaction Date: " + formatter.format(date));
				}
		}
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}
		System.out.println("");
	}		
}