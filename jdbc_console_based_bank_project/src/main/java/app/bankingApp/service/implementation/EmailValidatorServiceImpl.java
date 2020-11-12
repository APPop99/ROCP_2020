package app.bankingApp.service.implementation;

import org.apache.log4j.Logger;

import app.bankingApp.presenter.implementation.MainMenuPresenterImpl;
import app.bankingApp.service.EmailValidatorService;

public class EmailValidatorServiceImpl implements EmailValidatorService 
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);
	
	public EmailValidatorServiceImpl()
	{
		
	}

	public boolean isEmailValid(final String email)
	{
		boolean isEmailFormatValid = false;
		
		final String EMAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		
		while (isEmailFormatValid == false)	
		{
			//Test if the format of email is valid, as it is used as username	
			if (email.matches(EMAIL_PATTERN))
			{
//				System.out.println("Email format is valid!");
				log.info("Email format is valid");
				isEmailFormatValid = true;
				break;
			} 
			else 
			{
				isEmailFormatValid = false;
				System.out.println("Email format is not valid. Please enter an email with a valid format!");
				log.info("Email format is not valid. Please enter an email with a valid format!");
			}
		}	
		
		return isEmailFormatValid;
	}
}
