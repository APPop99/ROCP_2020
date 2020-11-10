package app.bankingApp.DAO;

import java.util.List;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.User;

//This is the interface providing access to Data Layer (Data Access Object)
public interface UserOperationsDAO 
{
	//CREATE operation
	public int createUser(User user)throws BusinessException;
	//UPDATE operation
	public int updateUser(User user)throws BusinessException;
	public int addNonCustomerUserToApprovalTable(User user)throws BusinessException;
	//DELETE operation
	public void deleteUser(/*to include parameters*/)throws BusinessException;
	//READ operation
	public User getUserById(int id) throws BusinessException;
	public boolean getUserByEmail(String email) throws BusinessException;
	public List<User> getUsersByEmail(String email) throws BusinessException;
//	public User getUserByAccountId(String accountId) throws BusinessException;
	public List<User> getAllUsers() throws BusinessException;
	public List<User> getUersByLastName(String lastName) throws BusinessException;
	public List<User> getUersByFirstName(String firstName) throws BusinessException;
//	public List<User> getUersByEmail(String email) throws BusinessException; //?? probably will use email as Primary key in db??
	public List<User> getUsersByAccountId(String accountId) throws BusinessException;
	public List<User> getUsersByStatus(String statusUser) throws BusinessException;
	public User getUserByContactNumber(Long contact) throws BusinessException;
	public List<User> getUsersFromApprovalTable(boolean userApprovalPendingStatus) throws BusinessException;
//	public int changeUserStatus(User user) throws BusinessException;
}
