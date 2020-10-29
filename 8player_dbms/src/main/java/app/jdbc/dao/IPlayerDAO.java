package app.jdbc.dao;

import app.jdbc.exception.BusinessException;
import app.jdbc.model.Player;

//DAO - Data Access Object
//A DAO represents that all the code which is related  to your DB or persistence or
//storage will be written inside this.
public interface IPlayerDAO {

	public int createPlayer(Player player)throws BusinessException;

	public int updatePlayerTeamName(int id, String newTeamName)throws BusinessException;

	public int deletePlayer(int id)throws BusinessException;

	public Player getPlayerById(int id)throws BusinessException;
}