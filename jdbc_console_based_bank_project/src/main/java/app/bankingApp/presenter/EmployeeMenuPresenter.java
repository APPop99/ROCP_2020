package app.bankingApp.presenter;

import app.bankingApp.model.BankAccount;
import app.bankingApp.model.User;

public interface EmployeeMenuPresenter 
{

	public void showEmployeeMenu(User userSession); 

	public void approveCustomerUserAccount(User userSession);

	public void approveBankAccount(User userSession);

	public void getBankAccountListyByUser();

	public void getAllTransactions();

}
