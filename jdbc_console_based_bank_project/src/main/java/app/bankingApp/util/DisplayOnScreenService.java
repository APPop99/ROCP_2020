package app.bankingApp.util;

import java.util.List;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
import app.bankingApp.model.User;

public interface DisplayOnScreenService 
{

	void printListOfBankAccountsByUser(List<BankAccount> bankAccountsListByUser, User userSession) throws BusinessException;

	void printListOfTransferTransactionsByRecipientUser(List<BankTransaction> tempTransferBankTransactionsListByRecipientUser,
			User userSession) throws BusinessException;

}
