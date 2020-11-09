package app.bankingApp.presenter.implementation;

import org.apache.log4j.Logger;

import app.bankingApp.model.User;
import app.bankingApp.presenter.CustomerMenuPresenter;

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
		//register for a customer account
		//open an account with a starting balance
		//view the balance of an account
		//make a withdrawal from a specific account (reject invalid transactions)
		//make a deposit into a specific account (reject invalid transactions)
		//post a money transfer to another account
		//accept money transfer from another account
	}
}
