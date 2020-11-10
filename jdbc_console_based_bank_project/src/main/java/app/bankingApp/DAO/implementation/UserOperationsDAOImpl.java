package app.bankingApp.DAO.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.StatusUser;
import app.bankingApp.model.User;
import app.bankingApp.presenter.implementation.MainMenuPresenterImpl;
import app.bankingApp.DAO.dbUtil.UserOperationsQueries;
import app.bankingApp.DAO.dbUtil.PostgresSqlConnection;

public class UserOperationsDAOImpl implements app.bankingApp.DAO.UserOperationsDAO 
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);

	@Override
	public int createUser(User user) throws BusinessException 
	{
//		System.out.println("Here is the CreateUser Method from DAO layer");
		log.info("Here is the CreateUser Method from DAO layer");
		int c = 0;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = UserOperationsQueries.INSERTUSER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
			preparedStatement.setLong(4, user.getPhoneNumber());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setTimestamp(7, user.getDateUserAccountCreation());
			preparedStatement.setTimestamp(8, user.getDateUserAccountDeletion());
			preparedStatement.setString(9, user.getStatusUser().toString());
						
			c = preparedStatement.executeUpdate();
			
//			System.out.println("User created! Extracting now the userId!");
			String sql1 = UserOperationsQueries.GETLASTUSERINSERTED;
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			ResultSet resultSet = preparedStatement1.executeQuery();

			if (resultSet.next())
			{
				user.setId(resultSet.getInt("user_id"));						
			} else
			{
				throw new BusinessException("Invalid ID!!!... No matching records found for the ID = "+ resultSet.getInt("user_id"));
			}
			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println(e); // take off this line when in production
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}		
		return c;
	}

	@Override
	public User getUserById(int id) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean getUserByEmail(String email) throws BusinessException 
	{
//		System.out.println("Here is the getUserByEmail Method from DAO layer");
		log.info("Here is the getUserByEmail Method from DAO layer");
		//Instantiate an object to record/handle the results of the query 
//		User userByEmail = null;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = UserOperationsQueries.GETUSERBYEMAIL;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next())
			{
				//It was found another user that is registered with the same email
				System.out.println("Email is already registered by another user!");
				log.info("Email is already registered by another user!");
				return false;
			} else
			{
				return true;
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			log.info(e);
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		}  
	}
	
	@Override
	public List<User> getAllUsers() throws BusinessException 
	{
//		System.out.println("Here is the getAllUsers Method from DAO layer");
		log.info("Here is the getAllUsers Method from DAO layer");
		//Instantiate an object to record/handle the results of the query 
		List<User> usersList = new ArrayList<>();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = UserOperationsQueries.GETALLUSERS;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				User user = new User(resultSet.getInt("user_id"), resultSet.getString("first_name"), resultSet.getString("last_name"),
						resultSet.getDate("dob"), resultSet.getLong("phone_number"), resultSet.getString("email"), 
						resultSet.getString("password"), resultSet.getTimestamp("date_user_account_creation"), 
						resultSet.getTimestamp("date_user_account_deletion"), StatusUser.valueOf(resultSet.getString("status_user")));
				usersList.add(user);		
			} 
			if(usersList.size()==0)
			{
				throw new BusinessException("No players' records available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		} 
		return usersList;
	}
	
	@Override
	public List<User> getUsersByEmail(String email) throws BusinessException 
	{
//		System.out.println("Here is the getUsersByEmail Method from DAO layer");
		log.info("Here is the getUsersByEmail Method from DAO layer");
		//Instantiate an object to record/handle the results of the query 
		List<User> usersListByEmail = new ArrayList<>();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = UserOperationsQueries.GETUSERSLISTBYEMAIL;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				User user = new User(resultSet.getInt("user_id"), resultSet.getString("first_name"), 
						resultSet.getString("last_name"), resultSet.getDate("dob"), resultSet.getLong("phone_number"), 
						resultSet.getString("email"), resultSet.getString("password"), 
						resultSet.getTimestamp("date_user_account_creation"), resultSet.getTimestamp("date_user_account_deletion"), 
						StatusUser.valueOf(resultSet.getString("status_user")));
				usersListByEmail.add(user);		
			} 
			if(usersListByEmail.size()==0)
			{
				throw new BusinessException("No records of users with email: "+ email +" available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			log.info(e);
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		} 
		return usersListByEmail;
	}
	
	@Override
	public int addNonCustomerUserToApprovalTable(User user) throws BusinessException 
	{
//		System.out.println("Here is the <Add NonCustomer User To Approval Table> Method from DAO layer");
		log.info("Here is the <Add NonCustomer User To Approval Table> Method from DAO layer");
		int c = 0;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = UserOperationsQueries.INSERTUSERTOCUSTOMERAPPROVALTABLE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setBoolean(2, user.isCustomerStatusApproved());
			preparedStatement.setBoolean(3, user.isCustomerApprovalPending());
			
			c = preparedStatement.executeUpdate();
			
		}	
		catch (ClassNotFoundException | SQLException e) 
		{	
				System.out.println(e); // take off this line when in production
				log.info(e);
				throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		} 
		return c;
	}
	
	@Override
	public List<User> getUersByLastName(String lastName) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> getUersByFirstName(String firstName) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> getUsersByAccountId(String accountId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> getUsersByStatus(String statusUser) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getUserByContactNumber(Long contact) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateUser(User user) throws BusinessException 
	{
//		System.out.println("Here is the <Change User Status> Method from DAO layer");
		log.info("Here is the <Change User Status and Change Customer Acct Approval Status> Method from DAO layer");
		int c = 0;
		int d = 0;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = UserOperationsQueries.UPDATEUSERCUSTOMERSTATUS;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getStatusUser().toString());
			
			c = preparedStatement.executeUpdate();
			
			sql = UserOperationsQueries.UPDATEUSERCUSTOMERREQAPPROVALSTATUS;
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, user.isCustomerApprovalPending());
			preparedStatement.setBoolean(2, user.isCustomerStatusApproved());
			preparedStatement.setInt(3, user.getId());
			
			d = preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println(e);	// take off this line when in production
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
	public void deleteUser() throws BusinessException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getUsersFromApprovalTable(boolean userApprovalPendingStatus) throws BusinessException 
	{
//		System.out.println("Here is the getUsersFromApprovalTable Method from DAO layer");
		log.info("Here is the getUsersFromApprovalTable Method from DAO layer");
		//Instantiate an object to record/handle the results of the query 
		List<User> usersToBeApprovedList = new ArrayList<>();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = UserOperationsQueries.GETUSERSBYCUSTOMERACCOUNTAPPROVALSTATUS;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, userApprovalPendingStatus);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				User user = new User(resultSet.getInt("user_id"), resultSet.getString("first_name"), 
						resultSet.getString("last_name"), resultSet.getDate("dob"), resultSet.getLong("phone_number"), 
						resultSet.getString("email"), resultSet.getString("password"), 
						resultSet.getTimestamp("date_user_account_creation"), resultSet.getTimestamp("date_user_account_deletion"), 
						StatusUser.valueOf(resultSet.getString("status_user")));
				usersToBeApprovedList.add(user);		
			} 
			if(usersToBeApprovedList.size()==0)
			{
				throw new BusinessException("No records of users with status PENDING: "+ userApprovalPendingStatus +" for their requests to get their Customer Accounts approved available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			log.info(e);
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		} 
		return usersToBeApprovedList;	
	}

//	@Override
//	public int changeUserStatus(User user) throws BusinessException 
//	{
////		System.out.println("Here is the <Change User Status> Method from DAO layer");
//		log.info("Here is the <Change User Status> Method from DAO layer");
//		int c = 0;
//		
//		return 0;
//	}
}