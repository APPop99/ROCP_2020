package app.bankingApp.presenter.implementation;

import java.util.Scanner;

import org.apache.log4j.Logger;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.StatusUser;
import app.bankingApp.model.User;
import app.bankingApp.presenter.CustomerMenuPresenter;
//import app.bankingApp.presenter.MainMenuPresenter;
import app.bankingApp.service.UserService;
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
		//open an account with a starting balance
		//view the balance of an account
		//make a withdrawal from a specific account (reject invalid transactions)
		//make a deposit into a specific account (reject invalid transactions)
		//post a money transfer to another account
		//accept money transfer from another account
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
						}
						System.out.println("User with " + userSession.getId() + " | "+userSession.getFirstName() 
							+ " "+userSession.getLastName()+" already submitted a request for Bank's approval for a Customer Acoount");
						break;
					}
					System.out.println("User with " + userSession.getId() + " | "+userSession.getFirstName() 
					+ " "+userSession.getLastName()+" has already Customer status");
					break;
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
				System.out.println("Do you like to have your account registered as Customer Account ? (Y/N)");
				choiceStatus = scannerAddNonCustomerUserToApprovalTable.nextLine();

				switch (choiceStatus)
				{
					case "Y":
						//Code Here for SERVICE LAYER
						System.out.println("User with id " + userSession.getId() + " will be saved in temp db waiting for approval.");
						log.info("User with id " + userSession.getId() + "will saved in temp db waiting for approval.");
						userSession.setCustomerApprovalPending(true); 	//set value to true - Customer Account approval is pending
						userSession.setCustomerStatusApproved(false);	//set value to false - this User has not yet been approved as Customer 
						userService.addNonCustomerUserToApprovalTable(userSession);
						choiceBool = true;
						break;
					case "y":
						//Code Here for SERVICE LAYER
						System.out.println("User with id " + userSession.getId() + " will be saved in temp db waiting for approval.");
						log.info("User with id " + userSession.getId() + "will saved in temp db waiting for approval.");
						userSession.setCustomerApprovalPending(true); 	//set value to true - Customer Account approval is pending
						userSession.setCustomerStatusApproved(false);	//set value to false - this User has not yet been approved as Customer 
						userService.addNonCustomerUserToApprovalTable(userSession);
						choiceBool = true;
						break;
					case "N":
						break;
					case "n":
						break;
					default: 
						System.out.println("Please enter only 'Y' or 'N' to continue!");
//						choiceBool = true;
						break;
				}	
			} while (choiceBool != true);
						
			if (userService.addNonCustomerUserToApprovalTable(userSession)>0)
			{
				System.out.println("User: "+ userSession.getId() + " | " + userSession.getFirstName() + " " 
						+ userSession.getLastName()+" was saved in temp db waiting for approval");
				log.info("User: "+ userSession.getId() + " | " + userSession.getFirstName() + " " 
						+ userSession.getLastName()+" was saved in temp db waiting for approval");				
//				scannerUserCreation.close();
			}	
		}
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
			log.info(e.getMessage());
		}
		System.out.println("");		
	}
}
