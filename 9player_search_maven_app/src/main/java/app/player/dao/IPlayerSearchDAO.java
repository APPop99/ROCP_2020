package app.player.dao;

import java.util.List;

import app.player.exception.BusinessException;
import app.player.model.Player;

//This is the interface providing access to Data Layer (Data Access Object)
public interface IPlayerSearchDAO 
{
	public Player getPlayerById(int id) throws BusinessException;
	public List<Player> getAllPlayers() throws BusinessException;
	public List<Player> getPlayersByName(String name) throws BusinessException;
	public List<Player> getPlayersByGender(String gender) throws BusinessException;
	public List<Player> getPlayersByTeamName(String teamName) throws BusinessException;
	public List<Player> getPlayersByAge(int age) throws BusinessException;
	public Player getPlayerByContactNumber(Long contact) throws BusinessException;
}
