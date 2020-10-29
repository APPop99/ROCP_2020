package app.jdbc;

import app.jdbc.dao.IPlayerDAO;
import app.jdbc.dao.implementation.PlayerDAO;
import app.jdbc.exception.BusinessException;
import app.jdbc.model.Player;

public class playerDBMS_demo {

	public static void main(String[] args) 
	{

		//CREATE operation = instantiate a new player and save in db
		Player playerCreated=new Player(112, "Laura", 26, "F", "Rockers", 5323467890L);
		
		PlayerDAO playerDAO=new PlayerDAO();

		try {
			if(playerDAO.createPlayer(playerCreated)>0)
			{
				System.out.println("Player created in DB with below details:");
				System.out.println(playerCreated);	
			}
		} catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}

		//READ operation = finding a player specified by id
		try {
			int id=106;
			Player playerRead=playerDAO.getPlayerById(id);
			if (playerRead!=null)
			{
				System.out.println("Player found with id "+id+" details are : ");
				System.out.println(playerRead);
			}
		} 
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}
		
		//UPDATE operation = finding a player specified by id and updating a field (teamName)
		try {
			int id=107;
			String newTeamName = "Bulls";
			
			Player playerRead=playerDAO.getPlayerById(id);
			if (playerRead!=null)
			{
				System.out.println("Player found with id "+id+" details are : ");
				System.out.println(playerRead);
				if(playerDAO.updatePlayerTeamName(playerRead.getId(), newTeamName)>0)
				{
					System.out.println("Player with id: " + id + " has an updated team name: " + newTeamName);	
				}
			}
			
		} catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}
		
		//DELETE operation = deleting a player specified by id;
		try 
		{
			int id=109;
			Player playerRead=playerDAO.getPlayerById(id);
			
			if (playerRead!=null)
			{
				System.out.println("Player found with id "+id+" details are : ");
				System.out.println(playerRead);
				
				if(playerDAO.deletePlayer(id)>0)		
				{
					System.out.println("Player with id " + id + " was deleted from DB.");
				}
			}
		} 
		catch (BusinessException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
