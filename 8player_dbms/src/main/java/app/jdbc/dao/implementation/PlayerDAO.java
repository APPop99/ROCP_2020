package app.jdbc.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.jdbc.dao.IPlayerDAO;
import app.jdbc.dbutil.PostgresSqlConnection;
import app.jdbc.exception.BusinessException;
import app.jdbc.model.Player;

public class PlayerDAO implements IPlayerDAO
{
	@Override
	public int createPlayer(Player player) throws BusinessException {
		int c=0;
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = "INSERT INTO roc_revature.player(id, name, age, gender, teamName, contact)"
					+ "VALUES(?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, player.getId());
			preparedStatement.setString(2, player.getName());
			preparedStatement.setInt(3, player.getAge());
			preparedStatement.setString(4, player.getGender());
			preparedStatement.setString(5, player.getTeamName());
			preparedStatement.setLong(6, player.getContact());
			
			c = preparedStatement.executeUpdate();
			
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println(e);	// take off this line when in production
			throw new BusinessException("Internal error occured... Please contact SYSADMIN");
		} 
		return c;
	}

	public int updatePlayerTeamName(int id, String newTeamName) throws BusinessException {
		int c=0;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = "UPDATE roc_revature.player SET teamName=? WHERE id=? "
					+ "VALUES(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newTeamName);
			preparedStatement.setInt(2, id);
			
			c = preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println(e);	// take off this line when in production
			throw new BusinessException("Internal error occured... Please contact SYSADMIN");
		} 
		return c;
	}

	public int deletePlayer(int id) throws BusinessException {
		int c=0;
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			//write here the delete query
			String sql = "DELETE FROM roc_revature.player WHERE id=? "
				+ "VALUES(?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			//set parameter here

			c=preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); // take off this line when in production
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return c;
	}

	public Player getPlayerById(int id) throws BusinessException {
		Player player=null;
		
		try (Connection connection=PostgresSqlConnection.getConnection())
		{
			String sql = "SELECT  name, age, gender, teamName, contact FROM roc_revature.player WHERE id=?";
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
}
