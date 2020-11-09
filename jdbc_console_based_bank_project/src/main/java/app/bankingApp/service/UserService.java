package app.bankingApp.service;

import java.util.List;

import app.bankingApp.exception.BusinessException;
import app.bankingApp.model.User;

public interface UserService 
{
	//CREATE operation
	public int createUser(User user)throws BusinessException;
	//UPDATE operation
	public int updateUser()throws BusinessException;
	//DELETE operation
	public void deleteUser(int id)throws BusinessException;
	//READ operation
	public boolean getUserByEmail(String email) throws BusinessException;
	public List<User> getUsersByEmail(String email) throws BusinessException;
	public List<User> getAllUsers() throws BusinessException;
	public List<User> getUsersByLastName(String LastName) throws BusinessException;
	public List<User> getUsersByFirstName(String firstName) throws BusinessException;
	public List<User> getUsersByStatus(String statusUser) throws BusinessException;
	public List<User> getUsersByAccountId(String bankAccountId) throws BusinessException;
	public User getUserByPhoneNumber(Long phoneNumber) throws BusinessException;
}
