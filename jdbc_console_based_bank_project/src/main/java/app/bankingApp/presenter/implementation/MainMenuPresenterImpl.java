package app.bankingApp.presenter.implementation;

import java.sql.Timestamp;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;

import app.bankingApp.presenter.CustomerMenuPresenter;
import app.bankingApp.presenter.EmployeeMenuPresenter;
import app.bankingApp.presenter.MainMenuPresenter;
import app.bankingApp.service.UserService;
import app.bankingApp.util.ValidatorUtil;
import app.bankingApp.util.implementation.ValidatorUtilImpl;
import app.bankingApp.service.implementation.UserServiceImpl;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.StatusUser;
import app.bankingApp.model.User;
//import app.bankingApp.mainApp.*;

public class MainMenuPresenterImpl implements MainMenuPresenter 
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);
	private final Scanner scannerMainMenu = new Scanner(System.in);
	
	public void startMenu() 
	{		
//		Scanner scannerMainMenu = new Scanner(System.in);
		ValidatorUtil choiceMainMenuValidator = new ValidatorUtilImpl();
		
		System.out.println("Welcome to Console based Bank!");
		System.out.println("------------------------------");
		
		String choice;
		try
		{
			do 
			{
				do
				{
					System.out.println("Console Bank Entry MENU");
					System.out.println("=======================");
					System.out.println("Please select your option:");
					System.out.println("--------------------------\n");
					System.out.println("1) New USER registration");
					System.out.println("2) Existing USER login");
					System.out.println("3) EXIT");
					System.out.println("Please enter appropriate choice(1-3) :) ");
				
					choice = scannerMainMenu.nextLine();

				} while((choiceMainMenuValidator.isMainMenuChoiceValid(choice) == false));
				
					
				switch (choice)
				{
					case "1":
	//					System.out.println("Method <New user registration> will create a new User!");
						log.info("Method <New user registration> will create a new User!");
						MainMenuPresenter createUser = new MainMenuPresenterImpl();
						createUser.createNewUser();
						break;
					case "2":
	//					System.out.println("Method <User login> will allow a registered user to login!");
						log.info("Method <User login> will allow a registered user to login!");
						MainMenuPresenter loginUserMenu = new MainMenuPresenterImpl();
						User userSession = loginUserMenu.loginUser();
						
						//Start Customer Menu or Employee Menu
						CustomerMenuPresenter customerMenu = new CustomerMenuPresenterImpl(userSession);
						EmployeeMenuPresenter employeeMenu = new EmployeeMenuPresenterImpl(userSession);
	
	//					scannerUserLogin.close();
						switch (userSession.getStatusUser()) 
						{
							case NONCUSTOMER:
								System.out.println("\nThis customer does not have a Customer Account created/approved and has status: "+userSession.getStatusUser()+"\n");
								log.info("This customer does not have a Customer Account created/approved and has status: "+userSession.getStatusUser()+"\n");
								customerMenu.showNonCustomerMenu(userSession);
								break;
							case CUSTOMER:
								System.out.println("\nThis customer has a bank account created and has status: "+userSession.getStatusUser()+"\n");
								log.info("This customer has a bank account created and has status: "+userSession.getStatusUser()+"\n");
								customerMenu.showCustomerMenu(userSession);
								break;
							case EMPLOYEE:
								System.out.println("\nThis user is an employee, with role: "+userSession.getStatusUser()+"\n");
								log.info("This user is an employee, with role: "+userSession.getStatusUser()+"\n");
								employeeMenu.showEmployeeMenu(userSession);
								break;
							case INACTIVE:
								System.out.println("\nUser logged in: " + userSession.getFirstName() + userSession.getLastName() + " is not an active user. \n"
										+ "This user has de-activated (deleted) all his/her bank accounts \n");
								log.info("User logged in: " + userSession.getFirstName() + userSession.getLastName() + " is not an active user. \n"
										+ "This user has de-activated (deleted) all his/her bank accounts \n");
								customerMenu.showNonCustomerMenu(userSession);
	//							System.out.println("Please select an active user!");
								break;
						}	
						break;
					case "3":
						System.out.println("\nThank you for using Console Bank App V1.0... We expect to see you back very soon!");
//						scannerMainMenu.close();
						break;
					default:
						System.out.println("\nPlease enter 1, 2 or 3 to continue!");
						break;
				}
			} while (!choice.equalsIgnoreCase("3")); 	
		}
		catch (NumberFormatException e) 
		{
			System.out.println("Data entered has an invalid format. Please enter a number between 1 and 3!");
		}
	}
	
	public void createNewUser()
	{
//		Scanner scannerMainMenu = new Scanner(System.in);
		
		//instantiate an object that will be used () to transfer data to and from Service layer 
		UserService userService = new UserServiceImpl();
		
		System.out.println("\nPlease Enter the details for the new User you wish to save in the db:");
		try 
		{
			System.out.print("Please enter User's first name: ");
			String firstName=scannerMainMenu.nextLine();
			
			System.out.print("Please enter User's last name: ");
			String lastName=scannerMainMenu.nextLine();
			
			//phone number is read from keyboard and validate phone number
			ValidatorUtil isPhoneNumberFormatValidObj = new ValidatorUtilImpl();
			Long phoneNumber = 0L;
			boolean isPhoneNumberValid = false;
			
			while (isPhoneNumberValid == false)
			{
				System.out.print("Please enter User's phone number (10 digits): ");
				String phoneNumberAsString = scannerMainMenu.nextLine();
				
				if (isPhoneNumberFormatValidObj.isPhoneNumberFormatValid(phoneNumberAsString) == true)
				{				
					phoneNumber = Long.parseLong(phoneNumberAsString);
					isPhoneNumberValid = true;			
				}		
			} 
				
			//Email is read from keyboard and validated
			String email = "";
			ValidatorUtil emailFormatValidator = new ValidatorUtilImpl();
			boolean isEmailFormatValidAndUnique = false;

			//instantiate an object that will be used () to transfer data to and from Service layer 
			UserService userServiceEmailCheck = new UserServiceImpl();
			
			while(isEmailFormatValidAndUnique == false)
			{
				do 
				{
					System.out.print("Please enter User's email. It should be an unique email, not used by another User: ");
					email = scannerMainMenu.nextLine();					
				} while(userServiceEmailCheck.isUserByEmailDuplicate(email) != true);
				
				if (emailFormatValidator.isEmailValid(email) == true)
				{				
					isEmailFormatValidAndUnique = true;
				}
			}
			
			//Date of Birth is read from keyboard and validated
			System.out.print("Please enter User's date of birth (format dd/mm/yyy): ");
			String dobString = scannerMainMenu.nextLine();
			ValidatorUtil dateParserObj = new ValidatorUtilImpl();
			Date dob = dateParserObj.dateParsing(dobString);
			
			//Password is read from keyboard and validated
			//This part (password validation) should be optimized at refactor and moved to Service package
			String password= "";
			ValidatorUtil passwordFormatValidator = new ValidatorUtilImpl();
			boolean isPasswordValid = false;
			
			while (isPasswordValid == false)
			{
				System.out.println("Please enter User's password: \n"
						+ "(Please use a combination between 8 and 40 letters (uppercaser & lowercase), "
						+ "numbers and special characters, at least one of each): ");
				password = scannerMainMenu.nextLine();
			
				if (passwordFormatValidator.isPasswordValid(password) == true)
				{				
					isPasswordValid = true;
				}
			};
						
			Timestamp dateUserAccountCreation = new Timestamp(System.currentTimeMillis()); 
			Timestamp dateUserAccountDeletion = null; 
			
			//Code Here for SERVICE LAYER
			User userNew = new User(firstName, lastName, email, dob, phoneNumber, password);
			userNew.setDateUserAccountCreation(dateUserAccountCreation);	
			userNew.setDateUserAccountDeletion(dateUserAccountDeletion);	
			
			String choiceStatus;
			boolean choiceBool = false;

			do
			{
				System.out.println("The new user is Employee of the bank? (Y/N)");
				choiceStatus = scannerMainMenu.nextLine();

				switch (choiceStatus.toUpperCase())
				{
					case "Y":
						userNew.setStatusUser(StatusUser.EMPLOYEE);
						choiceBool = true;
						break;
					case "N":
						userNew.setStatusUser(StatusUser.NONCUSTOMER);
						choiceBool = true;
						break;
					default: 
						System.out.println("Please enter only 'Y'/y' or 'N'/'n' to continue!");
//						choiceBool = true;
						break;
				}	
			} while (choiceBool != true);
						
			if (userService.createUser(userNew)>0)
			{
				System.out.println("\nUser is created in DB with below details: ");
				log.info("\nUser is created in DB with below details: ");				
				
				Date date = new Date(userNew.getDateUserAccountCreation().getTime());
				SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMMM yyyy"); 
				
				System.out.println("User id: " + userNew.getId() + " | " + userNew.getFirstName() + " " 
						+ userNew.getLastName() + " | " + userNew.getEmail() + " | " + formatter.format(date));				
				log.info("User id: " + userNew.getId() + " | " + userNew.getFirstName() + " " 
						+ userNew.getLastName() + " | " + userNew.getEmail() + " | " + formatter.format(date));
//				scannerUserCreation.close();
			}	
		} 
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
//			scannerMainMenu.close();
		}
		System.out.println("");
	}

	@Override
	public User loginUser() 
	{
//		Scanner scannerUserLogin = new Scanner(System.in);
//		Scanner scannerMainMenu = new Scanner(System.in);
		
		//instantiate an object that will be used () to transfer data to and from Service layer 
		ValidatorUtil isLoginSuccessfulObj = new ValidatorUtilImpl();
		ValidatorUtil returnSelectUser = new ValidatorUtilImpl();
		ValidatorUtil isEmailFormatValidObj = new ValidatorUtilImpl();
//		ValidatorUtil isPasswordFormatValidObj = new ValidatorUtilImpl();

		UserService userService = new UserServiceImpl();
		User userLogin = new User();
		List<User> userList;
		String email;
		String password;

		System.out.println("\nPlease enter your credentials to login:");
		try 
		{
			do
			{
				//test the email format
				do
				{
					System.out.print("Please enter your username (email): ");
					email=scannerMainMenu.nextLine();
				} while((isEmailFormatValidObj.isEmailValid(email) == false));
				
				//read the password from keyboard
				System.out.print("Please enter your password: ");
				password=scannerMainMenu.nextLine();
				
				//retrieve all users using the same email as username
				userList = userService.getUsersByEmail(email);

			} while (isLoginSuccessfulObj.isLoginSuccessful(email, password, userList) == false);
		
			//System.out.println("Login successful!\n");
			log.info("Login successful!\n");
			userLogin = returnSelectUser.selectUser(email, password, userList); 
			
			Date date = new Date(userLogin.getDateUserAccountCreation().getTime());
			SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM yyyy"); 
			
			System.out.println("\nUser logged in successfully with the following details:");
			System.out.println("User id: " + userLogin.getId() + " | " + userLogin.getFirstName() + " " 
					+ userLogin.getLastName() + " | " + userLogin.getEmail() + " | " + formatter.format(date));
			log.info("\nUser logged in successfully with the following details:");
			log.info("User id: " + userLogin.getId() + " | " + userLogin.getFirstName() + " " 
					+ userLogin.getLastName() + " | " + userLogin.getEmail() + " | " + formatter.format(date));
			return userLogin;
		} 
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}
		System.out.println("");
		return null;
	}
}
