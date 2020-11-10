package app.bankingApp.presenter;

import app.bankingApp.model.User;

public interface CustomerMenuPresenter 
{
	public void showCustomerMenu(User userSession);

	public void showNonCustomerMenu(User userSession);

	public void addNonCustomerUserToApprovalTable(User userSession);
}