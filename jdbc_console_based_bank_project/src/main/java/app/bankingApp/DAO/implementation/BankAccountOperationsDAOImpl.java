package app.bankingApp.DAO.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import app.bankingApp.DAO.BankAccountOperationsDAO;
import app.bankingApp.DAO.dbUtil.BankAccountOperationsQueries;
import app.bankingApp.DAO.dbUtil.PostgresSqlConnection;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.BankAccount;
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

			preparedStatement.setLong(1, bankAccount.getBankAccountNumber());
			preparedStatement.setDouble(2, bankAccount.getAccountBalance());
			preparedStatement.setInt(3, bankAccount.getAccountOwnerId());
			preparedStatement.setTimestamp(4, bankAccount.getDateBankAccountCreation());
			preparedStatement.setTimestamp(5, bankAccount.getDateBankAccountApproval());
			preparedStatement.setTimestamp(6, bankAccount.getDateBankAccountDeletion());
			preparedStatement.setString(7, user.getStatusUser().toString());
						
			c = preparedStatement.executeUpdate();
			
//			System.out.println("Bank Account created! Extracting now the bankAccountId!");
			String sql1 = BankAccountOperationsQueries.GETLASTBANKACCTINSERTED;
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			ResultSet resultSet = preparedStatement1.executeQuery();

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
			String sql = BankAccountOperationsQueries.UPDATEBANKACCOUNTSTATUS;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bankAccount.getBankAccountId());
			preparedStatement.setString(2, bankAccount.getStatusBankAccount().toString());
			
			c = preparedStatement.executeUpdate();
			
			sql = BankAccountOperationsQueries.UPDATEBANKACCOUNTAPPROVALSTATUS;
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, bankAccount.isBankAccountApprovalPending());
			preparedStatement.setBoolean(2, bankAccount.isBankAccountApproved());
			preparedStatement.setInt(3, bankAccount.getBankAccountId());
			
			d = preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) 
		{
//			System.out.println(e);	// take off this line when in production
			log.info(e);
			throw new BusinessException("Internal error occured... Please contact SYSADMIN");
		} 
		if (c!=0 && d!=0)
		{
			return c;
		} else 
		{
			return 0;
		}
	}

	@Override
	public BankAccount getBankAccountById(int id) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transferFundsOut(int sourceBankAccounId, int destinationBankAccounId, Double transferAmount, int userId)
			throws BusinessException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferFundsIn(int sourceBankAccounId, int destinationBankAccounId, Double transferAmount, int userId)
			throws BusinessException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdrawFunds(int bankAccounID, Double withdrawAmount, int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void depositFunds(int bankAccounID, Double deposit, int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		
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
	public List<BankAccount> getAccountsList(int userId) throws BusinessException 
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
//		System.out.println("Here is the getBankAccountByNumber Method from DAO layer");
		log.info("Here is the getBankAccountByNumber Method from DAO layer");
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = BankAccountOperationsQueries.GETBANKACCOUNTBYNUMBER;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, bankAccountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next())
			{
				//It was found another bank account with the same number
				System.out.println("Bank Account is already generated for another user!");
				log.info("Bank Account is already generated for another user!");
				return false;
			} else
			{
				return true;
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
//			System.out.println(e); // take off this line when in production
			log.info(e);
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
				log.info(e);
				throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		} 
		return c;
	}

	@Override
	public List<BankAccount> getBankAccountByUser(User userSession) throws BusinessException 
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
						resultSet.getLong("bank_account_number"), resultSet.getTimestamp("date_bank_account_creation"), 
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
			log.info(e);
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		} 
		return bankAccountsListByUser;
	}

	@Override
	public List<BankAccount> getBankAccountByUser(int userId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
