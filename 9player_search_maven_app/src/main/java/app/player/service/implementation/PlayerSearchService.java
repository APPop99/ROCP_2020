package app.player.service.implementation;

import java.util.List;

import app.player.dao.IPlayerSearchDAO;
import app.player.dao.implementation.PlayerSearchDAO;
import app.player.exception.BusinessException;
import app.player.model.Player;
import app.player.service.IPlayerSearchService;

//Methods provide higher level access to data and business logic
public class PlayerSearchService implements IPlayerSearchService 
{
	//instantiate an object that will be used (through its methods) to transfer data to and from Data Access layer 
	private IPlayerSearchDAO searchDAO = new PlayerSearchDAO();  
	
	@Override
	public Player getPlayerById(int id) throws BusinessException 
	{
		Player player = null;
		if (id > 99 && id < 1000)
		{
			// insert here the code for DAO, and it will return a player object that respects the requirements (id)
			player = searchDAO.getPlayerById(id);
		} else
		{
			throw new BusinessException("Entered Player ID " + id + " is INVALID!!!.. Please ReEnter");
		}
		return player;
	}

	@Override
	public List<Player> getAllPlayers() throws BusinessException 
	{
		List<Player> playersList = null;
		playersList = searchDAO.getAllPlayers();
		return playersList;
	}

	@Override
	public List<Player> getPlayersByName(String name) throws BusinessException 
	{
		List<Player> playersListName = null;
		playersListName = searchDAO.getPlayersByName(name);
		return playersListName;
	}

	@Override
	public List<Player> getPlayersByGender(String gender) throws BusinessException 
	{
		List<Player> playersListGender = null;
		playersListGender = searchDAO.getPlayersByGender(gender);
		return playersListGender;
	}

	@Override
	public List<Player> getPlayersByTeamName(String teamName) throws BusinessException 
	{
		List<Player> playersListTeamName = null;
		playersListTeamName = searchDAO.getPlayersByGender(teamName);
		return playersListTeamName;
	}

	@Override
	public List<Player> getPlayersByAge(int age) throws BusinessException 
	{
		List<Player> playersListByAge = null;
		playersListByAge = searchDAO.getPlayersByAge(age);
		return playersListByAge;
	}

	@Override
	public Player getPlayerByContactNumber(Long contact) throws BusinessException 
	{
		Player playerByContact = null;
		playerByContact = searchDAO.getPlayerByContactNumber(contact);
		return playerByContact;
	}
}
