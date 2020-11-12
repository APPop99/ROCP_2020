package app.bankingApp.presenter.implementation;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;

import app.bankingApp.presenter.CustomerMenuPresenter;
import app.bankingApp.presenter.EmployeeMenuPresenter;
import app.bankingApp.presenter.MainMenuPresenter;
import app.bankingApp.service.UserService;
import app.bankingApp.service.EmailValidatorService;
import app.bankingApp.service.implementation.EmailValidatorServiceImpl;
import app.bankingApp.service.implementation.UserServiceImpl;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.StatusUser;
import app.bankingApp.model.User;

public class MainMenuPresenterImpl implements MainMenuPresenter 
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);

	public void startMenu() 
	{		
		Scanner scannerMainMenu = new Scanner(System.in);
		
		System.out.println("Welcome to Console based Bank!");
		System.out.println("------------------------------");
		
		int choice = 0;
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
			
			try 
			{
				choice = Integer.parseInt(scannerMainMenu.nextLine());
			} 
			
			catch (NumberFormatException e) 
			{
				System.out.println("Please enter a number between 1 and 3!");
			}
			
			switch (choice)
			{
				case 1:
//					System.out.println("Method <New user registration> will create a new User!");
					log.info("Method <New user registration> will create a new User!");
					MainMenuPresenter createUser = new MainMenuPresenterImpl();
					createUser.createNewUser();
					break;
				case 2:
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
							System.out.println("This customer does not have a Customer Account created/approved and has status: "+userSession.getStatusUser()+"\n");
							log.info("This customer does not have a Customer Account created/approved and has status: "+userSession.getStatusUser()+"\n");
							customerMenu.showNonCustomerMenu(userSession);
							break;
						case CUSTOMER:
							System.out.println("This customer has a bank account created and has status: "+userSession.getStatusUser()+"\n");
							log.info("This customer has a bank account created and has status: "+userSession.getStatusUser()+"\n");
							customerMenu.showCustomerMenu(userSession);
							break;
						case EMPLOYEE:
							System.out.println("This user is an employee, with role: "+userSession.getStatusUser()+"\n");
							log.info("This user is an employee, with role: "+userSession.getStatusUser()+"\n");
							employeeMenu.showEmployeeMenu(userSession);
							break;
						case INACTIVE:
							System.out.println("User logged in: " + userSession.getFirstName() + userSession.getLastName() + " is not an active user. \n"
									+ "This user has de-activated (deleted) all his/her bank accounts \n");
							log.info("User logged in: " + userSession.getFirstName() + userSession.getLastName() + " is not an active user. \n"
									+ "This user has de-activated (deleted) all his/her bank accounts \n");
							customerMenu.showNonCustomerMenu(userSession);
//							System.out.println("Please select an active user!");
							break;
					}	
					break;
				case 3:
					System.out.println("Thank you for using Console Bank App V1.0... We expect to see you back very soon!");
					scannerMainMenu.close();
					break;
				default:
					System.out.println("Please enter 1, 2 or 3 to continue!");
					break;
			}
		} while (choice != 3); 	
	}
	
	public void createNewUser()
	{
		Scanner scannerUserCreation = new Scanner(System.in);

		//instantiate an object that will be used () to transfer data to and from Service layer 
		UserService userService = new UserServiceImpl();
		
		System.out.println("Please Enter the details for the new User you wish to save in the db:");
		try 
		{
			System.out.print("Please enter User's first name: ");
			String firstName=scannerUserCreation.nextLine();
			
			System.out.print("Please enter User's last name: ");
			String lastName=scannerUserCreation.nextLine();
			
			System.out.print("Please enter User's phone number: ");
			Long phoneNumber = Long.parseLong(scannerUserCreation.nextLine());
			
			String email = "";
			EmailValidatorService isEmailFormatValid = new EmailValidatorServiceImpl();
			boolean isEmailFormatValidAndUnique = false;

			//instantiate an object that will be used () to transfer data to and from Service layer 
			UserService userServiceEmailCheck = new UserServiceImpl();
			
			//Email is read from keyboard and validated
			//This part (email validation) should be optimized at refactor and moved to an Util package
			while(isEmailFormatValidAndUnique == false)
			{
				do 
				{
					System.out.print("Please enter User's email: ");
					email = scannerUserCreation.nextLine();					
				} while(userServiceEmailCheck.isUserByEmailDuplicate(email) != true);
				
				
//				while (isEmailFormatValid == false)	
//				{
//					//Test if the format of email is valid, as it is used as username
//					
//					if (email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) 
//					{
////						System.out.println("Email format is valid!");
//						log.info("Email format is valid");
//						isEmailFormatValid = true;
//						break;
//					} 
//					else 
//					{
//						isEmailFormatValid = false;
//						System.out.println("Email format is not valid. Please enter an email with a valid format!");
//						log.info("Email format is not valid. Please enter an email with a valid format!");
//					}
//				}
				
				if (isEmailFormatValid.isEmailValid(email) == true)
				{				
					isEmailFormatValidAndUnique = true;
				}
			}
			
			//Date of Birth is read from keyboard and validated
			//This part (dob processing) should be optimized at refactor and moved to an Util package
			System.out.print("Please enter User's date of birth (format dd/mm/yyy): ");
			String dobString = scannerUserCreation.nextLine();
			
			SimpleDateFormat sdfDobString = new SimpleDateFormat("dd/MM/yyyy");
			sdfDobString.setLenient(false);
			Date dob=null;
			
			try 
			{
				dob = sdfDobString.parse(dobString);
			} 
				catch (ParseException e1) 
			{
					System.out.println(e1);
			}
			
			//Password is read from keyboard and validated
			//This part (email validation) should be optimized at refactor and moved to an Util package
			String password= "";
			boolean isPasswordValid = false;
			
			while (isPasswordValid == false)
			{
				System.out.println("Please enter User's password: \n"
						+ "(Please use a combination between 8 and 40 letters (uppercaser & lowercase), "
						+ "numbers and special characters, at least one of each): ");
				password = scannerUserCreation.nextLine();
				
				if (password.matches("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})")) 
				{
					System.out.println("Password is valid!");
					log.info("Password is valid");
					isPasswordValid = true;
					break;
				} 
				else 
				{
					isPasswordValid = false;
					System.out.println("Password is not valid!");
					log.info("Password is not valid!");
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
				System.out.println("The new user is Employee? (Y/N)");
				choiceStatus = scannerUserCreation.nextLine();

				switch (choiceStatus)
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
						System.out.println("Please enter only 'Y' or 'N' to continue!");
//						choiceBool = true;
						break;
				}	
			} while (choiceBool != true);
						
			if (userService.createUser(userNew)>0)
			{
				System.out.println("User is created in DB with below details: ");
				log.info("User is created in DB with below details: ");				
				
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
		}
		System.out.println("");
//		scannerUserCreation.close();
	}

//	private EmailValidator isEmailValid(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public boolean EmailValidator(String email)
//	{
//		boolean isEmailFormatValid = false;
//		
//		final String PASSWORD_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//		
//		while (isEmailFormatValid == false)	
//		{
//			//Test if the format of email is valid, as it is used as username
//			
//			
//			if (email.matches(PASSWORD_PATTERN))
//			{
////				System.out.println("Email format is valid!");
//				log.info("Email format is valid");
//				isEmailFormatValid = true;
//				break;
//			} 
//			else 
//			{
//				isEmailFormatValid = false;
//				System.out.println("Email format is not valid. Please enter an email with a valid format!");
//				log.info("Email format is not valid. Please enter an email with a valid format!");
//			}
//		}	
//		
//		return isEmailFormatValid;
//	}
	
	@Override
	public User loginUser() 
	{
		Scanner scannerUserLogin = new Scanner(System.in);

		//instantiate an object that will be used () to transfer data to and from Service layer 
		UserService userService = new UserServiceImpl();
		User userLogin = new User();
		List<User> userList;
		
		System.out.println("Please enter your credentials to login:");
		try 
		{
			System.out.print("Please enter your username (email): ");
			String email=scannerUserLogin.nextLine();
			System.out.print("Please enter your password: ");
			String password=scannerUserLogin.nextLine();
			
			//retrieve all users using the same email as username
			userList = userService.getUsersByEmail(email);
			
			if (loginSuccessful(email, password, userList)) 
			{
				
//				System.out.println("Login successful!\n");
				log.info("Login successful!\n");
				userLogin = selectUser(email, password, userList); 
				
				Date date = new Date(userLogin.getDateUserAccountCreation().getTime());
				SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMMM yyyy"); 
				
				System.out.println("User logged in successfully with the following details:");
				System.out.println("User id: " + userLogin.getId() + " | " + userLogin.getFirstName() + " " 
						+ userLogin.getLastName() + " | " + userLogin.getEmail() + " | " + formatter.format(date));
				log.info("User logged in successfully with the following details:");
				log.info("User id: " + userLogin.getId() + " | " + userLogin.getFirstName() + " " 
						+ userLogin.getLastName() + " | " + userLogin.getEmail() + " | " + formatter.format(date));
				return userLogin;
			}
			else {
				System.out.println("Login failed. Credentials are not correct. Please try again.\n");
				log.info("Login failed. Credentials are not correct. Please try again.\n");
//				scannerUserLogin.close();
				return null;
			}
		} 
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}
		System.out.println("");
//		scannerUserLogin.close();
		return null;
	}
	
	public boolean loginSuccessful(String email, String password, List<User> userList) {
		
		for(User user : userList) {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public User selectUser(String email, String password, List<User> userList) {
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
	public Boolean validate(String arg) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
