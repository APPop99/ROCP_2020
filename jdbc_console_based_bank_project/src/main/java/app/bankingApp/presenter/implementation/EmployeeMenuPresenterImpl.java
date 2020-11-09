package app.bankingApp.presenter.implementation;

import org.apache.log4j.Logger;

import app.bankingApp.model.User;
import app.bankingApp.presenter.EmployeeMenuPresenter;

public class EmployeeMenuPresenterImpl implements EmployeeMenuPresenter 
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);

	public EmployeeMenuPresenterImpl(User userSession) 
	{
		
	}

	@Override
	public void showEmployeeMenu(User userSession) 
	{
		System.out.println("Employee Menu!");
		//employee can approve or reject an account
		//view a customer's bank accounts
		//view a log of all transactions (of a specific account or all accounts):
			//open accounts
			//funds deposit or funds withdrawal
			//transfers posted or accepted
	}
}
