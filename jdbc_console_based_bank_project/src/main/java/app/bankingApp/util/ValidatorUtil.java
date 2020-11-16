package app.bankingApp.util;

import java.util.Date;
import java.util.List;

import app.bankingApp.model.BankAccount;
import app.bankingApp.model.User;

public interface ValidatorUtil 
{

	boolean isEmailValid(String email);

	boolean isPasswordValid(String password);
	
	Date dateParsing(String dobString);
	
	public BankAccount verifySelectedBankAccount(User userSession, List<BankAccount> bankAccountsListByUser);

	boolean isPhoneNumberFormatValid(String phoneNumberAsString);
	
	boolean isLoginSuccessful(String email, String password, List<User> userList);

	User selectUser(String email, String password, List<User> userList);

	boolean isMainMenuChoiceValid(String choice);

}
