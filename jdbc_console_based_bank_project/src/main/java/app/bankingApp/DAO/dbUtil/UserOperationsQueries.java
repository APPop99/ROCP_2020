package app.bankingApp.DAO.dbUtil;

public class UserOperationsQueries 
{
	public static final String INSERTUSER = "INSERT INTO project_console_bank.user(first_name, last_name, dob, "
			+ "phone_number, email, password, date_user_account_creation, date_user_account_deletion, status_user_id) "
			+ "VALUES(?,?,?,?,?,?,?,?,(select status_id from project_console_bank.user_status where status_user=?))";
	
	public static final String GETLASTUSERINSERTED = "SELECT user_id, date_user_account_creation "
			+ "FROM project_console_bank.user ORDER BY date_user_account_creation DESC LIMIT 1";
// OR I can add 'RETURN ID' at the end of the INSERT query;

	public static final String UPDATEUSERSTATUS = "UPDATE project_console_bank.user"
			+ "set status_user_id = status_id"
			+ "from project_console_bank.user_status"
			+ "where project_console_bank.user.user_id = ? and"
			+ "project_console_bank.user_status.status_user = ?";
	
//	public static final String INSERTUSEREMPLOYEE = "INSERT INTO project_console_bank.user(first_name, last_name, dob, "
//			+ "phone_number, email, password, date_user_account_creation, date_user_account_deletion, status_user_id) "
//			+ "VALUES(?,?,?,?,?,?,?,?,(select status_id from project_console_bank.user_status where status_user=?))";
	
//	public static final String GETUSERBYID = "SELECT name, age, gender, teamName, contact FROM roc_revature.player WHERE id=?";
	
	public static final String GETUSERBYEMAIL = "SELECT user_id, first_name, last_name, dob, phone_number, email, "
			+ "password, date_user_account_creation, date_user_account_deletion, status_user"
			+ " FROM project_console_bank.user u "
			+ " left join project_console_bank.user_status us on u.status_user_id = us.status_id "
			+ " WHERE email = ? AND ORDER BY last_name ASC";
	
	public static final String GETUSERSLISTBYEMAIL = "SELECT user_id, first_name, last_name, dob, phone_number, email, "
			+ "password, date_user_account_creation, date_user_account_deletion, status_user"
			+ " from project_console_bank.user u "
			+ " left join project_console_bank.user_status us on u.status_user_id = us.status_id "
			+ " where email = ? ORDER BY last_name ASC";
	
	public static final String GETALLUSERS = "SELECT user_id, first_name, last_name, dob, phone_number, email, "
			+ "password, date_user_account_creation, date_user_account_deletion, status_user"
			+ " FROM project_console_bank.user u "
			+ " left join project_console_bank.user_status us on u.status_user_id = us.status_id "
			+ " ORDER BY last_name ASC";
	
//	public static final String GETUSERSBYLASTNAME = "SELECT id,name, age, gender, teamName, contact FROM roc_revature.player WHERE name=? ORDER BY id ASC";
//	public static final String GETUSERSBYFIRSTNAME = "SELECT id,name, age, gender, teamName, contact FROM roc_revature.player WHERE teamName=? ORDER BY id ASC";
//	public static final String GETUSERSBYACCOUNTID = "SELECT id,name, age, gender, teamName, contact FROM roc_revature.player WHERE age=? ORDER BY id ASC";
//	public static final String GETUSERSBYSTATUS = "SELECT id,name, age, gender, teamName, contact FROM roc_revature.player WHERE contact=? ORDER BY id ASC";
//	public static final String GETUSERBYPHONENUMBER = "SELECT id,name, age, gender, teamName, contact FROM roc_revature.player WHERE contact=? ORDER BY id ASC";
}