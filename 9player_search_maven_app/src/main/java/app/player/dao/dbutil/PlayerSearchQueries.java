package app.player.dao.dbutil;

public class PlayerSearchQueries 
{
	public static final String GETPLAYERBYID = "SELECT name, age, gender, teamName, contact FROM roc_revature.player WHERE id=?";
	public static final String GETALLPLAYERS = "SELECT id,name,age,gender,teamname,contact FROM roc_revature.player ORDER BY id ASC";
	public static final String GETPLAYERSBYGENDER = "SELECT id,name, age, gender, teamName, contact FROM roc_revature.player WHERE gender=? ORDER BY id ASC";
	public static final String GETPLAYERSBYNAME = "SELECT id,name, age, gender, teamName, contact FROM roc_revature.player WHERE name=? ORDER BY id ASC";
	public static final String GETPLAYERSBYTEAMNAME = "SELECT id,name, age, gender, teamName, contact FROM roc_revature.player WHERE teamName=? ORDER BY id ASC";
	public static final String GETPLAYERSBYAGE = "SELECT id,name, age, gender, teamName, contact FROM roc_revature.player WHERE age=? ORDER BY id ASC";
	public static final String GETPLAYERSBYCONTACT = "SELECT id,name, age, gender, teamName, contact FROM roc_revature.player WHERE contact=? ORDER BY id ASC";
}