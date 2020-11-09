package app.bankingApp.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import app.bankingApp.DAO.UserOperationsDAO;
import app.bankingApp.DAO.implementation.UserOperationsDAOImpl;
import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.User;
import app.bankingApp.presenter.implementation.MainMenuPresenterImpl;
import app.bankingApp.service.UserService;

public class UserServiceImpl implements UserService
{
	private static Logger log=Logger.getLogger(MainMenuPresenterImpl.class);

	//instantiate an object that will be used (through its methods) to transfer data to and from Data Access layer 
	private UserOperationsDAO userDAO = new UserOperationsDAOImpl();  
	
	@Override
	public int createUser(User user) throws BusinessException {
//		System.out.println("Here is the CreateUser Method from Service layer");
		log.info("Here is the CreateUser Method from Service layer");
		int c = userDAO.createUser(user);		
		return c;
	}

	@Override
	public int updateUser() throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
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
	public boolean getUserByEmail(String email) throws BusinessException {
//		System.out.println("Here is the getUserByEmail Method from Service layer");
		log.info("Here is the getUserByEmail Method from Service layer");		
//		List<User> usersListByEmail = null;
		return userDAO.getUserByEmail(email);
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
	public List<User> getUsersByAccountId(String bankAccountId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByPhoneNumber(Long phoneNumber) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
