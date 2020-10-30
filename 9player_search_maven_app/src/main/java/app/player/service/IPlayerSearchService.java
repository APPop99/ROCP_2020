package app.player.service;

import java.util.List;

import app.player.exception.BusinessException;
import app.player.model.Player;

//This interface is providing business logic and services to access the methods from Data Access Object
//Was moved from the Presenter level (View / Main method)
public interface IPlayerSearchService 
{
	public Player getPlayerById(int id) throws BusinessException;
	public List<Player> getAllPlayers() throws BusinessException;
	public List<Player> getPlayersByName(String name) throws BusinessException;
	public List<Player> getPlayersByGender(String gender) throws BusinessException;
	public List<Player> getPlayersByTeamName(String teamName) throws BusinessException;
	public List<Player> getPlayersByAge(int age) throws BusinessException;
	public Player getPlayerByContactNumber(Long contact) throws BusinessException;
}