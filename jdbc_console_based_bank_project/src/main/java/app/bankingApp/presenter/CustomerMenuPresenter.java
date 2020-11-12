package app.bankingApp.presenter;

import java.util.List;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.User;

public interface CustomerMenuPresenter 
{
	public void showCustomerMenu(User userSession);

	public void showNonCustomerMenu(User userSession);

	public void addNonCustomerUserToApprovalTable(User userSession);

	public void createNewBankAccount(User userSession);

	public List<BankAccount> getBankAccountBalance(User userSession);

	public void updateDepositFundsIntoBankAccount(User userSession);

	public void updateWithdrawFundsFromBankAccount(User userSession);
}