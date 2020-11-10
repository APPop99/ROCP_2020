package app.bankingApp.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import app.bankingApp.DAO.UserOperationsDAO;
import app.bankingApp.DAO.implementation.UserOperationsDAOImpl;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.Customer;
import app.bankingApp.model.User;
import app.bankingApp.presenter.implementation.MainMenuPresenterImpl;
import app.bankingApp.service.UserService;

public class UserServiceImpl implements UserService
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);

	//instantiate an object that will be used (through its methods) to transfer data to and from Data Access layer 
	private UserOperationsDAO userDAO = new UserOperationsDAOImpl();  
	
	@Override
	public int createUser(User user) throws BusinessException 
	{
		//		System.out.println("Here is the CreateUser Method from Service layer");
		log.info("Here is the CreateUser Method from Service layer");
		int c = userDAO.createUser(user);		
		return c;
	}

	@Override
	public int updateUser(User user) throws BusinessException 
	{
//		System.out.println("Here is the CreateUser Method from Service layer");
		log.info("Here is the createCustomerToBeApproved Method from Service layer");
		int c = userDAO.updateUser(user);		
		return c;
	}

	@Override
	public void deleteUser(int id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getUsersByEmail(String email) throws BusinessException {
//		System.out.println("Here is the getUsersByEmail Method from Service layer");
		log.info("Here is the getUsersByEmail Method from Service layer");		
		List<User> usersListByEmail = null;
		usersListByEmail = userDAO.getUsersByEmail(email);
		return usersListByEmail;
	}

	@Override
	public boolean isUserByEmailDuplicate(String email) throws BusinessException {
//		System.out.println("Here is the getUserByEmail Method from Service layer");
		log.info("Here is the getUserByEmail Method from Service layer");		
//		List<User> usersListByEmail = null;
		return userDAO.isUserByEmailDuplicate(email);
//		return usersListByEmail;
	}
	
	@Override
	public List<User> getAllUsers() throws BusinessException {
//		System.out.println("Here is the getAllUsers Method from Service layer");
		log.info("Here is the getAllUsers Method from Service layer");		
		List<User> usersList = null;
		usersList = userDAO.getAllUsers();
		return usersList;
	}

	@Override
	public List<User> getUsersByLastName(String LastName) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByFirstName(String firstName) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByStatus(String statusUser) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> getUsersFromApprovalTable(boolean userApprovalPendingStatus) throws BusinessException 
	{
	//	System.out.println("Here is the getUsersFromApprovalTable Method from Service layer");
		log.info("Here is the getUsersFromApprovalTable Method from Service layer");		
		List<User> usersToBeApprovedList = null;
		usersToBeApprovedList = userDAO.getUsersFromApprovalTable(userApprovalPendingStatus);
		return usersToBeApprovedList;
	}

	@Override
	public List<User> getUsersByAccountId(String bankAccountId) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByPhoneNumber(Long phoneNumber) throws BusinessException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addNonCustomerUserToApprovalTable(User user) throws BusinessException 
	{
		System.out.println("Here is the <Add NonCustomer User To Approval Table> Method from Service layer");
		log.info("Here is the <Add NonCustomer User To Approval Table> Method from Service layer");
		int c = userDAO.addNonCustomerUserToApprovalTable(user);		
		return c;
	}

	@Override
	public User getUserById(int id) throws BusinessException 
	{
//		System.out.println("Here is the <Get Use By ID> Method from Service layer");
		log.info("Here is the <Get Use By ID> Method from Service layer");
		
		User user = null;
		user = userDAO.getUserById(id);
		return user;
	}

//	@Override
//	public int changeUserStatus(User user) throws BusinessException 
//	{
////		System.out.println("Here is the CreateUser Method from Service layer");
//		log.info("Here is the createCustomerToBeApproved Method from Service layer");
//		int c = userDAO.changeUserStatus(user);		
//		return c;
//	}
}
