package app.bankingApp.DAO.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

import org.apache.log4j.Logger;

import app.bankingApp.DAO.BankAccountOperationsDAO;
import app.bankingApp.DAO.dbUtil.BankAccountOperationsQueries;
import app.bankingApp.DAO.dbUtil.PostgresSqlConnection;
//import app.bankingApp.DAO.dbUtil.UserOperationsQueries;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
import app.bankingApp.model.BankTransaction;
import app.bankingApp.model.StatusAccount;
//import app.bankingApp.model.StatusUser;
import app.bankingApp.model.TransactionType;
import app.bankingApp.model.User;
import app.bankingApp.presenter.implementation.MainMenuPresenterImpl;

public class BankAccountOperationsDAOImpl implements BankAccountOperationsDAO 
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);

	@Override
	public int createNewBankAccount(User user, BankAccount bankAccount) throws BusinessException 
	{
//		System.out.println("Here is the Create New Bank Account method from DAO layer");
		log.info("Here is the Create New Bank Account method from DAO layer");
		int c = 0;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = BankAccountOperationsQueries.INSERTBANKACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			System.out.println("Bank account number: " + bankAccount.getBankAccountNumber() 
//			+ " | Bank Account balance: " + bankAccount.getAccountBalance() 
//			+ " | Bank Account Owner id: " + bankAccount.getAccountOwnerId() 
//			+ " | Date creation: " + bankAccount.getDateBankAccountCreation()
//			+ " | Date approval: " + bankAccount.getDateBankAccountApproval()
//			+ " | Date deletion: " + bankAccount.getDateBankAccountDeletion()
//			+ " | Account status: " + bankAccount.getStatusBankAccount().toString());
			
			preparedStatement.setLong(1, bankAccount.getBankAccountNumber());
			preparedStatement.setDouble(2, bankAccount.getAccountBalance());
			preparedStatement.setInt(3, bankAccount.getAccountOwnerId());
			preparedStatement.setTimestamp(4, bankAccount.getDateBankAccountCreation());
			preparedStatement.setTimestamp(5, bankAccount.getDateBankAccountApproval());
			preparedStatement.setTimestamp(6, bankAccount.getDateBankAccountDeletion());
			preparedStatement.setString(7, bankAccount.getStatusBankAccount().toString());
						
			c = preparedStatement.executeUpdate();
			
//			System.out.println("Bank Account created! Extracting now the bankAccountId!");
			String sql1 = BankAccountOperationsQueries.GETLASTBANKACCTINSERTED;
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			ResultSet resultSet = preparedStatement1.executeQuery();

//			System.out.println("Result Set is: " + resultSet);
			
			if (resultSet.next())
			{
				bankAccount.setBankAccountId(resultSet.getInt("bank_account_id"));						
			} else
			{
				throw new BusinessException("Invalid ID!!!... No matching records found for the ID = "
					+ resultSet.getInt("bank_account_id"));
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
//			System.out.println(e); // take off this line when in production
			log.info(e);
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}		
		return c;
	}

	@Override
	public int updateBankAccount(User user, BankAccount bankAccount) throws BusinessException 
	{
//		System.out.println("Here is the <Change Bank Account Status> method from DAO layer");
		log.info("Here is the <Change Bank Account Status> method from DAO layer");
		int c = 0;
		int d = 0;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
//			System.out.println("UPDATEBANKACCOUNTSTATUS sql will be executed!");
			String sql = BankAccountOperationsQueries.UPDATEBANKACCOUNTSTATUS;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, bankAccount.getDateBankAccountApproval());
			preparedStatement.setInt(2, bankAccount.getBankAccountId());
			preparedStatement.setString(3, bankAccount.getStatusBankAccount().toString());
			
			c = preparedStatement.executeUpdate();
//			System.out.println("Finalized execution for UPDATEBANKACCOUNTSTATUS sql!");

			if (c!=0)
			{
				try (Connection connection1=PostgresSqlConnection.getConnection())
				{
//					System.out.println("UPDATEBANKACCOUNTAPPROVALSTATUS sql will be executed!!");
					sql = BankAccountOperationsQueries.UPDATEBANKACCOUNTAPPROVALSTATUS;
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setBoolean(1, bankAccount.isBankAccountApprovalPending());
					preparedStatement.setBoolean(2, bankAccount.isBankAccountApproved());
					preparedStatement.setInt(3, bankAccount.getBankAccountId());
					
					d = preparedStatement.executeUpdate();
//					System.out.println("Finalized execution for UPDATEBANKACCOUNTAPPROVALSTATUS sql!");
				}
				catch (ClassNotFoundException | SQLException e) 
				{
	//				System.out.println(e);	// take off this line when in production
					log.error(e);
					throw new BusinessException("Internal error occured in UPDATEBANKACCOUNTAPPROVALSTATUS sql... "
							+ "Please contact SYSADMIN");
				}
			}
			else
				System.out.println("Error occured in UPDATEBANKACCOUNTSTATUS sql!");
				log.error("Error occured in UPDATEBANKACCOUNTSTATUS sql!");
		}
		catch (ClassNotFoundException | SQLException e) 
		{
//			System.out.println(e);	// take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured in UPDATEBANKACCOUNTSTATUS sql... Please contact SYSADMIN");
		}
		
		if (d!=0)
		{
			return d;
		} else 
		{
			return 0;
		}
	}

	@Override
	public BankAccount getBankAccountById(int id) throws BusinessException 
	{
//		System.out.println("Here is the <Get Bank Account By Id> method from DAO layer");
		log.info("Here is the <Get Bank Account By Id> method from DAO layer");
		//Instantiate an object to record/handle the results of the query 
		BankAccount bankAccountById = null;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = BankAccountOperationsQueries.GETBANKACCOUNTBYID;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				//The bank account with requested id was found
				bankAccountById = new BankAccount(id, 
						resultSet.getLong("bank_account_number"), 
						resultSet.getInt("bank_account_owner_id"),
						resultSet.getTimestamp("date_bank_acct_creation"), 
						StatusAccount.valueOf(resultSet.getString("type_account_status")),
						resultSet.getDouble("bank_account_balance"));
			} else
			{
				log.warn("Bank Account: " + bankAccountById);
				System.out.println("Invalid ID!!!... No matching records found for the ID = "+id);
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
//			System.out.println(e); // take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured in GETBANKACCOUNTBYID sql... Kindly contact SYSADMIN");
		} 
		return bankAccountById;
	}

	@Override
	public int transferFundsOut(BankAccount sourceBankAccountId, BankAccount destinationBankAccountId, 
			double transferAmount, int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		int c = 0;
		return c;	
	}

	@Override
	public int transferFundsIn(BankAccount sourceBankAccount, BankAccount destinationBankAccount, 
			double transferAmount, int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		int c = 0;
		return c;	
	}

	@Override
	public int withdrawFundsTransaction(BankAccount bankAccount, double newBalanceToRecord, int userId) throws BusinessException 
	{
		int c = 0;
//		System.out.println("Here is the withdrawFundsTransaction method from DAO layer");
		log.info("Here is the withdrawFundsTransaction method from DAO layer");
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = BankAccountOperationsQueries.UPDATEBANKACCOUNTBALANCE;
		
//			double newAccountBalance = bankAccount.newBalanceToRecord() - amountToWithdraw;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, newBalanceToRecord);
			preparedStatement.setInt(2, bankAccount.getBankAccountId());
//			preparedStatement.setInt(3, userId);
			
			c = preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) 
		{
//			System.out.println(e);	// take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured... Please contact SYSADMIN");
		} 
		return c;
	}

	@Override
	public int depositFundsTransaction(BankAccount bankAccount, double newBalanceToRecord, int userId) throws BusinessException 
	{
		int c = 0;
//		System.out.println("Here is the depositFunds method from DAO layer");
		log.info("Here is the depositFunds method from DAO layer");
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = BankAccountOperationsQueries.UPDATEBANKACCOUNTBALANCE;
			
//			double newAccountBalance = bankAccount.getAccountBalance() + amountToDeposit;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, newBalanceToRecord);
			preparedStatement.setInt(2, bankAccount.getBankAccountId());
//			preparedStatement.setInt(3, userId);
			
			c = preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) 
		{
//			System.out.println(e);	// take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured... Please contact SYSADMIN");
		} 
		return c;
	}
	
	@Override
	public int postTransferFundsTransaction(BankAccount selectedSourceBankAccount, double newBalanceToRecord,
			int userId) throws BusinessException 
	{	
		//saves the new balance of source bank account, after a transfer is initiated from a bank account
		int c = 0;
//		System.out.println("Here is the postTransferFundsTransaction method from DAO layer");
		log.info("Here is the postTransferFundsTransaction method from DAO layer");
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = BankAccountOperationsQueries.UPDATEBANKACCOUNTBALANCE;
			
//			double newAccountBalance = selectedSourceBankAccount.getAccountBalance() - newBalanceToRecord;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, newBalanceToRecord);
			preparedStatement.setInt(2, selectedSourceBankAccount.getBankAccountId());
//			preparedStatement.setInt(3, userId);
			
			c = preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) 
		{
//			System.out.println(e);	// take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured... Please contact SYSADMIN");
		} 
		return c;
	}

	@Override
	public int recordTransaction(BankTransaction transactionObj) throws BusinessException 
	{
		int c = 0;
//		System.out.println("Here is the recordTransaction method from DAO layer");
		log.info("Here is the recordTransaction method from DAO layer");

		System.out.println("\nTransaction details: "+ transactionObj);
		
		//need to create 3 separate cases:
		//1. Deposit funds (only destination bank account selected);
		//2. Withdraw funds (only source bank account selected);
		//3. Regular transfer between 2 bank accounts
		if (transactionObj.getSourceBankAccount() == null)
		{
			try (Connection connection=PostgresSqlConnection.getConnection())
			{
				//if the source bank account is null, it is a deposit transaction
				String sql = BankAccountOperationsQueries.RECORDDEPOSITTRANSACTION;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setInt(1, transactionObj.getDestinationBankAccount().getBankAccountId());
				preparedStatement.setDouble(2, transactionObj.getAmount());
				preparedStatement.setTimestamp(3, transactionObj.getTransactionDate());
				preparedStatement.setString(4,transactionObj.getTransactionDescription());
				preparedStatement.setString(5, transactionObj.getTransactionType().toString());
				
				c = preparedStatement.executeUpdate();

				String sql1 = BankAccountOperationsQueries.GETLASTTRANSACTIONINSERTED;
				PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
				ResultSet resultSet = preparedStatement1.executeQuery();

				if (resultSet.next())
				{
					transactionObj.setIdTransaction(resultSet.getInt("id_transaction"));						
				} else
				{
					throw new BusinessException("Invalid ID!!!... No matching records found for the ID = "
						+ resultSet.getInt("id_transaction"));
				}
			}
			catch (ClassNotFoundException | SQLException e) 
			{
//				System.out.println(e);	// take off this line when in production
				log.error(e);
				throw new BusinessException("Internal error occured in RECORDTRANSACTION sql... Please contact SYSADMIN");
			} 
		}
		else
		{
			//the source bank account is not null
			//if the destination account is null, it is a withdraw transaction
			if (transactionObj.getDestinationBankAccount() == null)
			{
				try (Connection connection=PostgresSqlConnection.getConnection())
				{
					String sql = BankAccountOperationsQueries.RECORDWITHDRAWTRANSACTION;
					PreparedStatement preparedStatement = connection.prepareStatement(sql);

					preparedStatement.setInt(1, transactionObj.getSourceBankAccount().getBankAccountId());
					preparedStatement.setDouble(2, transactionObj.getAmount());
					preparedStatement.setTimestamp(3, transactionObj.getTransactionDate());
					preparedStatement.setString(4,transactionObj.getTransactionDescription());
					preparedStatement.setString(5, transactionObj.getTransactionType().toString());
					
					c = preparedStatement.executeUpdate();

					String sql1 = BankAccountOperationsQueries.GETLASTTRANSACTIONINSERTED;
					PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
					ResultSet resultSet = preparedStatement1.executeQuery();

					if (resultSet.next())
					{
						transactionObj.setIdTransaction(resultSet.getInt("id_transaction"));						
					} else
					{
						throw new BusinessException("Invalid ID!!!... No matching records found for the ID = "
							+ resultSet.getInt("id_transaction"));
					}
				}
				catch (ClassNotFoundException | SQLException e) 
				{
//					System.out.println(e);	// take off this line when in production
					log.error(e);
					throw new BusinessException("Internal error occured in RECORDTRANSACTION sql... Please contact SYSADMIN");
				} 
			}
			else
			{	
				//regular transaction between 2 not-null accounts
				try (Connection connection=PostgresSqlConnection.getConnection())
				{
					String sql = BankAccountOperationsQueries.RECORDTRANSACTION;
					PreparedStatement preparedStatement = connection.prepareStatement(sql);

					preparedStatement.setInt(1, transactionObj.getSourceBankAccount().getBankAccountId());
					preparedStatement.setInt(2, transactionObj.getDestinationBankAccount().getBankAccountId());
					preparedStatement.setDouble(3, transactionObj.getAmount());
					preparedStatement.setTimestamp(4, transactionObj.getTransactionDate());
					preparedStatement.setString(5,transactionObj.getTransactionDescription());
					preparedStatement.setString(6, transactionObj.getTransactionType().toString());
					
					c = preparedStatement.executeUpdate();
					
					String sql1 = BankAccountOperationsQueries.GETLASTTRANSACTIONINSERTED;
					PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
					ResultSet resultSet = preparedStatement1.executeQuery();

					if (resultSet.next())
					{
						transactionObj.setIdTransaction(resultSet.getInt("id_transaction"));						
					} else
					{
						throw new BusinessException("Invalid ID!!!... No matching records found for the ID = "
							+ resultSet.getInt("id_transaction"));
					}
				}
				catch (ClassNotFoundException | SQLException e) 
				{
//					System.out.println(e);	// take off this line when in production
					log.error(e);
					throw new BusinessException("Internal error occured in RECORDTRANSACTION sql... Please contact SYSADMIN");
				} 
			}
		}
		return c;
	}

	@Override
	public int recordTransferTransaction(BankTransaction transferFundsTransactionObj) throws BusinessException 
	{
		int c = 0;
//		System.out.println("Here is the recordTransferTransaction (transfer transactions table) method from DAO layer");
		log.info("Here is the recordTransferTransaction (transfer transactions table) method from DAO layer");

		System.out.println("\nTransaction details: " + transferFundsTransactionObj);
		
		//regular transaction between 2 not-null accounts 
		//(both transactions <send / receive> are recorded separately in a transfer transaction table)
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = BankAccountOperationsQueries.RECORDTRANSFERTRANSACTION;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, transferFundsTransactionObj.getSourceBankAccount().getBankAccountId());
			preparedStatement.setInt(2, transferFundsTransactionObj.getDestinationBankAccount().getBankAccountId());
			preparedStatement.setDouble(3, transferFundsTransactionObj.getAmount());
			preparedStatement.setTimestamp(4, transferFundsTransactionObj.getTransactionDate());
			preparedStatement.setString(5,transferFundsTransactionObj.getTransactionDescription());
			preparedStatement.setBoolean(6, transferFundsTransactionObj.isTransactionCleared());
			preparedStatement.setString(7, transferFundsTransactionObj.getTransactionType().toString());
			
			c = preparedStatement.executeUpdate();
			
			String sql1 = BankAccountOperationsQueries.GETLASTTRANSFERTRANSACTIONINSERTED;
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			ResultSet resultSet = preparedStatement1.executeQuery();

			if (resultSet.next())
			{
				transferFundsTransactionObj.setIdTransaction(resultSet.getInt("id_transfer_transaction"));						
			} else
			{
				throw new BusinessException("Invalid ID!!!... No matching records found for the ID = "
					+ resultSet.getInt("id_transaction"));
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
//			System.out.println(e);	// take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured in RECORDTRANSFERTRANSACTION sql... Please contact SYSADMIN");
		} 	
		return c;
	}
	
	@Override
	public void closeBankAccount(int bankAccounId, int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double getAccountBalance(int bankAccountId, int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> getAllBankAccountsList() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBankAccountDuplicate(long bankAccountNumber) throws BusinessException 
	{
//		System.out.println("Here is the isBankAccountDuplicate Method from DAO layer");
		log.info("Here is the isBankAccountDuplicate Method from DAO layer");
		
//		boolean isBankAccountDuplicate = true;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = BankAccountOperationsQueries.GETBANKACCOUNTBYNUMBER;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, bankAccountNumber);
			
			ResultSet resultSet = preparedStatement.executeQuery();
//			System.out.println("Result Set: "+resultSet);
			
			if (!resultSet.next())
			{
				//the bank account number is unique 
				return true;
			} else
			{
				//It was found another bank account with the same number
				System.out.println("Bank Account is already generated for another user!");
				log.info("Bank Account is already generated for another user!");
				return false;
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		}
	}

	@Override
	public int addNewBankAccountToApprovalTable(User user, BankAccount bankAccount) throws BusinessException 
	{
//		System.out.println("Here is the <Add New Bank Account To Approval Table> Method from DAO layer");
		log.info("Here is the <Add New Bank Account To Approval Table> Method from DAO layer");
		
		int c = 0;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
//			System.out.println("Calling the insert query!!");
			String sql = BankAccountOperationsQueries.INSERTBANKACCTTOAPPROVALTABLE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, bankAccount.getBankAccountId());
			preparedStatement.setBoolean(2, bankAccount.isBankAccountApproved());
			preparedStatement.setBoolean(3, bankAccount.isBankAccountApprovalPending());
			
			c = preparedStatement.executeUpdate();	
		}	
		catch (ClassNotFoundException | SQLException e) 
		{	
//				System.out.println(e); 	// take off this line when in production
				log.error(e);
				throw new BusinessException("Internal error occured in INSERTBANKACCTTOAPPROVALTABLE sql... Kindly contact SYSADMIN");
		} 
		return c;
	}

	@Override
	public List<BankAccount> getBankAccountsListByUser(User userSession) throws BusinessException 
	{	
		//Instantiate an object to record/handle the results of the query 
		List<BankAccount> bankAccountsListByUser = new ArrayList<>();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = BankAccountOperationsQueries.GETBANKACCOUNTLISTBYUSER;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userSession.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				BankAccount bankAccount = new BankAccount(resultSet.getInt("bank_account_id"), 
						resultSet.getLong("bank_account_number"), userSession.getId(),
						resultSet.getTimestamp("date_bank_acct_creation"), 
						StatusAccount.valueOf(resultSet.getString("type_account_status")),
						resultSet.getDouble("bank_account_balance"));
				bankAccountsListByUser.add(bankAccount);		
			} 
			if(bankAccountsListByUser.size()==0)
			{
				throw new BusinessException("No records of bank accounts opened by User: " + userSession.getId() 
				+ " | " + userSession.getFirstName() + userSession.getLastName() + " available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
//			System.out.println(e); // take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured in GETBANKACCOUNTLISTBYUSER sql... Kindly contact SYSADMIN");
		} 
		return bankAccountsListByUser;
	}

	@Override
	public List<BankAccount> getBankAccountsListByUser(int userId) throws BusinessException 
	{
		//Instantiate an object to record/handle the results of the query 
		List<BankAccount> bankAccountsListByUser = new ArrayList<>();
		User user = new User();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = BankAccountOperationsQueries.GETBANKACCOUNTLISTBYUSER;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			//Improvement: to change the output type, in order to be able to return User object as well
			//Apparently I already did it, by using 2 calls: one to retrieve User and one to retrieve the related Bank Accounts  
//			if (resultSet.next())
//			{	
//				user.setStatusUserId(userId);
//				user.setFirstName(resultSet.getString("first_name"));
//				user.setLastName(resultSet.getString("last_name"));
//			}
			while (resultSet.next())
			{
				BankAccount bankAccount = new BankAccount(resultSet.getInt("bank_account_id"), 
						resultSet.getLong("bank_account_number"), userId, 
						resultSet.getTimestamp("date_bank_acct_creation"),
						StatusAccount.valueOf(resultSet.getString("type_account_status")),
						resultSet.getDouble("bank_account_balance"));
				bankAccountsListByUser.add(bankAccount);
			} 
			if(bankAccountsListByUser.size()==0)
			{
				throw new BusinessException("No records of bank accounts opened by Customer with id: " 
					+ userId + " | " + user.getFirstName() + " " + user.getLastName()+ " available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
//			System.out.println(e); // take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured in GETBANKACCOUNTLISTBYUSER sql... Kindly contact SYSADMIN");
		} 
		return bankAccountsListByUser;
	}

	@Override
	public List<BankTransaction> getAllTransactions() throws BusinessException 
	{
//		System.out.println("Here is the getAllTransactions method from DAO layer");
		log.info("Here is the getAllTransactions method from DAO layer");
		//Instantiate an object to record/handle the results of the query 
		List<BankTransaction> allTransactionsList = new ArrayList<>();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = BankAccountOperationsQueries.GETALLTRANSACTIONS;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{	
				BankTransaction bankTransaction = new BankTransaction(resultSet.getInt("id_transaction"), 
						resultSet.getDouble("transaction_amount"), 
						TransactionType.valueOf(resultSet.getString("transaction_type")), 
						resultSet.getTimestamp("transaction_date"));
				bankTransaction.setTransactionDescription(resultSet.getString("transaction_description"));
				bankTransaction.setSourceBankAccountId(resultSet.getInt("id_source_bank_account"));
				bankTransaction.setDestinationBankAccountId(resultSet.getInt("id_destination_bank_account"));

				allTransactionsList.add(bankTransaction);
			} 
			if(allTransactionsList.size()==0)
			{
				throw new BusinessException("No players' records available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
//			System.out.println(e); // take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured in GETALLTRANSACTIONS sql.. Kindly contact SYSADMIN");
		} 
		return allTransactionsList;
	}

	@Override
	public List<BankAccount> getBankAccountsFromApprovalTable(boolean isBankAccountApprovalPending)
			throws BusinessException 
	{
//		System.out.println("Here is the getBankAccountsFromApprovalTable Method from DAO layer");
		log.info("Here is the getBankAccountsFromApprovalTable Method from DAO layer");
		//Instantiate an object to record/handle the results of the query 
		List<BankAccount> bankAccountsToBeApprovedList = new ArrayList<>();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = BankAccountOperationsQueries.GETBANKACCTSBYAPPROVALSTATUS;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, isBankAccountApprovalPending);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				BankAccount bankAccount = new BankAccount(resultSet.getInt("bank_account_id"), 
						resultSet.getLong("bank_account_number"), resultSet.getInt("bank_account_owner_id"), 
						StatusAccount.valueOf(resultSet.getString("type_account_status")));
				bankAccount.setBankAccountApproved(false);
				bankAccount.setBankAccountApprovalPending(true);
				bankAccountsToBeApprovedList.add(bankAccount);		
			} 
			if(bankAccountsToBeApprovedList.size()==0)
			{
				throw new BusinessException("No records of users with status PENDING: "+ isBankAccountApprovalPending +" for their requests to get their Customer Accounts approved available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
//			System.out.println(e); // take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured in GETBANKACCTSBYAPPROVALSTATUS sql... Kindly contact SYSADMIN");
		} 
		return bankAccountsToBeApprovedList;	
	}

	@Override
	public BankAccount getBankAccountByNumber(Long selectedDestinationBankAccountNumber) throws BusinessException 
	{
//		System.out.println("Here is the <Get Bank Account By Number> method from DAO layer");
		log.info("Here is the <Get Bank Account By Number> method from DAO layer");
		//Instantiate an object to record/handle the results of the query 
		BankAccount bankAccountByNumber = null;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = BankAccountOperationsQueries.GETBANKACCOUNTBYNUMBER;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, selectedDestinationBankAccountNumber);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				//The bank account with requested id was found
				bankAccountByNumber = new BankAccount(resultSet.getInt("bank_account_id"), 
						selectedDestinationBankAccountNumber, 
						resultSet.getInt("bank_account_owner_id"),
						resultSet.getTimestamp("date_bank_acct_creation"), 
						StatusAccount.valueOf(resultSet.getString("type_account_status")),
						resultSet.getDouble("bank_account_balance"));
			} else
			{
				log.warn("Bank Account: " + bankAccountByNumber);
				System.out.println("Invalid Bank Account Number!!!... No matching records found for the Bank Account Number = "
						+ selectedDestinationBankAccountNumber);
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
//			System.out.println(e); // take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured in GETBANKACCOUNTBYNUMBER sql... Kindly contact SYSADMIN");
		} 
		return bankAccountByNumber;	
	}

	@Override
	public List<BankTransaction> getAllTransferTransactionsByRecipientUser(User userSession, 
			TransactionType transactionType, boolean isTransactionCleared) throws BusinessException 
	{
		//Instantiate an object to record/handle the results of the query 
		List<BankTransaction> transferTransactionsListByRecipientUser = new ArrayList<>();		
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = BankAccountOperationsQueries.GETALLTRANSFERTRANSACTIONSBYRECIPIENTUSER;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userSession.getId());
			preparedStatement.setString(2, transactionType.toString());
			preparedStatement.setBoolean(3, isTransactionCleared);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				BankTransaction transferTransaction = new BankTransaction(resultSet.getInt("id_transfer_transaction"), 
					resultSet.getInt("id_source_bank_account"), 
					resultSet.getInt("id_destination_bank_account"), 
					resultSet.getDouble("transfer_amount"), 
					TransactionType.valueOf(resultSet.getString("transaction_type")), 
					resultSet.getTimestamp("transfer_date"));
				
				BankAccount destinationBankAccount = new BankAccount(resultSet.getInt("id_destination_bank_account"), 
						resultSet.getLong("destination_bk_acct_no"), 
						resultSet.getDouble("destination_bk_acct_balance"), 
						StatusAccount.valueOf(resultSet.getString("destination_type_acct_status")), 
						resultSet.getInt("destination_bk_acct_owner_id")); 
				transferTransaction.setDestinationBankAccount(destinationBankAccount);
				
				BankAccount sourceBankAccount = new BankAccount(resultSet.getInt("id_source_bank_account"), 
						resultSet.getLong("source_bk_acct_no"), 
						resultSet.getDouble("source_bk_acct_balance"), 
						StatusAccount.valueOf(resultSet.getString("source_type_acct_status")), 
						resultSet.getInt("source_bk_acct_owner_id")); 
				transferTransaction.setSourceBankAccount(sourceBankAccount);
				
				User userSender = new User (resultSet.getInt("source_bk_acct_owner_id"),
						resultSet.getString("sender_fname"),
						resultSet.getString("sender_lname"));
				transferTransaction.setUserSender(userSender);
				
				User userRecipient = new User (resultSet.getInt("destination_bk_acct_owner_id"),
						resultSet.getString("recipient_fname"),
						resultSet.getString("recipient_lname"));
				transferTransaction.setUserRecipient(userRecipient);
				
				transferTransaction.setTransactionDescription(resultSet.getString("transfer_description"));
				transferTransaction.setTransactionCleared(resultSet.getBoolean("is_transfer_cleared"));
				
				transferTransactionsListByRecipientUser.add(transferTransaction);
			} 
			if(transferTransactionsListByRecipientUser.size()==0)
			{
				throw new BusinessException("No records of incoming transactions for Customer with id: " 
					+ userSession.getId() + " | " + userSession.getFirstName() + " " + userSession.getLastName()
					+ " available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
//			System.out.println(e); // take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured in GETALLTRANSFERTRANSACTIONSBYRECIPIENTUSER sql... Kindly contact SYSADMIN");
		} 
		return transferTransactionsListByRecipientUser;
	}

	@Override
	public BankTransaction getTransferTransactionById(int selectedTransferTransactionId) throws BusinessException 
	{
//		System.out.println("Here is the <getTransferTransactionById> method from DAO layer");
		log.info("Here is the <getTransferTransactionById> method from DAO layer");
		//Instantiate an object to record/handle the results of the query 
		BankTransaction bankTransferTransactionById = null;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = BankAccountOperationsQueries.GETTRANSFERTRANSACTIONBYID;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, selectedTransferTransactionId);
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				//The bank account with requested id was found
				bankTransferTransactionById = new BankTransaction(resultSet.getInt("id_transfer_transaction"), 
					resultSet.getInt("id_source_bank_account"), 
					resultSet.getInt("id_destination_bank_account"), 
					resultSet.getDouble("transfer_amount"), 
					TransactionType.valueOf(resultSet.getString("transaction_type")), 
					resultSet.getTimestamp("transfer_date"));
				
				BankAccount destinationBankAccount = new BankAccount(resultSet.getInt("id_destination_bank_account"), 
						resultSet.getLong("destination_bk_acct_no"), 
						resultSet.getDouble("destination_bk_acct_balance"), 
						StatusAccount.valueOf(resultSet.getString("destination_type_acct_status")), 
						resultSet.getInt("destination_bk_acct_owner_id")); 					
				bankTransferTransactionById.setDestinationBankAccount(destinationBankAccount);
				
				BankAccount sourceBankAccount = new BankAccount(resultSet.getInt("id_source_bank_account"), 
						resultSet.getLong("source_bk_acct_no"), 
						resultSet.getDouble("source_bk_acct_balance"), 
						StatusAccount.valueOf(resultSet.getString("source_type_acct_status")), 
						resultSet.getInt("source_bk_acct_owner_id")); 
				bankTransferTransactionById.setSourceBankAccount(sourceBankAccount);
				
				User userSender = new User (resultSet.getInt("source_bk_acct_owner_id"),
						resultSet.getString("sender_fname"),
						resultSet.getString("sender_lname"));
				bankTransferTransactionById.setUserSender(userSender);
				
				User userRecipient = new User (resultSet.getInt("destination_bk_acct_owner_id"),
						resultSet.getString("recipient_fname"),
						resultSet.getString("recipient_lname"));
				bankTransferTransactionById.setUserRecipient(userRecipient);
				
				bankTransferTransactionById.setTransactionDescription(resultSet.getString("transfer_description"));
				bankTransferTransactionById.setTransactionCleared(resultSet.getBoolean("is_transfer_cleared"));
			} else
			{
				log.warn("Invalid ID!!!... No matching records found for the transaction ID = "+selectedTransferTransactionId);
				System.out.println("Invalid ID!!!... No matching records found for the transaction ID = "+selectedTransferTransactionId);
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
//			System.out.println(e); // take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured in GETTRANSFERTRANSACTIONBYID sql... Kindly contact SYSADMIN");
		} 
		return bankTransferTransactionById;	
	}

	@Override
	public int updateTransferTransactionClearingStatus(BankTransaction selectedTransferTransaction,
			boolean isTransactionCleared) throws BusinessException 
	{
		int c = 0;
//		System.out.println("Here is the updateTransferTransactionClearingStatus method from DAO layer");
		log.info("Here is the updateTransferTransactionClearingStatus method from DAO layer");
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = BankAccountOperationsQueries.UPDATETRANSFERTRANSACTIONCLEARANCESTATUS;
			
//			double newAccountBalance = bankAccount.getAccountBalance() + amountToDeposit;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, isTransactionCleared);
			preparedStatement.setInt(2, selectedTransferTransaction.getIdTransaction());
//			preparedStatement.setInt(3, userId);
			
			c = preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) 
		{
//			System.out.println(e);	// take off this line when in production
			log.error(e);
			throw new BusinessException("Internal error occured in UPDATETRANSFERTRANSACTIONCLEARANCESTATUS sql... Please contact SYSADMIN");
		} 
		return c;
	}
}
