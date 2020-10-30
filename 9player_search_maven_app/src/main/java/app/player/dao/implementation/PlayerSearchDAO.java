package app.player.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.player.dao.dbutil.PlayerSearchQueries;
import app.player.dao.dbutil.PostgresSqlConnection;
import app.player.dao.IPlayerSearchDAO;
import app.player.exception.BusinessException;
import app.player.model.Player;

//Methods provide lower level access to data
public class PlayerSearchDAO implements IPlayerSearchDAO
{
//	@Override
	public Player getPlayerById(int id) throws BusinessException 
	{
		Player player=null;
		//connects to database
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = PlayerSearchQueries.GETPLAYERBYID;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				player = new Player(id, resultSet.getString("name"), resultSet.getInt("age"), 
						resultSet.getString("gender"), resultSet.getString("teamName"), resultSet.getLong("contact"));
						
			} else
			{
				throw new BusinessException("Invalid ID!!!... No matching records found for the ID = "+id);
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		} 
		
		return player;
	}

	public List<Player> getAllPlayers() throws BusinessException 
	{
		//Instantiate an object to record/handle the results of the query 
		List<Player> playersList = new ArrayList<>();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = PlayerSearchQueries.GETALLPLAYERS;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				Player player = new Player(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"), 
						resultSet.getString("gender"), resultSet.getString("teamName"), resultSet.getLong("contact"));
				playersList.add(player);		
			} 
			if(playersList.size()==0)
			{
				throw new BusinessException("No players' records available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		} 
		return playersList;
	}

	public List<Player> getPlayersByName(String name) throws BusinessException 
	{
		//Instantiate an object to record/handle the results of the query 
		List<Player> playersListByName = new ArrayList<>();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = PlayerSearchQueries.GETPLAYERSBYNAME;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name.replace(name.charAt(0), Character.toUpperCase(name.charAt(0))));
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				Player player = new Player(resultSet.getInt("id"), name, resultSet.getInt("age"), 
						resultSet.getString("gender"), resultSet.getString("teamName"), resultSet.getLong("contact"));
				playersListByName.add(player);		
			} 
			if(playersListByName.size()==0)
			{
				throw new BusinessException("No records of players with Name "+name+" available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		} 
		return playersListByName;
	}

	public List<Player> getPlayersByGender(String gender) throws BusinessException 
	{
		//Instantiate an object to record/handle the results of the query 
		List<Player> playersListByGender = new ArrayList<>();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = PlayerSearchQueries.GETPLAYERSBYGENDER;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, gender.toUpperCase());
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				Player player = new Player(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"), 
						gender, resultSet.getString("teamName"), resultSet.getLong("contact"));
				playersListByGender.add(player);		
			} 
			if(playersListByGender.size()==0)
			{
				throw new BusinessException("No records of players with Gender "+gender+" available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		} 
		return playersListByGender;
	}

	public List<Player> getPlayersByTeamName(String teamName) throws BusinessException 
	{
		//Instantiate an object to record/handle the results of the query 
		List<Player> playersListByTeamName = new ArrayList<>();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = PlayerSearchQueries.GETPLAYERSBYTEAMNAME;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teamName.replace(teamName.charAt(0), Character.toUpperCase(teamName.charAt(0))));
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				Player player = new Player(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"), 
						resultSet.getString("gender"), teamName, resultSet.getLong("contact"));
				playersListByTeamName.add(player);		
			} 
			if(playersListByTeamName.size()==0)
			{
				throw new BusinessException("No records of players playing for the team "+teamName+" available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		} 
		return playersListByTeamName;
	}

	public List<Player> getPlayersByAge(int age) throws BusinessException 
	{
		//Instantiate an object to record/handle the results of the query 
		List<Player> playersListByAge = new ArrayList<>();
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{		//instantiate the sql
			String sql = PlayerSearchQueries.GETPLAYERSBYAGE;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, age);
			ResultSet resultSet = preparedStatement.executeQuery();
		
			while (resultSet.next())
			{
				Player player = new Player(resultSet.getInt("id"), resultSet.getString("name"), age, 
					resultSet.getString("gender"), resultSet.getString("teamName"), resultSet.getLong("contact"));
				playersListByAge.add(player);		
			} 
			if(playersListByAge.size()==0)
			{
				throw new BusinessException("No records of players of age "+age+" available to retrieve!");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		} 
		return playersListByAge;
	}

	public Player getPlayerByContactNumber(Long contact) throws BusinessException 
	{
		Player player=null;
		//connects to database
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			//instantiate the sql
			String sql = PlayerSearchQueries.GETPLAYERSBYCONTACT;		// Values are passed as arguments of the method
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, contact);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				player = new Player(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"), 
						resultSet.getString("gender"), resultSet.getString("teamName"), contact);
						
			} else
			{
				throw new BusinessException("Invalid Contact!!!... No matching records found for the contact number= "+contact);
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			System.out.println(e); // take off this line when in production
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		} 
		
		return player;
	}
}